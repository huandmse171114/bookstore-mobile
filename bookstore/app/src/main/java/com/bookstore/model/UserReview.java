package com.bookstore.model;

public class UserReview {
    private String id;
    private String user;
    private int rating;
    private String comment;

    public UserReview(String id, String user, int rating, String comment) {
        this.id = id;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
