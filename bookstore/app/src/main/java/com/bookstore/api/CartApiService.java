package com.bookstore.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CartApiService {

    @GET("/api/order-items")
    Call<List<CartItem>> getAllCartItems();
}
