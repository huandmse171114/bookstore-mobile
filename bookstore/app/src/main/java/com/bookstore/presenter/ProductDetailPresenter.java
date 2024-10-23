package com.bookstore.presenter;

import com.bookstore.MyApplication;
import com.bookstore.api.CartApiService;
import com.bookstore.contract.ProductDetailContract;
import com.bookstore.model.CartAddRequest;
import com.bookstore.model.CartAddResponse;
import com.bookstore.model.CartRequest;
import com.bookstore.model.CartResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {
    private ProductDetailContract.View view;
    private CartApiService apiService;

    public ProductDetailPresenter(ProductDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void addToCart(String bookId) {
        view.showLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bookstore-api-nodejs.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(CartApiService.class);
        MyApplication app = (MyApplication) view.getMyApplicationContext();
        CartAddRequest cartRequest = new CartAddRequest(bookId, 1, app.getUserId());
        apiService.addToCart(cartRequest).enqueue(new Callback<CartAddResponse>() {
            @Override
            public void onResponse(Call<CartAddResponse> call, Response<CartAddResponse> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    CartAddResponse cartResponse = response.body();
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
            public void onFailure(Call<CartAddResponse> call, Throwable t) {
                view.hideLoading();
                view.showAddToCartError("Lỗi kết nối: " + t.getMessage());
            }
        });
    }
}
