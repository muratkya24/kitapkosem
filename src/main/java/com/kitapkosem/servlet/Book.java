package com.kitapkosem.servlet;

public class Book {
    private int id;
    private String title;
    private String author;
    private String description;
    private int publishYear;

    public Book(int id, String title, String author, String description, int publishYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.publishYear = publishYear;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public int getPublishYear() { return publishYear; }
}
