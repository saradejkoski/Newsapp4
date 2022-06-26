package at.ac.fhcampuswien.models;

import java.util.Locale;

public class Article {
    private String author;
    private String title;
    private Source source;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    /*
        Build Pattern.
     */
    // Builder is passed to constructor of Article.
    private Article(Builder builder) {
        this.author = builder.author;
        this.title = builder.title;
        this.source = builder.source;
        this.description = builder.description;
        this.url = builder.url;
        this.urlToImage = builder.urlToImage;
        this.publishedAt = builder.publishedAt;
        this.content = builder.content;
    }

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


    // Builder class.
    public static class Builder {
        private String author;
        private String title;
        private Source source;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;

        public Builder(){}

        // Each function needs to return the Builder itself.
        public Builder author(String author) {
            this.author = author;
            return this;
        }
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        public Builder source(Source source) {
            this.source = source;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder url(String url) {
            this.url = url;
            return this;
        }
        public Builder urlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
            return this;
        }
        public Builder publishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
            return this;
        }
        public Builder content(String content) {
            this.content = content;
            return this;
        }

        // build() returns a new object of Article.
        public Article build(){
            return new Article(this);
        }
    }
}