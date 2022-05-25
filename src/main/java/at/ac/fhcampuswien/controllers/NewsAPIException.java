package at.ac.fhcampuswien.controllers;

public class NewsAPIException extends Exception{
    public NewsAPIException() {
        super();
    }
    public NewsAPIException(String message) {
        super(message);
    }
}
