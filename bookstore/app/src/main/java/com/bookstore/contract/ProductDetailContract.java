package com.bookstore.contract;

import android.content.Context;
import com.bookstore.model.BookDetail;
import com.bookstore.model.CartAddRequest;
import com.bookstore.model.CartAddResponse;

public interface ProductDetailContract {
    interface Model {
        interface OnFinishedListener {
            void onAddToCartSuccess(CartAddResponse response);
            void onAddToCartError(String message);
            void onNetworkError(String error);
        }

        void addToCart(CartAddRequest request, OnFinishedListener listener);
        void dispose();
    }

    interface View {
        void showBookDetails(BookDetail book);
        void showAddToCartSuccess();
        void showAddToCartError(String message);
        void showLoading();
        void hideLoading();
        Context getMyApplicationContext();
    }

    interface Presenter {
        void addToCart(String bookId);
        void onDestroy();
    }
}