package com.bookstore.api;

import com.bookstore.model.OrderCreateRequest;
import com.bookstore.model.OrderCreateResponse;
import com.bookstore.model.SignInRequest;
import com.bookstore.model.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderApi {

    @POST("/api/orders")
    Call<OrderCreateResponse> create(@Body OrderCreateRequest request);
}
