package com.bookstore.model;

public class Book {
    private int id;
    private String title;
    private float price;
    private int imageResourceId;

    public Book(int id, String title, float price, int imageResourceId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public Book(String title, float price, int imageResourceId) {
        this.title = title;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    // Getter methods for each field
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    // Setter methods for each field
    public int setId(int id) {
        this.id = id;
        return id;
    }

    public String setTitle(String title) {
        this.title = title;
        return title;
    }

    public float setPrice(float price) {
        this.price = price;
        return price;
    }

    public int setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
        return imageResourceId;
    }

}
