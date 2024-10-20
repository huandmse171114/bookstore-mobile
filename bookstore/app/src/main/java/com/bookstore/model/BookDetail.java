package com.bookstore.model;

public class BookDetail {
    private String title;
    private String author;
    private int price;
    private int coverResourceId;

    public BookDetail(String title, String author, int price, int coverResourceId) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.coverResourceId = coverResourceId;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPrice() { return price; }
    public int getCoverResourceId() { return coverResourceId; }
}
