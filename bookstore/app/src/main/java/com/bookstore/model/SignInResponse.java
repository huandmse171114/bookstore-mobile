package com.bookstore.model;

public class SignInResponse {
    private String token; // Assume your API returns a token
    private String message; // Optional message field

    // Constructor, getters, and setters
    public SignInResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
