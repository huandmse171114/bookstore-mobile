package com.bookstore.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bookstore.api.SearchBookApi;
import com.bookstore.contract.ProductDetailContract;
import com.bookstore.contract.ProductDetailPresenter;
import com.bookstore.databinding.ProductDetailLayoutBinding;
import com.bookstore.model.BookDetail;
import com.bookstore.model.OtherProductsAdapter;
import com.bookstore.model.ProductDetailResponse;
import com.bookstore.model.TabAdapter;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailContract {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private RecyclerView rvOtherProducts;
    private ProductDetailLayoutBinding binding;
    private ProductDetailPresenter presenter;
    private String bookId;
    private SearchBookApi apiService;
    private List<BookDetail> products;
    private OtherProductsAdapter otherProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        binding = ProductDetailLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        setupAllBooksRecyclerView();
        setupTabLayout();
        setupOtherProducts();
        displayBookDetails();
        // Nhận dữ liệu từ Intent
//        int bookImage = getIntent().getIntExtra("book_image", -1);
//        String bookTitle = getIntent().getStringExtra("book_title");
//        int price = getIntent().getIntExtra("book_price", -1);
//
//        // Hiển thị hình ảnh và tiêu đề
//        if (bookImage != -1) {
//            binding.bookImgView.setImageResource(bookImage);
//            binding.price.setText(price);
//        }
//
//        binding.titleName.setText(bookTitle);

        // note
//        CartApiService apiService = RetrofitClient.getClient().create(CartApiService.class);
//        presenter = new ProductDetailPresenterImpl(this, apiService);
//        bookId = getIntent().getIntExtra("book_id", -1);
//        if (bookId != -1) {
//            setupAddToCartButton();
//        } else {
//            showAddToCartError("Không tìm thấy thông tin sách");
//            finish();
//        }

        binding.back.setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        binding.btnCartToolbar.setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        tabLayout = binding.tabLayout;
        viewPager = binding.viewPager;
        rvOtherProducts = binding.rvOtherProducts;
        products = new ArrayList<>();


    }
    // Hiển thị thông tin sách
    private void displayBookDetails() {
        String bookImage = getIntent().getStringExtra("book_image");
        String bookTitle = getIntent().getStringExtra("book_title");
        float price = getIntent().getFloatExtra("book_price", 0f);

        if (bookImage != null && !bookImage.isEmpty()) {
            Glide.with(this)
                    .load(bookImage)
                    .into(binding.bookImgView);
        }
        binding.titleName.setText(bookTitle);
        binding.price.setText(String.format("%.0f VND", price));
    }

    private void setupTabLayout() {
        TabAdapter tabAdapter = new TabAdapter(this);
        viewPager.setAdapter(tabAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(TabAdapter.TAB_TITLES[position])
        ).attach();
    }

    private void setupAllBooksRecyclerView() {
        otherProducts = new OtherProductsAdapter(products);
        rvOtherProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvOtherProducts.setAdapter(otherProducts);
    }
    // api get allProduct
    private void setupOtherProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bookstore-api-nodejs.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchBookApi service = retrofit.create(SearchBookApi.class);
        Call<ProductDetailResponse> call = service.getAllBooks();

        call.enqueue(new Callback<ProductDetailResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ProductDetailResponse> call, Response<ProductDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.clear();
                    products.addAll(response.body().getProducts());
                    otherProducts.setProducts(products);
                    otherProducts.notifyDataSetChanged();
                    rvOtherProducts.setAdapter(otherProducts);
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Không thể tải danh sách sản phẩm khác", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ProductDetailResponse> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

            private void setupAddToCartButton() {
        binding.buttonAddToCart.setOnClickListener(v -> {
            // Implement add to cart logic
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
            presenter.addToCart(bookId);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showBookDetails(BookDetail book) {

    }

    @Override
    public void showAddToCartSuccess() {
        Toast.makeText(ProductDetailActivity.this, "Sách đã được thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddToCartError(String message) {
        Toast.makeText(ProductDetailActivity.this, "Sách đã được thêm vào giỏ hàng thành công!" + message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoading() {
        System.out.println("Đang tải...");
        // Có thể thêm logic để hiển thị một biểu tượng loading hoặc progress bar
    }

    @Override
    public void hideLoading() {
        System.out.println("Đã tải xong.");
        // Có thể thêm logic để ẩn biểu tượng loading hoặc progress bar
    }
}