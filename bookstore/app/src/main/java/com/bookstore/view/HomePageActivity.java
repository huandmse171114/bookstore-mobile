package com.bookstore.view;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.R;
import com.bookstore.model.Book;
import com.bookstore.model.Category;
import com.bookstore.presenter.BookPresenter;
import com.bookstore.presenter.CategoryPresenter;

import java.util.List;

public class HomePageActivity extends AppCompatActivity implements CategoryView, BookView {

    private RecyclerView categoryRecyclerView;
    private RecyclerView bookRecyclerView;
    private CategoryAdapter categoryAdapter;
    private BookAdapter bookAdapter;
    private CategoryPresenter categoryPresenter;
    private BookPresenter bookPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        // Initialize presenters
        categoryPresenter = new CategoryPresenter(this);
        bookPresenter = new BookPresenter(this);

        // Set up category RecyclerView
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Set up book RecyclerView
        bookRecyclerView = findViewById(R.id.bookRecyclerView);
        bookRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        // Load categories and books
        categoryPresenter.loadCategories();
        bookPresenter.loadBooks();




    }

    @Override
    public void showCategories(List<Category> categories) {
        categoryAdapter = new CategoryAdapter(categories, new CategoryAdapter.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(Category category) {
                categoryPresenter.onCategorySelected(category);

            }
        });
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void showBooks(List<Book> books) {
        bookAdapter = new BookAdapter(books);
        bookRecyclerView.setAdapter(bookAdapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}