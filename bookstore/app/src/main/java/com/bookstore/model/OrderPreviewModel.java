package com.bookstore.model;

import com.bookstore.MyApplication;
import com.bookstore.api.CartApiService;
import com.bookstore.api.OrderItemApi;
import com.bookstore.api.RetrofitClient;
import com.bookstore.contract.OrderPreviewContract;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderPreviewModel implements OrderPreviewContract.Model {
    private OrderItemApi orderItemApi;
    private Retrofit retrofit;

    public OrderPreviewModel() {
        retrofit = RetrofitClient.getClient();
        orderItemApi = retrofit.create(OrderItemApi.class);
    }

    @Override
    public void getOrderItems(OrderPreviewContract.Presenter presenter, MyApplication app) {
        orderItemApi.getUserItems(MyApplication.getUserId()).enqueue(new Callback<OrderItemsGetResponse>() {
            @Override
            public void onResponse(Call<OrderItemsGetResponse> call, Response<OrderItemsGetResponse> response) {
                List<OrderItemData> data = response.body().getData();
                app.setOrderItems(data);
                presenter.updateOrderItemRecyclerView(data);

            }

            @Override
            public void onFailure(Call<OrderItemsGetResponse> call, Throwable throwable) {
                presenter.showToastMessage("Get failed");
            }
        });
    }
}
