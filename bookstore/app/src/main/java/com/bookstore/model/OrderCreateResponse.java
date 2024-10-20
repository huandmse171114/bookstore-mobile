package com.bookstore.model;

import java.util.List;

public class OrderCreateResponse {
    private String user;
    private String paymentBill;
    private List<OrderItemResponse> orderItems;
    private ShippingAddress shippingAddress;
}
