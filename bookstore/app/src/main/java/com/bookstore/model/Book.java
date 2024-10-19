package com.bookstore.model;

public class Book {
    private int id;
    private String title;
    //    private String categoryName;
//    private int categoryId;
    private int imageResourceId;

    public Book(int id, String title, int imageResourceId) {
        this.id = id;
        this.title = title;
//        this.categoryName = categoryName;

        this.imageResourceId = imageResourceId;
    }

    // Getter methods for each field
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public String setTitle(String title) {
        this.title = title;
        return title;
    }

    public int setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
        return imageResourceId;
    }

}
