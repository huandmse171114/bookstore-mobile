package com.bookstore.api;

import com.bookstore.model.OrderCreateRequest;
import com.bookstore.model.OrderCreateResponse;
import com.bookstore.model.OrderItemsGetResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderItemApi {

    @GET("/api/order-items")
    Call<OrderItemsGetResponse> getAll();

    @GET("/api/order-items/{id}")
    Call<OrderItemsGetResponse> getUserItems(@Path("id") String id);

}
