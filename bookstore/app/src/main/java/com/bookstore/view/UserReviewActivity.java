package com.bookstore.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bookstore.R;
import com.bookstore.contract.BookReviewContract;
import com.bookstore.databinding.ProductDetailLayoutBinding;
import com.bookstore.model.BookReviewModel;
import com.bookstore.model.UserReview;
import com.bookstore.presenter.BookReviewPresenter;
import com.bumptech.glide.Glide;

import java.util.List;
public class UserReviewActivity extends AppCompatActivity implements BookReviewContract.View {
    private ProductDetailLayoutBinding binding;
    private BookReviewContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProductDetailLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupPresenter();
        loadData();
    }

    private void setupPresenter() {
        BookReviewContract.Model model = new BookReviewModel();
        presenter = new BookReviewPresenter(model);
        presenter.attachView(this);
    }

    private void loadData() {
        String bookId = getIntent().getStringExtra("book_id");
        if (bookId != null) {
            presenter.loadReviews(bookId);
        }
    }

    @Override
    public void showReviews(List<UserReview> reviews) {
        // This method can be used if you want to show all reviews in a list
    }

    @Override
    public void updateFirstReview(UserReview review) {
        //set cung truoc
        binding.cusName.setText("Customer Name");

        Glide.with(this)
                .load(R.drawable.logo)
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(binding.customer);

        binding.bookRatingBar.setRating(review.getRating());
        binding.des.setText(review.getComment());
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateReviewCount(int count) {
        binding.Review.setText(String.format("Review (%d)", count));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        binding = null;
    }
}
