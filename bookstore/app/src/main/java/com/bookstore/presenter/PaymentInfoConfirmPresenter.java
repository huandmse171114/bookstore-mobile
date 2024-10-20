package com.bookstore.presenter;

import com.bookstore.api.OrderApi;
import com.bookstore.api.OrderItemApi;
import com.bookstore.api.PaymentBillApi;
import com.bookstore.api.RetrofitClient;
import com.bookstore.constract.PaymentInfoConfirmContract;
import com.bookstore.model.OrderCreateRequest;
import com.bookstore.model.OrderCreateResponse;
import com.bookstore.model.OrderItem;
import com.bookstore.model.OrderItemData;
import com.bookstore.model.OrderItemsGetResponse;
import com.bookstore.model.PaymentBillCreateRequest;
import com.bookstore.model.PaymentBillCreateResponse;
import com.bookstore.model.PaymentGPTResponse;
import com.bookstore.model.ShippingAddress;
import com.bookstore.model.SignInResponse;
import com.bookstore.view.PaymentInfoConfirmActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaymentInfoConfirmPresenter implements PaymentInfoConfirmContract.Presenter {
    private PaymentInfoConfirmContract.Model model;
    private PaymentInfoConfirmContract.View view;
    private Retrofit retrofit;
    private PaymentBillApi paymentBillApi;
    private OrderApi orderApi;
    private OrderItemApi orderItemApi;

    public PaymentInfoConfirmPresenter(PaymentInfoConfirmContract.View view, PaymentInfoConfirmContract.Model model) {
        this.view = view;
        this.model = model;
        retrofit = RetrofitClient.getClient();
        paymentBillApi = retrofit.create(PaymentBillApi.class);
        orderApi = retrofit.create(OrderApi.class);
        orderItemApi = retrofit.create(OrderItemApi.class);
    }


    @Override
    public String createOrder(PaymentGPTResponse gptResponse) {
        String id = "";

        PaymentBillCreateRequest request = new PaymentBillCreateRequest(
                gptResponse.getSenderName(),
                gptResponse.getSenderBank(),
                gptResponse.getSenderAccount(),
                gptResponse.getReceiverName(),
                gptResponse.getReceiverBank(),
                gptResponse.getReceiverAccount(),
                gptResponse.getDate(),
                gptResponse.getTotalAmount()
        );

        try {
             Response<PaymentBillCreateResponse> response = paymentBillApi.create(request).execute();
            if (response.isSuccessful()) {
                String paymentBillId = response.body().getData().get_id();

                List<OrderItemData> orderItemsData = getOrderItem();

                if (orderItemsData != null) {
                    List<OrderItem> orderItems = orderItemsData
                            .stream()
                            .map(item -> new OrderItem(item.getId(), item.getQty()))
                            .collect(Collectors.toList());

                    ShippingAddress defaultShippingAddress = new ShippingAddress(
                            "Minh Huan",
                            "121 La Xuan Oai Thu Duc HCM",
                            "HCM",
                            "70000",
                            "VI",
                            "0929384759"
                    );

                    OrderCreateRequest orderCreateRequest = new OrderCreateRequest(
                            orderItems,
                            defaultShippingAddress,
                            "VNPay",
                            paymentBillId
                    );

                    try {
                        Response<OrderCreateResponse> orderCreateResponse = orderApi
                                .create(orderCreateRequest).execute();

                        if (orderCreateResponse.isSuccessful() && orderCreateResponse.body() != null) {
                            view.showToastMessage("success create order");
                        }else {
                            view.showToastMessage("failed create order");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    view.showToastMessage("empty order item" + response.message());
                }

            }else {
                view.showToastMessage("fail" + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    private List<OrderItemData> getOrderItem() {

        List<OrderItemData> response = new ArrayList<>();

        try {
            Response<OrderItemsGetResponse> responseRaw = orderItemApi.getAll().execute();

            if (responseRaw.isSuccessful() && responseRaw.body() != null) {
                response = responseRaw.body().getData();
                view.showToastMessage("success get order items");
            }else {
                response = null;
                view.showToastMessage("failed get order items");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }
}
