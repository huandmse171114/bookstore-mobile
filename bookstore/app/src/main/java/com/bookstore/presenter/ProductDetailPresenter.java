package com.bookstore.presenter;

import com.bookstore.MyApplication;
import com.bookstore.contract.ProductDetailContract;
import com.bookstore.model.CartAddRequest;
import com.bookstore.model.CartAddResponse;

public class ProductDetailPresenter implements ProductDetailContract.Presenter,
        ProductDetailContract.Model.OnFinishedListener {

    private ProductDetailContract.View view;
    private ProductDetailContract.Model model;

    public ProductDetailPresenter(ProductDetailContract.View view, ProductDetailContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void addToCart(String bookId) {
        if (view != null) {
            view.showLoading();
            MyApplication app = (MyApplication) view.getMyApplicationContext();
            CartAddRequest request = new CartAddRequest(bookId, 1, app.getUserId());
            model.addToCart(request, this);
        }
    }

    @Override
    public void onAddToCartSuccess(CartAddResponse response) {
        if (view != null) {
            view.hideLoading();
            view.showAddToCartSuccess();
        }
    }

    @Override
    public void onAddToCartError(String message) {
        if (view != null) {
            view.hideLoading();
            view.showAddToCartError(message);
        }
    }

    @Override
    public void onNetworkError(String error) {
        if (view != null) {
            view.hideLoading();
            view.showAddToCartError(error);
        }
    }

    @Override
    public void onDestroy() {
        model.dispose();
        view = null;
    }
}