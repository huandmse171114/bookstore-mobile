package com.bookstore.contract;

import android.content.Context;

import com.bookstore.model.CartItem;
import com.bookstore.model.CartItemResponse;
import com.bookstore.model.CartResponse;

import java.util.List;

public interface CartContract {

    interface View {
        Context getApplicationContext();
        void showToastMessage(String message);
        void updateCartItemsRecyclerView(List<CartItemResponse> data);
        void updateCartItemsRecyclerView2(List<CartItem> data);
    }

    interface Presenter {
        List<CartItemResponse> getCartItems();

    }

    interface Model {

    }

}