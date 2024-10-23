package com.bookstore.model;

import com.bookstore.contract.BookReviewContract;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class BookReviewModel implements BookReviewContract.Model {
    private static final String BASE_URL = "https://bookstore-api-nodejs.onrender.com";
    private final BookApiService apiService;

    public BookReviewModel() {
        // Setup Retrofit
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(BookApiService.class);
    }

    private interface BookApiService {
        @GET("api/products/{id}/reviews")
        Call<ReviewResponse> getReviews(
                @Path("bookId") String bookId
        );
    }

    @Override
    public void getReviews(String bookId, final OnReviewsLoadedCallback callback) {
        apiService.getReviews(bookId)
                .enqueue(new Callback<ReviewResponse>() {
                    @Override
                    public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ReviewResponse reviewResponse = response.body();
                            if (reviewResponse.isStatus()) {
                                callback.onSuccess(reviewResponse.getReviews());
                            } else {
                                callback.onError(reviewResponse.getMessage());
                            }
                        } else {
                            callback.onError("Failed to load reviews");
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewResponse> call, Throwable t) {
                        callback.onError(t.getMessage());
                    }
                });
    }
}