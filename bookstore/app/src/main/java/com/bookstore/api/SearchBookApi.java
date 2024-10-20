package com.bookstore.api;

import com.bookstore.view.SearchBookResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchBookApi {
    @GET("/api/products")
    Call<SearchBookResponse> getPopularBooks(@Query("q") String query);
}
