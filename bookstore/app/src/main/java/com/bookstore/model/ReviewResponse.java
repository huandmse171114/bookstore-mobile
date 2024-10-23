package com.bookstore.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
public class ReviewResponse {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<UserReview> reviews;

    @SerializedName("total")
    private int total;

    public boolean isStatus() { return status; }
    public String getMessage() { return message; }
    public List<UserReview> getReviews() { return reviews; }
    public int getTotal() { return total; }
}