package com.bookstore.contract;
import java.util.List;
import com.bookstore.model.UserReview;

public interface BookReviewContract {
    interface View {
        void showReviews(List<UserReview> reviews);
        void showLoading();
        void hideLoading();
        void showError(String message);
        void updateReviewCount(int count);
        void updateFirstReview(UserReview review);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void loadReviews(String bookId);
        void onReviewClicked(UserReview review);
    }

    interface Model {
        void getReviews(String bookId, OnReviewsLoadedCallback callback);
        interface OnReviewsLoadedCallback {
            void onSuccess(List<UserReview> reviews);
            void onError(String error);
        }
    }
}