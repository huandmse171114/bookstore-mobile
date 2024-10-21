package com.bookstore.model;

public class OrderItemView {

    private String imageURL;
    private String name;
    private int quantity;
    private int price;

    public OrderItemView(String imageURL, String name, int quantity, int price) {
        this.imageURL = imageURL;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
