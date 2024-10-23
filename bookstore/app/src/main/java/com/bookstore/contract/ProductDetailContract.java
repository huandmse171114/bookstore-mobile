package com.bookstore.contract;

import android.content.Context;

import com.bookstore.model.BookDetail;

public interface ProductDetailContract {
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
    }
}
