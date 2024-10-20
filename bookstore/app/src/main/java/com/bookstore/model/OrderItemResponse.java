package com.bookstore.model;

public class OrderItemResponse {
    private String id;
    private String name;
    private String image;
    private int qty;
    private int price;
    private String product;

    public OrderItemResponse(String id, String name, String image, int qty, int price, String product) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.qty = qty;
        this.price = price;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
