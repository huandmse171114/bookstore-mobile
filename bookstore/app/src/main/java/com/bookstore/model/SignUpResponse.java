package com.bookstore.model;

public class SignUpResponse {
    private String _id;
    private String username;
    private String email;
    private boolean isAdmin;

    public SignUpResponse(String _id, String username, String email, boolean isAdmin) {
        this._id = _id;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
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
}
