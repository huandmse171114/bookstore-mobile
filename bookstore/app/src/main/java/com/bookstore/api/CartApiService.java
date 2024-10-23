package com.bookstore.api;

import com.bookstore.model.CartRequest;
import com.bookstore.model.CartResponse;
import com.bookstore.model.OrderItemsGetResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface CartApiService {

    @GET("/api/order-items")
    Call<CartResponse> getAll();

    @GET("/api/order-items/{id}")
    Call<OrderItemsGetResponse> getUserItems(@Path("id") String id);

    @POST("/api/order-items")
    Call<CartResponse> addToCart(@Body CartRequest cartRequest);
}
