package com.bookstore.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CartApiService {

    @GET("/store/api/v1/customer/{username}/cart-details")
    Call<List<CartItem>> getCartDetails(@Path("username") String username);
}
