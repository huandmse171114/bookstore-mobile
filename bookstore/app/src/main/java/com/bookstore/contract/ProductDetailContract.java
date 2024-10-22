package com.bookstore.contract;

import android.content.Context;

import com.bookstore.model.BookDetail;

public interface ProductDetailContract {
    void showBookDetails(BookDetail book);
    void showAddToCartSuccess();
    void showAddToCartError(String message);
    void showLoading();
    void hideLoading();
    Context getApplicationContext();
}
