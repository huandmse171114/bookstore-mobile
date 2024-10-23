package com.bookstore.contract;

import android.content.Context;

import com.bookstore.model.OrderItemData;

import java.util.List;

public interface OrderPreviewContract {

    interface View {
        void showToastMessage(String message);
        void updateOrderItemRecyclerView(List<OrderItemData> data);
        Context getApplicationContext();
    }

    interface Presenter {
        List<OrderItemData> getOrderItems();
    }

    interface Model {

    }

}
