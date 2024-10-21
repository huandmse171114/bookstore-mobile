package com.bookstore.model;

import java.util.List;

public class OrderCreateRequest {
    private List<OrderItem> orderItems;
    private ShippingAddress shippingAddress;
    private String paymentMethod;
    private String paymentBill;

    public OrderCreateRequest(List<OrderItem> orderItems, ShippingAddress shippingAddress, String paymentMethod, String paymentBill) {
        this.orderItems = orderItems;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.paymentBill = paymentBill;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentBill() {
        return paymentBill;
    }

    public void setPaymentBill(String paymentBill) {
        this.paymentBill = paymentBill;
    }
}