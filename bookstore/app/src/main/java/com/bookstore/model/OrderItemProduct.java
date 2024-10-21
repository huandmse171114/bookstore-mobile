package com.bookstore.model;

public class OrderItemProduct {
    private String _id;

    public OrderItemProduct(String id) {
        this._id = id;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }
}
