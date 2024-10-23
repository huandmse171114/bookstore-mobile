package com.bookstore.presenter;

import com.bookstore.contract.BookReviewContract;
import com.bookstore.model.UserReview;
import java.util.List;

public class BookReviewPresenter implements BookReviewContract.Presenter {
    private BookReviewContract.View view;
    private final BookReviewContract.Model model;
    private List<UserReview> currentReviews;

    public BookReviewPresenter(BookReviewContract.Model model) {
        this.model = model;
    }

    @Override
    public void attachView(BookReviewContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadReviews(String bookId) {
        if (view != null) {
            view.showLoading();
        }

        model.getReviews(bookId, new BookReviewContract.Model.OnReviewsLoadedCallback() {
            @Override
            public void onSuccess(List<UserReview> reviews) {
                if (view != null) {
                    view.hideLoading();
                    currentReviews = reviews;
                    view.showReviews(reviews);
                    view.updateReviewCount(reviews.size());

                    if (!reviews.isEmpty()) {
                        view.updateFirstReview(reviews.get(0));
                    }
                }
            }

            @Override
            public void onError(String error) {
                if (view != null) {
                    view.hideLoading();
                    view.showError(error);
                }
            }
        });
    }

    @Override
    public void onReviewClicked(UserReview review) {
        // Handle review click if needed
    }
}