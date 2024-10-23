package com.bookstore.model;

public class CartAddRequest {
    private String product;
    private int qty;
    private String user;

    public CartAddRequest(String bookId, int quantity, String userId) {
        this.product = bookId;
        this.qty = quantity;
        this.user= userId;
    }

    public String getBookId() {
        return product;
    }

    public void setBookId(String bookId) {
        this.product = bookId;
    }

    public int getQuantity() {
        return qty;
    }

    public void setQuantity(int quantity) {
        this.qty = quantity;
    }

    public String getUserId() {
        return user;
    }

    public void setUserId(String userId) {
        this.user  = userId;
    }

}
