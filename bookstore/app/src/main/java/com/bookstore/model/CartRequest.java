package com.bookstore.model;

public class CartRequest {
    private String bookId;
    private int quantity;
    private String userId;

    public CartRequest(String bookId, int quantity, String userId) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
