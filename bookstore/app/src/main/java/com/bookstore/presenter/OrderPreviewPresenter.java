package com.bookstore.presenter;

import com.bookstore.MyApplication;
import com.bookstore.api.OrderItemApi;
import com.bookstore.api.RetrofitClient;
import com.bookstore.contract.OrderPreviewContract;
import com.bookstore.model.OrderItemData;
import com.bookstore.model.OrderItemsGetResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderPreviewPresenter implements OrderPreviewContract.Presenter {

    private OrderPreviewContract.View view;
    private OrderPreviewContract.Model model;
    private OrderItemApi orderItemApi;
    private Retrofit retrofit;

    public OrderPreviewPresenter(OrderPreviewContract.View view, OrderPreviewContract.Model model) {
        this.view = view;
        this.model = model;
        retrofit = RetrofitClient.getClient();
        orderItemApi = retrofit.create(OrderItemApi.class);
    }

    @Override
    public List<OrderItemData> getOrderItems() {
        List<OrderItemData> response = new ArrayList<>();

        MyApplication app = (MyApplication) view.getApplicationContext();

        orderItemApi.getUserItems(app.getUserId()).enqueue(new Callback<OrderItemsGetResponse>() {
            @Override
            public void onResponse(Call<OrderItemsGetResponse> call, Response<OrderItemsGetResponse> response) {
                List<OrderItemData> data = response.body().getData();
                app.setOrderItems(data);
                view.updateOrderItemRecyclerView(data);

            }

            @Override
            public void onFailure(Call<OrderItemsGetResponse> call, Throwable throwable) {
                view.showToastMessage("Get failed");
            }
        });



        return response;
    }
}
