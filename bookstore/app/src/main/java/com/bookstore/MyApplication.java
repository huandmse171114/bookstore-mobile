package com.bookstore;

import android.app.Application;

import com.bookstore.model.OrderItemData;
import com.bookstore.model.ShippingAddress;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private static String userId = "671226027478771599feaedf";
    private static ShippingAddress shippingAddress;
    private static List<OrderItemData> orderItems = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance()
        );
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
}