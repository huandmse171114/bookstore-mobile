package com.bookstore.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bookstore.api.CartApiService;
import com.bookstore.api.SearchBookApi;
import com.bookstore.contract.ProductDetailContract;
import com.bookstore.databinding.ProductDetailLayoutBinding;
import com.bookstore.model.BookDetail;
import com.bookstore.model.OtherProductsAdapter;
import com.bookstore.model.ProductDetailResponse;
import com.bookstore.model.TabAdapter;
import com.bookstore.presenter.ProductDetailPresenter;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayoutMediator;
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
    private List<BookDetail> products = new ArrayList<>();
    private SearchBookApi apiService;
    private CartApiService cartApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProductDetailLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ProductDetailPresenter(this);
        initViews();
//        setupPresenter();
        setupTabLayout();
        setupOtherProducts();
        displayBookDetails();
        setupListeners();
    }

    private void initViews() {
        otherProductsAdapter = new OtherProductsAdapter(products);
        binding.rvOtherProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvOtherProducts.setAdapter(otherProductsAdapter);
    }

    private void setupPresenter() {
        // Initialize your presenter here
//         presenter = new ProductDetailPresenterImpl(this);
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

        apiService = retrofit.create(SearchBookApi.class);
        apiService.getAllBooks().enqueue(new Callback<ProductDetailResponse>() {
            @Override
            public void onResponse(Call<ProductDetailResponse> call, Response<ProductDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.clear();
                    products.addAll(response.body().getProducts());
                    otherProductsAdapter.notifyDataSetChanged();
                } else {
                    showError("Không thể tải danh sách sản phẩm khác");
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResponse> call, Throwable t) {
                showError("Lỗi kết nối: " + t.getMessage());
            }
        });
    }

    private void displayBookDetails() {
        Intent intent = getIntent();
        String bookImage = intent.getStringExtra("book_image");
        String bookTitle = intent.getStringExtra("book_title");
        float price = intent.getFloatExtra("book_price", 0f);

        if (bookImage != null && !bookImage.isEmpty()) {
            Glide.with(this).load(bookImage).into(binding.bookImgView);
        }
        binding.titleName.setText(bookTitle);
        binding.price.setText(String.format("%.0f VND", price));
    }

    private void setupListeners() {
        binding.back.setOnClickListener(v -> navigateToHomePage());
        binding.buttonAddToCart.setOnClickListener(v -> addToCart());
    }

    private void navigateToHomePage() {
        startActivity(new Intent(this, HomePageActivity.class));
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
        showToast("Sách đã được thêm vào giỏ hàng thành công!");
    }

    @Override
    public void showAddToCartError(String message) {
        showError("Lỗi khi thêm vào giỏ hàng: " + message);
    }

    @Override
    public void showLoading() {
        // Implement loading UI
    }

    @Override
    public void hideLoading() {
        // Hide loading UI
    }

    @Override
    public Context getMyApplicationContext() {
        return getApplicationContext();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}