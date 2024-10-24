package com.bookstore.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bookstore.api.SearchBookApi;
import com.bookstore.contract.ProductDetailContract;
import com.bookstore.databinding.ProductDetailLayoutBinding;
import com.bookstore.model.BookDetail;
import com.bookstore.adapter.OtherProductsAdapter;
import com.bookstore.model.ProductDetailModel;
import com.bookstore.model.ProductDetailResponse;
import com.bookstore.model.TabAdapter;
import com.bookstore.presenter.ProductDetailPresenter;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailContract.View {

    private ProductDetailLayoutBinding binding;
    private ProductDetailContract.Presenter presenter;
    private OtherProductsAdapter otherProductsAdapter;
    private final List<BookDetail> products = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProductDetailLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
        setupPresenter();
        setupTabLayout();
        setupOtherProducts();
        displayBookDetails();
        setupListeners();
    }

    private void initViews() {
        otherProductsAdapter = new OtherProductsAdapter(products, this::onOtherProductClick);
        binding.rvOtherProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvOtherProducts.setAdapter(otherProductsAdapter);
    }

    private void setupPresenter() {
        // Initialize your presenter here
        ProductDetailContract.Model model = new ProductDetailModel();
        presenter = new ProductDetailPresenter(this, model);

    }

    private void setupTabLayout() {
        TabAdapter tabAdapter = new TabAdapter(this);
        binding.viewPager.setAdapter(tabAdapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setText(TabAdapter.TAB_TITLES[position])
        ).attach();
    }

    private void setupOtherProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bookstore-api-nodejs.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchBookApi apiService = retrofit.create(SearchBookApi.class);
        apiService.getAllBooks().enqueue(new Callback<ProductDetailResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<ProductDetailResponse> call, @NonNull Response<ProductDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.clear();
                    products.addAll(response.body().getProducts());
                    otherProductsAdapter.notifyDataSetChanged();
                } else {
                    showError("Không thể tải danh sách sản phẩm khác");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductDetailResponse> call, @NonNull Throwable t) {
                showError("Lỗi kết nối: " + t.getMessage());
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void displayBookDetails() {
        Intent intent = getIntent();
        String bookImage = intent.getStringExtra("book_image");
        String bookTitle = intent.getStringExtra("book_title");
        float price = intent.getFloatExtra("book_price", 0f);

        NumberFormat numberFormat = NumberFormat.getInstance();

        if (bookImage != null && !bookImage.isEmpty()) {
            Glide.with(this).load(bookImage).into(binding.bookImgView);
        }
        binding.titleName.setText(bookTitle);
        binding.price.setText(numberFormat.format(price) +" VND");
    }

    private void setupListeners() {
        binding.back.setOnClickListener(v -> navigateToHomePage());
        binding.btnCartToolbar.setOnClickListener(v -> navigateToViewCart());
        binding.buttonAddToCart.setOnClickListener(v -> addToCart());
    }

    private void navigateToHomePage() {
        startActivity(new Intent(this, HomePageActivity.class));
        finish();
    }
    private void navigateToViewCart() {
        startActivity(new Intent(this, CartActivity.class));
        finish();
    }

    private void addToCart() {
        // Implement add to cart logic
        String bookId = getIntent().getStringExtra("book_id");
        if (bookId != null) {
            presenter.addToCart(bookId);
        } else {
            showError("Không tìm thấy thông tin sách");
        }
    }

    @Override
    public void showBookDetails(BookDetail book) {
        // Implement if needed
    }

    @Override
    public void showAddToCartSuccess() {
        showToast();
    }

    private void onOtherProductClick(BookDetail book) {
        // Lưu lại thông tin sản phẩm mới được chọn
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("book_id", book.getId());
        intent.putExtra("book_title", book.getName());
        intent.putExtra("book_price", book.getPrice());
        intent.putExtra("book_image", book.getImage());
        startActivity(intent);
        finish(); // Kết thúc activity hiện tại để người dùng trở về trang chi tiết sản phẩm mới
    }

    @Override
    public void showAddToCartError(String message) {
        showError(message + ": Vui lòng đăng nhập trước khi thêm vào giỏ hàng");
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        // Show loading UI
        binding.progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        // Hide loading UI
        binding.progressBar.setVisibility(android.view.View.GONE);
    }

    @Override
    public Context getMyApplicationContext() {
        return getApplicationContext();
    }

    private void showToast() {
        Toast.makeText(this, "Sách đã được thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}