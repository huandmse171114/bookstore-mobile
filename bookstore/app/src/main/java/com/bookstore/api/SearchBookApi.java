package com.bookstore.api;

import com.bookstore.model.SearchBookResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchBookApi {
    @GET("/api/products")
    Call<SearchBookResponse> getPopularBooks(@Query("brand") String query);
}
