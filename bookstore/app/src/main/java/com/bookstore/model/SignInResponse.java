package com.bookstore.model;

public class SignInResponse {
    private String _id;
    private String username;
    private String email;
    private boolean isAdmin;
    private String token;
    private String message; // Optional field if the API response contains a message field

    public SignInResponse(String _id, String username, String email, boolean isAdmin, String token, String message) {
        this._id = _id;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
        this.token = token;
        this.message = message;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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