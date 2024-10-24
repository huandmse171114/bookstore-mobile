package com.bookstore.presenter;

import com.bookstore.MyApplication;
import com.bookstore.api.OrderApi;
import com.bookstore.api.PaymentBillApi;
import com.bookstore.api.RetrofitClient;
import com.bookstore.contract.PaymentInfoConfirmContract;
import com.bookstore.model.CartItem;
import com.bookstore.model.OrderCreateRequest;
import com.bookstore.model.OrderCreateResponse;
import com.bookstore.model.OrderItem;
import com.bookstore.model.OrderItemData;
import com.bookstore.model.PaymentBillCreateRequest;
import com.bookstore.model.PaymentBillCreateResponse;
import com.bookstore.model.PaymentGPTResponse;
import com.bookstore.model.ShippingAddress;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Response;
import retrofit2.Retrofit;

public class PaymentInfoConfirmPresenter implements PaymentInfoConfirmContract.Presenter {
    private PaymentInfoConfirmContract.Model model;
    private PaymentInfoConfirmContract.View view;
    private Retrofit retrofit;
    private PaymentBillApi paymentBillApi;
    private OrderApi orderApi;


    public PaymentInfoConfirmPresenter(PaymentInfoConfirmContract.View view, PaymentInfoConfirmContract.Model model) {
        this.view = view;
        this.model = model;
        retrofit = RetrofitClient.getClient();
        paymentBillApi = retrofit.create(PaymentBillApi.class);
        orderApi = retrofit.create(OrderApi.class);
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
                MyApplication app = (MyApplication) view.getApplicationContext();
                String userId = app.getUserId();

                List<CartItem> orderItemsData = MyApplication.getCartItems();

                if (orderItemsData != null) {
                    List<OrderItem> orderItems = orderItemsData
                            .stream()
                            .map(item -> new OrderItem(item.getProductId(), item.getQuantity()))
                            .collect(Collectors.toList());

                    ShippingAddress defaultShippingAddress = app.getShippingAddress();

                    OrderCreateRequest orderCreateRequest = new OrderCreateRequest(
                            userId,
                            orderItems,
                            defaultShippingAddress,
                            "VNPay",
                            paymentBillId
                    );

                    try {
                        Response<OrderCreateResponse> orderCreateResponse = orderApi
                                .create(orderCreateRequest).execute();

                        if (orderCreateResponse.isSuccessful() && orderCreateResponse.body() != null) {
                            MyApplication.addOrderCreateResponse(orderCreateResponse.body());
                            id = orderCreateResponse.body().toString();
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

}
