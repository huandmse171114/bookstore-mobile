package com.bookstore.api;

import com.bookstore.model.OrderCreateRequest;
import com.bookstore.model.OrderCreateResponse;
import com.bookstore.model.OrderItemsGetResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OrderItemApi {

    @GET("/api/order-items")
    Call<OrderItemsGetResponse> getAll();

}
