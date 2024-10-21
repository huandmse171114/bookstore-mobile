package com.bookstore.presenter;

import android.util.Log;
import com.bookstore.api.AuthApi;
import com.bookstore.contract.CategoryContract;
import com.bookstore.model.BookRequest;
import com.bookstore.model.Category;
import com.bookstore.CongfigAPI.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {
    private static final String TAG = "CategoryPresenter";
    private CategoryContract view;
    private AuthApi authApi;

    public CategoryPresenter(CategoryContract view) {
        this.view = view;
        this.authApi = RetrofitClient.getClient().create(AuthApi.class);
    }

    public void loadCategories() {
        Call<List<Category>> call = authApi.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categories = response.body();
                    Log.d(TAG, "Categories loaded: " + categories.toString());
                    view.showCategories(categories);
                } else {
                    Log.e(TAG, "Failed to load categories: " + response.message());
                    view.showMessage("Failed to load categories");
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(TAG, "Error loading categories: " + t.getMessage(), t);
                view.showMessage("Error: " + t.getMessage());
            }
        });
    }
    public void loadBooksByCategory(Integer categoryId) { // Giữ int
        Call<BookRequest> call;
        call = authApi.getBooks(categoryId);

        call.enqueue(new Callback<BookRequest>() {
            @Override
            public void onResponse(Call<BookRequest> call, Response<BookRequest> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BookRequest books = response.body();
                    view.showBooks(books.getProducts()); // Truyền danh sách sách cho View
                } else {
                    Log.e(TAG, "Failed to load books: " + response.message());
                    view.showMessage("Failed to load books");
                }
            }

            @Override
            public void onFailure(Call<BookRequest> call, Throwable t) {
                Log.e(TAG, "Error loading books: " + t.getMessage(), t);
                view.showMessage("Error: " + t.getMessage());
            }
        });
    }

    // Tải tất cả sách
    public void loadAllBooks() {
        loadBooksByCategory(null);
    }

    public void onCategorySelected(Category category) {
        Log.d(TAG, "Category selected: " + category.getName());
        view.showMessage("Selected: " + category.getName());
    }
}