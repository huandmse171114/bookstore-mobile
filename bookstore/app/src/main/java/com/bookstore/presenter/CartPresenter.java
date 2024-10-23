package com.bookstore.presenter;

import com.bookstore.MyApplication;
import com.bookstore.api.CartApiService;
import com.bookstore.api.RetrofitClient;
import com.bookstore.contract.CartContract;
import com.bookstore.model.CartItemResponse;
import com.bookstore.model.CartResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartPresenter implements CartContract.Presenter {

    private CartContract.View view;
    private CartContract.Model model;
    private CartApiService cartApiService;
    private Retrofit retrofit;

    public CartPresenter(CartContract.View view, CartContract.Model model) {
        this.view = view;
        this.model = model;
        retrofit = RetrofitClient.getClient();
        cartApiService = retrofit.create(CartApiService.class);
    }

    @Override
    public List<CartItemResponse> getCartItems() {

        List<CartItemResponse> response = new ArrayList<>();

        MyApplication app = (MyApplication) view.getApplicationContext();

        cartApiService.getUserItems(app.getUserId()).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<CartItemResponse> data = response.body().getData();
                data.addAll(data);
                data.addAll(data);
                app.setCartItemResponses(data);
                view.updateCartItemsRecyclerView(data);
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable throwable) {

            }
        });

        return response;

    }
}
