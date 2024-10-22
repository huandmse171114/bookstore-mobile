package com.bookstore.api;

import com.bookstore.model.BookDetail;
import com.bookstore.model.ProductDetailResponse;
import com.bookstore.model.SearchBook;
import com.bookstore.model.SearchBookResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchBookApi {
    @GET("/api/products")
    Call<SearchBookResponse> getPopularBooks(@Query("keyword") String query);
    @GET("/api/products")
    Call<ProductDetailResponse> getAllBooks();
}
