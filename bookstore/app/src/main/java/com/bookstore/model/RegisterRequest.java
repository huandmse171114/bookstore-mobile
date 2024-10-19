package com.bookstore.model;

public class RegisterRequest {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String role;

    // Constructor with role parameter
    public RegisterRequest(String username, String fullname, String email, String password, String role) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        // Set default role to "CUSTOMER" if not provided
        this.role = (role == null || role.isEmpty()) ? "CUSTOMER" : role;
    }

    // Constructor without role parameter (defaults to "CUSTOMER")
    public RegisterRequest(String username, String fullname, String email, String password) {
        this(username, fullname, email, password, "CUSTOMER");
    }

    // Getters and Setters (if needed)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}