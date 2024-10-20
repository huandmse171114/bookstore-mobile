package com.bookstore.model;

import java.util.Date;
import java.util.List;

public class Reviews
{
    private String _id;
    private String name;
    private float rating;
    private String comment;
    private String user;
    private Date createdAt;
    private Date updatedAt;

    public Reviews(String _id, String name, float rating,
                   String comment, String user,
                   Date createdAt, Date updatedAt) {
        this._id = _id;
        this.name = name;
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
