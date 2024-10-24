package com.bookstore.model;

import java.util.List;

public class OrderCreateResponse {
    private String user;
    private String paymentBill;
    private List<String> orderItems;
    private ShippingAddress shippingAddress;

    public OrderCreateResponse(String user, String paymentBill, List<String> orderItems, ShippingAddress shippingAddress) {
        this.user = user;
        this.paymentBill = paymentBill;
        this.orderItems = orderItems;
        this.shippingAddress = shippingAddress;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPaymentBill() {
        return paymentBill;
    }

    public void setPaymentBill(String paymentBill) {
        this.paymentBill = paymentBill;
    }

    public List<String> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<String> orderItems) {
        this.orderItems = orderItems;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
