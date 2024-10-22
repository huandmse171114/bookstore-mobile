
package com.bookstore.contract;
import com.bookstore.api.CartApiService;
import com.bookstore.model.BookDetail;
import com.bookstore.model.CartRequest;
import com.bookstore.model.CartResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailPresenterImpl implements ProductDetailPresenter {
    private ProductDetailContract view;
    private CartApiService apiService;

    public ProductDetailPresenterImpl(ProductDetailContract view, CartApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void addToCart(int bookId) {
        view.showLoading();
        CartRequest cartRequest = new CartRequest(bookId, 1);
        apiService.addToCart(cartRequest).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    CartResponse cartResponse = response.body();
                    if (cartResponse.isSuccess()) {
                        view.showAddToCartSuccess();
                    } else {
                        view.showAddToCartError(cartResponse.getMessage());
                    }
                } else {
                    view.showAddToCartError("Không thể thêm vào giỏ hàng");
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                view.hideLoading();
                view.showAddToCartError("Lỗi kết nối: " + t.getMessage());
            }
        });
    }
}