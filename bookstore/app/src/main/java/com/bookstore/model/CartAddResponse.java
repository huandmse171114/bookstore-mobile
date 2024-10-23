package com.bookstore.model;

import java.util.List;

public class CartAddResponse {
    private String message;
    private boolean success;
    private CartAddItemResponse data;

    public CartAddResponse(String message, boolean success, CartAddItemResponse data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CartAddItemResponse getData() {
        return data;
    }

    public void setData(CartAddItemResponse data) {
        this.data = data;
    }
}
