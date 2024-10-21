package com.bookstore.model;

public class CartResponse {
    private String message;
    private boolean success;

    public CartResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
