package com.bookstore.model;

import android.content.Context;

import com.bookstore.MyApplication;
import com.bookstore.api.CartApiService;
import com.bookstore.api.RetrofitClient;
import com.bookstore.contract.CartContract;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartModel implements CartContract.Model {
    private CartApiService cartApiService;
    private Retrofit retrofit;

    public CartModel() {
        retrofit = RetrofitClient.getClient();
        cartApiService = retrofit.create(CartApiService.class);
    }

    @Override
    public void getCartItems(CartContract.Presenter presenter, MyApplication app) {

        cartApiService.getUserItems(MyApplication.getUserId()).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<CartItemResponse> data = response.body().getData();
                app.setCartItemResponses(data);
                presenter.updateCartItemsRecyclerView(data);
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable throwable) {
                presenter.showViewMessage("Get user's cart failed");
            }
        });
    }
}
