package com.bookstore.model;

import java.util.List;

public class CartResponse {
    private String message;
    private boolean success;
    private List<CartItemResponse> data;

    public CartResponse(String message, boolean success, List<CartItemResponse> data) {
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

    public List<CartItemResponse> getData() {
        return data;
    }

    public void setData(List<CartItemResponse> data) {
        this.data = data;
    }
}
