package com.bookstore;

import android.app.Application;

import com.bookstore.model.CartItem;
import com.bookstore.model.CartItemResponse;
import com.bookstore.model.OrderCreateResponse;
import com.bookstore.model.OrderItemData;
import com.bookstore.model.ShippingAddress;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private static String userId;
    private static ShippingAddress shippingAddress;
    private static List<OrderItemData> orderItems = new ArrayList<>();
    private static List<CartItemResponse> cartItemResponses = new ArrayList<>();
    private static List<CartItem> cartItems = new ArrayList<>();
    private static int totalItems = 0;
    private static int totalPrice = 0;
    private static List<OrderCreateResponse> orderCreateResponses = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance()
        );
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        MyApplication.userId = userId;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        MyApplication.shippingAddress = shippingAddress;
    }

    public List<OrderItemData> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemData> orderItems) {
        if (!MyApplication.orderItems.isEmpty()) MyApplication.orderItems.clear();
        MyApplication.orderItems.addAll(orderItems);
    }

    public List<CartItemResponse> getCartItemResponses() {
        return cartItemResponses;
    }

    public void setCartItemResponses(List<CartItemResponse> cartItemResponses) {
        if (!MyApplication.cartItemResponses.isEmpty()) MyApplication.cartItemResponses.clear();
        MyApplication.cartItemResponses.addAll(cartItemResponses);
    }

    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    public static void setCartItems(List<CartItem> cartItems) {
        if (!MyApplication.cartItems.isEmpty()) MyApplication.cartItems.clear();
        MyApplication.cartItems.addAll(cartItems);
    }

    public static int getTotalItems() {
        return totalItems;
    }

    public static void setTotalItems(int totalItems) {
        MyApplication.totalItems = totalItems;
    }

    public static int getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(int totalPrice) {
        MyApplication.totalPrice = totalPrice;
    }

    public static List<OrderCreateResponse> getOrderCreateResponses() {
        return orderCreateResponses;
    }

    public static void setOrderCreateResponses(List<OrderCreateResponse> orderCreateResponses) {
        MyApplication.orderCreateResponses = orderCreateResponses;
    }

    public static void addOrderCreateResponse(OrderCreateResponse orderCreateResponse) {
        MyApplication.orderCreateResponses.add(orderCreateResponse);
    }
}