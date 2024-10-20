package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.bookstore.R;
import com.bookstore.databinding.ProductDetailLayoutBinding;
import com.bookstore.model.BookDetail;
import com.bookstore.model.OtherProductsAdapter;
import com.bookstore.model.TabAdapter;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private RecyclerView rvOtherProducts;
    private Button btnAddToCart;
    private ProductDetailLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        binding = ProductDetailLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        setupTabLayout();
        setupOtherProducts();
        setupAddToCartButton();
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
        binding.back.setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailActivity.this, HomePageActivity.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        rvOtherProducts = findViewById(R.id.rvOtherProducts);
        btnAddToCart = findViewById(R.id.buttonAddToCart);

        // Setup toolbar
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

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
        binding.price.setText(String.format("%,.0f VND", price));
    }

    private void setupTabLayout() {
        TabAdapter tabAdapter = new TabAdapter(this);
        viewPager.setAdapter(tabAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(TabAdapter.TAB_TITLES[position])
        ).attach();
    }

    private void setupOtherProducts() {
        List<BookDetail> otherProducts = getOtherProducts(); // Method to get product data
        OtherProductsAdapter adapter = new OtherProductsAdapter(otherProducts);
        rvOtherProducts.setAdapter(adapter);
        rvOtherProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void setupAddToCartButton() {
        btnAddToCart.setOnClickListener(v -> {
            // Implement add to cart logic
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    private List<BookDetail> getOtherProducts() {
        // In a real app, this would come from a database or API
        List<BookDetail> products = new ArrayList<>();
        products.add(new BookDetail("The Subtle Art of Not Giving a F*ck", "Mark Manson", 189000, R.drawable.logo));
        products.add(new BookDetail("The Magic of Thinking Big", "David J. Schwartz", 199000, R.drawable.logo));
        return products;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}