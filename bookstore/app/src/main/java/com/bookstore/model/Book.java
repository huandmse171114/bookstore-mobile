package com.bookstore.model;

import android.speech.RecognizerIntent;

import java.net.URI;
import java.util.Date;
import java.util.List;

public class Book {
    private String _id;
    private String name;
    private String image;
    private String brand;
    private int quantity;
    private String category;
    private String description;
    private float rating;
    private int numReviews;
    private float price;
    private int countInStock;
    private List<Reviews> reviews;
    private Date createdAt;
    private Date updatedAt;

    // Constructor
    public Book(String id, String name, String image, String brand, int quantity,
                String category, String description, float rating, int numReviews,
                float price, int countInStock, List<Reviews> reviews, Date createdAt,
                Date updatedAt) {
        this._id = id;
        this.name = name;
        this.image = image;
        this.brand = brand;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
        this.rating = rating;
        this.numReviews = numReviews;
        this.price = price;
        this.countInStock = countInStock;
        this.reviews = reviews;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;



    }
  
    // Getters and Setters
    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCountInStock() {
        return countInStock;
    }

    public void setCountInStock(int countInStock) {
        this.countInStock = countInStock;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}