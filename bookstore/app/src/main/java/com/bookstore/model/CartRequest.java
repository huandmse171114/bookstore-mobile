package com.bookstore.model;

public class CartRequest {
    private String product;
    private int qty;
    private String user;

    public CartRequest(String product, int qty, String user) {
        this.product = product;
        this.qty = qty;
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public int getQty() {
        return qty;
    }

    public String getUser() {
        return user;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
