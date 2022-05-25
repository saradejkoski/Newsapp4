package at.ac.fhcampuswien.controllers;

import at.ac.fhcampuswien.api.NewsApi;
import at.ac.fhcampuswien.downloader.Downloader;
import at.ac.fhcampuswien.enums.Country;
import at.ac.fhcampuswien.enums.Endpoint;
import at.ac.fhcampuswien.models.Article;
import at.ac.fhcampuswien.models.NewsResponse;
import at.ac.fhcampuswien.models.Source;

import java.util.*;
import java.util.stream.Collectors;

public class AppController {
    private List<Article> articles;

    public AppController() {}

    public void setArticles(List<Article> articles){
        this.articles = articles;
    }

    public List<Article> getArticles(){
        return articles;
    }

    // Method is needed for exercise 4 - ignore for exercise 3 solution
    // returns number of downloaded article urls
    public int downloadURLs(Downloader downloader) throws NewsAPIException{
        if( articles == null)
            throw new NewsAPIException();

        List<String> urls = new ArrayList<>();

        // TODO extract urls from articles with java stream

        return downloader.process(urls);
    }

    /**
     * gets the size of last fetched articles
     * @return size of article list
     */
    public int getArticleCount(){
        if(articles != null) {
            return articles.size();
        }
        return 0;
    }

    /**
     * get the top headlines from austria via newsapi
     * @return article list
     */
    public List<Article> getTopHeadlinesAustria() {
        NewsApi api = new NewsApi("corona", Country.at, Endpoint.TOP_HEADLINES);
        articles = new ArrayList<>();
        try {
            NewsResponse response = api.requestData();
            articles = response.getArticles();
        } catch (NewsAPIException e){
            System.out.println("An error occurred while fetching articles: " + e.getMessage());
        }

        return articles;
    }

    /**
     * returns all articles that do contain "bitcoin"
     * in their title from newsapi
     * @return filtered list
     */
    public List<Article> getAllNewsBitcoin() {
        NewsApi api = new NewsApi("bitcoin", Endpoint.EVERYTHING);
        articles = new ArrayList<>();
        try {
            NewsResponse response = api.requestData();
            articles = response.getArticles();
        } catch (NewsAPIException e){
            System.out.println("An error occurred while fetching articles: " + e.getMessage());
        }

        return articles;
    }

    public String getProviderWithMostArticles() throws NewsAPIException {
        if(articles == null)
            throw new NewsAPIException("Load data first");

        return articles.stream()
                .map(Article::getSource)
                .collect(Collectors.groupingBy(Source::getName))
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(o -> o.getValue().size()))
                .map(stringListEntry -> stringListEntry.getKey() + " " + stringListEntry.getValue().size())
                .orElseThrow();
    }

    public String getLongestNameOfAuthors() throws NewsAPIException {
        if(articles == null)
            throw new NewsAPIException("Load data first");

        return articles.stream()
                .map(Article::getAuthor)
                .filter(Objects::nonNull)
                .max(Comparator.comparing(String::length))
                .orElseThrow();
    }

    public long getCountArticlesNYTimes() throws NewsAPIException{
        if(articles == null)
            throw new NewsAPIException("Load data first");

       return articles.stream()
                .collect(Collectors.groupingBy(article -> article.getSource().getName()))   // aggregate to map - Map<sourcename, list<Article>>
                .entrySet() // obtain entrySet from map to do some more operations
                .stream()   // convert to stream again
                .filter(x -> "New York Times".equals(x.getKey()))   // filter by Map key
                .flatMap(x -> x.getValue().stream())    // flatten the filtered List<List<Article>> to a simple List<Article>
                .count();
    }

    public List<Article> getArticlesShorterThan(int length) throws NewsAPIException{
        if(articles == null)
            throw new NewsAPIException("Load data first");

        return articles.stream()
                .filter(article -> article.getTitle().length() <= length)
                .collect(Collectors.toList());
    }

    public List<Article> sortArticlesByContentLength() throws NewsAPIException{
        if(articles == null)
            throw new NewsAPIException("Load data first");

        return articles.stream()
                .sorted( (a1, a2) -> {
                    if(a1.getDescription().length() == a2.getDescription().length()){
                        return a1.getDescription().compareTo(a2.getDescription());
                    } else if (a1.getDescription().length() > a2.getDescription().length()) {
                        return 1;
                    } else {
                        return -1;
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * filters a given article list based on a query
     * @param query to filter by
     * @param articles  list to filter
     * @return filtered list
     */
    private static List<Article> filterList(String query, List<Article> articles){
        List<Article> filtered = new ArrayList<>();
        for(Article i : articles){
            if(i.getTitle().toLowerCase().contains(query)){
                filtered.add(i);
            }
        }
        return filtered;
    }


}
