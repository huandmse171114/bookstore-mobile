package com.bookstore.model;

public class SignUpRequest {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String confirmPassword;
    private String role;

    public SignUpRequest(String username, String fullname, String email, String password, String confirmPassword, String role) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = "CUSTOMER";
    }

    // Getters and setters (if needed)
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
