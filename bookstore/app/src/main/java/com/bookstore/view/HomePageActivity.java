package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bookstore.CongfigAPI.RetrofitClient;
import com.bookstore.R;
import com.bookstore.adapter.BookAdapter;
import com.bookstore.adapter.CategoryAdapter;
import com.bookstore.api.AuthApi;
import com.bookstore.contract.BookContract;
import com.bookstore.contract.CategoryContract;
import com.bookstore.databinding.HomePageBinding;

import com.bookstore.model.SearchBook;
import com.bookstore.presenter.BookPresenter;
import com.bookstore.presenter.CategoryPresenter;

import com.bookstore.model.Book;
import com.bookstore.model.Category;
import com.bookstore.presenter.BookPresenter;
import com.bookstore.presenter.CategoryPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private AuthApi authApi;
    private HomePageBinding binding;
    private BookPresenter bookPresenter;
    private CategoryPresenter categoryPresenter;
    private BookAdapter bookAdapter;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String bookName = "Thay Đổi Một Suy Nghĩ";
        String bookImage = "https://product.hstatic.net/1000237375/product/1080x1080_thay_doi_suy_nghi_b732e0443f9a4e0dbea8bffaf76fdd1b_large.jpg";
        float bookPrice = 90000;
        String bookName1 = "Vượt Qua Sự Chối Bỏ";
        String bookImage1 = "https://product.hstatic.net/1000237375/product/1_01043a8867ef41288874e48bf8c61ab9_master.png";
        float bookPrice1 = 309000;
        // Initialize adapters
        bookAdapter = new BookAdapter();
        categoryAdapter = new CategoryAdapter();

        // Set up RecyclerViews
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        binding.bookRecyclerView.setLayoutManager(gridLayoutManager);
        binding.bookRecyclerView.setAdapter(bookAdapter);
        binding.anotherProductRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.anotherProductRecyclerView.setAdapter(bookAdapter);

        binding.categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.categoryRecyclerView.setAdapter(categoryAdapter);

        // Set OnCategoryClickListener
        categoryAdapter.setOnCategoryClickListener(category -> {
            // Gọi API lấy sách theo danh mục
            categoryPresenter.loadBooksByCategory(category.getId());
        });

        // Initialize presenters
        bookPresenter = new BookPresenter(new BookContract() {
            @Override
            public void showBooks(List<Book> books) {
                bookAdapter.setBooks(books);
            }

            @Override
            public void showMessage(String message) {
                // Handle message
                Toast.makeText(HomePageActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        categoryPresenter = new CategoryPresenter(new CategoryContract() {
            @Override
            public void showCategories(List<Category> categories) {
                categoryAdapter.setCategories(categories);
                binding.categoryRecyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void showMessage(String message) {
                // Handle message
                Toast.makeText(HomePageActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showBooks(List<Book> books) {
                bookAdapter.setBooks(books);
                binding.bookRecyclerView.setAdapter(bookAdapter);
            }
        });

        // Load data
        categoryPresenter.loadCategories();
        categoryPresenter.loadAllBooks();

        // Initialize API service
        authApi = RetrofitClient.getClient().create(AuthApi.class);

        bookAdapter.setOnBookClickListener(book -> {
            String bookId = book.getId();
            Log.d("HomePageActivity", "Book ID: " + bookId);
            Intent intent = new Intent(HomePageActivity.this, ProductDetailActivity.class);
            intent.putExtra("book_image", book.getImage());
            intent.putExtra("book_title", book.getName());
            intent.putExtra("book_price",(float) book.getPrice());
            intent.putExtra("book_id",book.getId());
            startActivity(intent);
        });
        // Xử lý sự kiện khi người dùng nhấn vào biểu tượng giỏ hàng
        binding.cartIcon.setOnClickListener(v -> openCart());

        // Xử lý sự kiện khi người dùng nhấn vào biểu tượng tìm kiếm
        binding.searchIcon.setOnClickListener(v -> openSearch());

        // Xử lý sự kiện khi người dùng nhấn vào nút Load More
        binding.loadMoreButton.setOnClickListener(v -> openSearch());

        // Xử lý sự kiện khi người dùng nhấn vào nút see all
        binding.seeAllButton.setOnClickListener(v -> openSearch());

        binding.shopNow.setOnClickListener(book -> {
            Intent intent = new Intent(HomePageActivity.this, ProductDetailActivity.class);
            intent.putExtra("book_image",bookImage );
            intent.putExtra("book_title",bookName);
            intent.putExtra("book_price",bookPrice);
            startActivity(intent);
        });

        binding.shopNow1.setOnClickListener(book -> {
            Intent intent = new Intent(HomePageActivity.this, ProductDetailActivity.class);
            intent.putExtra("book_image",bookImage1 );
            intent.putExtra("book_title",bookName1);
            intent.putExtra("book_price",bookPrice1);
            startActivity(intent);
        });

        // Xử lý sự kiện cho Footer BottomNavigationView
        setupBottomNavigation();
    }

    // Setup BottomNavigationView actions
    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                // Handle navigation to Home
                Intent homeIntent = new Intent(HomePageActivity.this, HomePageActivity.class);
                startActivity(homeIntent);
                return true;

            } else if (itemId == R.id.nav_explore) {
                // Handle navigation to Explore/Search
                Intent exploreIntent = new Intent(HomePageActivity.this, SearchBookActivity.class);
                startActivity(exploreIntent);
                return true;

            } else if (itemId == R.id.nav_login) {
                // Handle navigation to Login
                Intent loginIntent = new Intent(HomePageActivity.this, AuthActivity.class);
                startActivity(loginIntent);
                return true;
            }

            return false;
        });
    }

    // Hàm để mở Activity giỏ hàng
    private void openCart() {
        Toast.makeText(this, "Opening Cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePageActivity.this, CartActivity.class);
        startActivity(intent);
    }

    // Hàm để mở Activity tìm kiếm
    private void openSearch() {
        Toast.makeText(this, "Opening Search", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePageActivity.this, SearchBookActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
