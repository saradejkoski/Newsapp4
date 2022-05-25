package at.ac.fhcampuswien.models;

public class Article {
    private String author;
    private String title;
    private Source source;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public Article(String author, String title){
        this.author = author;
        this.title = title;
    }

    public Source getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return
                "Title: " + getTitle() + "\n" +
                        "Author: " + getAuthor() + "\n"
                        //+ "; Source: " + getSource().getName() + "\n"
                        + "Desc (length): " + getDescription().length() + "\n Desc: " + getDescription() + "\n";
    }
}