package com.bookstore.model;

public class CartItemResponse {
    private String user;
    private String name;
    private int qty;
    private String image;
    private int price;
    private String product;
    private String _id;
    private String createdAt;
    private String updatedAt;
    private int __v;

    public CartItemResponse(String user, String name, int qty, String image, int price, String product, String _id, String createdAt, String updatedAt, int __v) {
        this.user = user;
        this.name = name;
        this.qty = qty;
        this.image = image;
        this.price = price;
        this.product = product;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public String getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getProduct() {
        return product;
    }

    public String get_id() {
        return _id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
