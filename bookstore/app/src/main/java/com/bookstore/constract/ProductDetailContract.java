package com.bookstore.constract;

import com.bookstore.model.BookDetail;

public interface ProductDetailContract {
    void showBookDetails(BookDetail book);
    void showAddToCartSuccess();
    void showAddToCartError(String message);
    void showLoading();
    void hideLoading();
}
