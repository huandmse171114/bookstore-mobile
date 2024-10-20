package com.bookstore.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.R;
import com.bookstore.databinding.HomePageBinding;
import com.bookstore.presenter.BookPresenter;
import com.bookstore.presenter.CategoryPresenter;
import com.bookstore.model.Book;
import com.bookstore.model.Category;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {

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
        });

        // Initialize presenters
        bookPresenter = new BookPresenter(new BookView() {
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

        categoryPresenter = new CategoryPresenter(new CategoryView() {
            @Override
            public void showCategories(List<Category> categories) {
                categoryAdapter.setCategories(categories);
            }

            @Override
            public void showMessage(String message) {
                // Handle message
                Toast.makeText(HomePageActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        // Load data
        bookPresenter.loadBooks();
        categoryPresenter.loadCategories();

        // Xử lý sự kiện khi người dùng nhấn vào biểu tượng giỏ hàng
        binding.cartIcon.setOnClickListener(v -> openCart());

        // Xử lý sự kiện khi người dùng nhấn vào biểu tượng tìm kiếm
        binding.searchIcon.setOnClickListener(v -> openSearch());

//        // Xử lý sự kiện cho Footer BottomNavigationView
//        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.nav_home:
//                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.nav_explore:
//                    Toast.makeText(this, "Explore clicked", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.nav_profile:
//                    Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
//                    return true;
//            }
//            return false;
//        });
    }

    // Hàm để mở Activity giỏ hàng (hoặc thực hiện logic khác)
    private void openCart() {
        Toast.makeText(this, "Opening Cart", Toast.LENGTH_SHORT).show();
        // Ví dụ mở Activity giỏ hàng
        // Intent intent = new Intent(HomePageActivity.this, CartActivity.class);
        // startActivity(intent);
    }

    // Hàm để mở Activity tìm kiếm (hoặc thực hiện logic khác)
    private void openSearch() {
        Toast.makeText(this, "Opening Search", Toast.LENGTH_SHORT).show();
        // Ví dụ mở Activity tìm kiếm
        // Intent intent = new Intent(HomePageActivity.this, SearchActivity.class);
        // startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}