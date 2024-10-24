package com.bookstore.model;

import com.bookstore.api.CartApiService;
import com.bookstore.contract.ProductDetailContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailModel implements ProductDetailContract.Model {
    private CartApiService apiService;
    private Call<CartAddResponse> currentCall;

    public ProductDetailModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bookstore-api-nodejs.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(CartApiService.class);
    }

    @Override
    public void addToCart(CartAddRequest request, OnFinishedListener listener) {
        currentCall = apiService.addToCart(request);
        currentCall.enqueue(new Callback<CartAddResponse>() {
            @Override
            public void onResponse(Call<CartAddResponse> call, Response<CartAddResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CartAddResponse cartResponse = response.body();
                    if (cartResponse.isSuccess()) {
                        listener.onAddToCartSuccess(cartResponse);
                    } else {
                        listener.onAddToCartError(cartResponse.getMessage());
                    }
                } else {
                    listener.onAddToCartError("Không thể thêm vào giỏ hàng");
                }
            }

            @Override
            public void onFailure(Call<CartAddResponse> call, Throwable t) {
                if (!call.isCanceled()) {
                    listener.onNetworkError("Lỗi kết nối: " + t.getMessage());
                }
            }
        });
    }

    @Override
    public void dispose() {
        if (currentCall != null && !currentCall.isCanceled()) {
            currentCall.cancel();
        }
    }
}
