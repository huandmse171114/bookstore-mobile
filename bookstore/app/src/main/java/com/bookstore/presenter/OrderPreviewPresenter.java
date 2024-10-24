package com.bookstore.presenter;

import com.bookstore.MyApplication;
import com.bookstore.api.OrderItemApi;
import com.bookstore.api.RetrofitClient;
import com.bookstore.contract.OrderPreviewContract;
import com.bookstore.model.OrderItemData;
import com.bookstore.model.OrderItemsGetResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderPreviewPresenter implements OrderPreviewContract.Presenter {

    private OrderPreviewContract.View view;
    private OrderPreviewContract.Model model;

    public OrderPreviewPresenter(OrderPreviewContract.View view, OrderPreviewContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getOrderItems() {
        model.getOrderItems(this, (MyApplication) view.getApplicationContext());
    }

    @Override
    public void showToastMessage(String message) {
        view.showToastMessage(message);
    }

    @Override
    public void updateOrderItemRecyclerView(List<OrderItemData> data) {
        view.updateOrderItemRecyclerView(data);
    }
}
