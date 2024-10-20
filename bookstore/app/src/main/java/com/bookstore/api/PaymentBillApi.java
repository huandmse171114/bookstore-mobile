package com.bookstore.api;

import com.bookstore.model.PaymentBillCreateRequest;
import com.bookstore.model.PaymentBillCreateResponse;
import com.bookstore.model.SignInRequest;
import com.bookstore.model.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PaymentBillApi {
    @POST("/api/payment-bills")
    Call<PaymentBillCreateResponse> create(@Body PaymentBillCreateRequest request);
}
