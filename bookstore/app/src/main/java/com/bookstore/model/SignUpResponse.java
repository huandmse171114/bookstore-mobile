package com.bookstore.model;

public class SignUpResponse {
    private String message; // Assume your API returns a message indicating success/failure

    // Constructor, getters, and setters
    public SignUpResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
