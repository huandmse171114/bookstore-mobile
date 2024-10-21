package com.bookstore.api;

import com.bookstore.model.CartRequest;
import com.bookstore.model.CartResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface CartApiService {

    @GET("/store/api/v1/customer/{username}/cart-details")
    Call<List<CartItem>> getCartDetails(@Path("username") String username);
    @POST("/api/order-items")
    Call<CartResponse> addToCart(@Body CartRequest cartRequest);
}
