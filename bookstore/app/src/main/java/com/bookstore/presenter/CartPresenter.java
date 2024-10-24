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

    public CartPresenter(CartContract.View view, CartContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getCartItems() {
        model.getCartItems(this, (MyApplication) view.getApplicationContext());
    }

    @Override
    public void showViewMessage(String message) {
        view.showToastMessage(message);
    }

    @Override
    public void updateCartItemsRecyclerView(List<CartItemResponse> data) {
        view.updateCartItemsRecyclerView(data);
    }
}
