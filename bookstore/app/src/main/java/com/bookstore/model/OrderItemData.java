package com.bookstore.model;

public class OrderItemData {

    private String _id;
    private String name;
    private int qty;
    private String image;
    private int price;
    private OrderItemProduct product;

    public OrderItemData(String id, String name, int qty, String image, int price, OrderItemProduct product) {
        this._id = id;
        this.name = name;
        this.qty = qty;
        this.image = image;
        this.price = price;
        this.product = product;
    }

    public OrderItemProduct getProduct() {
        return product;
    }

    public void setProduct(OrderItemProduct product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }
}
