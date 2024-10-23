package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bookstore.CongfigAPI.RetrofitClient;
import com.bookstore.MyApplication;
import com.bookstore.R;
import com.bookstore.adapter.BookAdapter;
import com.bookstore.adapter.CategoryAdapter;
import com.bookstore.api.AuthApi;
import com.bookstore.constract.BookContract;
import com.bookstore.constract.CategoryContract;
import com.bookstore.databinding.HomePageBinding;

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

    private ImageView profileImage; // Profile Image view
    private String userId; // User ID for checking login status

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve userId from MyApplication or SharedPreferences
        userId = ((MyApplication) getApplication()).getUserId();

        // Find the profileImage view
        profileImage = binding.profileImage; // Assuming you have this in your binding

        // Set up adapters
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
            // Call API to load books by category
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
            }

            @Override
            public void showMessage(String message) {
                // Handle message
                Toast.makeText(HomePageActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showBooks(List<Book> books) {
                bookAdapter.setBooks(books);
            }
        });

        // Load data
        categoryPresenter.loadCategories();
        categoryPresenter.loadAllBooks();

        // Initialize API service
        authApi = RetrofitClient.getClient().create(AuthApi.class);

        // Book click listener
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

        // Cart and search actions
        binding.cartIcon.setOnClickListener(v -> openCart());
        binding.searchIcon.setOnClickListener(v -> openSearch());

        // Xử lý sự kiện khi người dùng nhấn vào nút Load More
        binding.loadMoreButton.setOnClickListener(v -> openProductList());

        // Xử lý sự kiện khi người dùng nhấn vào nút see all
        binding.seeAllButton.setOnClickListener(v -> openProductList());


        // Profile image click listener
        profileImage.setOnClickListener(v -> {
            if (userId != null && !userId.isEmpty()) {
                Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
                intent.putExtra("userId", userId); // Pass userId if necessary
                startActivity(intent);
            } else {
                Toast.makeText(HomePageActivity.this, "Please log in to access profile", Toast.LENGTH_SHORT).show();
            }
        });

        // Footer BottomNavigationView
        setupBottomNavigation();
    }

    // Setup BottomNavigationView actions
    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            // Handle navigation based on the selected item
            if (itemId == R.id.nav_home) {
                // Handle navigation to Home
                return true;
            } else if (itemId == R.id.nav_explore) {
                // Handle navigation to Explore/Search
                Intent exploreIntent = new Intent(HomePageActivity.this, SearchBookActivity.class);
                startActivity(exploreIntent);
                return true;
            } else if (itemId == R.id.nav_login) {
                if (userId == null || userId.isEmpty()) {
                    Intent loginIntent = new Intent(HomePageActivity.this, AuthActivity.class);
                    startActivity(loginIntent);
                } else {
                    Intent profileIntent = new Intent(HomePageActivity.this, ProfileActivity.class);
                    startActivity(profileIntent);
                }
                return true;
            }
            return false;
        });
    }

    // Open Cart Activity
    private void openCart() {
        Toast.makeText(this, "Opening Cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePageActivity.this, CartActivity.class);
        startActivity(intent);
    }

    // Open Search Activity
    private void openSearch() {
        Toast.makeText(this, "Opening Search", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePageActivity.this, SearchBookActivity.class);
        startActivity(intent);
    }

    // Hàm để mở Activity load more, see alll
    private void openProductList() {
        Toast.makeText(this, "Opening Search", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePageActivity.this, ProductListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Prevent memory leaks
    }
}
