package com.bookstore.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.adapter.BookAdapter; // Nếu cần thiết
import com.bookstore.api.SearchBookApi;
import com.bookstore.databinding.SearchLayoutBinding;
import com.bookstore.model.LatestSearchAdapter;
import com.bookstore.model.PopularBooksAdapter;
import com.bookstore.model.SearchBook;
import com.bookstore.model.SearchBookResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchBookActivity extends AppCompatActivity {

    private EditText etSearch;
    private RecyclerView rvLatestSearch;
    private RecyclerView rvPopularBooks;
    private LatestSearchAdapter latestSearchAdapter;
    private PopularBooksAdapter popularBooksAdapter;
    private List<String> latestSearches;
    private List<SearchBook> books;
    private SearchLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // Khởi tạo binding cho layout
        binding = SearchLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Đặt padding cho view
        ViewCompat.setOnApplyWindowInsetsListener(binding.searchLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etSearch = binding.etSearch;
        rvLatestSearch = binding.rvLatestSearch;
        rvPopularBooks = binding.rvPopularBooks;
        latestSearches = new ArrayList<>();
        books = new ArrayList<>();

        setupLatestSearchRecyclerView();
        setupPopularBooksRecyclerView();
        loadPopularBooks(etSearch.getText().toString().trim());

        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String query = etSearch.getText().toString().trim();
                loadPopularBooks(query);
                if (!query.isEmpty()) {
                    addToLatestSearches(query);
                    etSearch.setText(""); // Xóa nội dung của EditText sau khi tìm kiếm
                }
                return true;
            }
            return false;
        });

        binding.btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(SearchBookActivity.this, HomePageActivity.class);
            startActivity(intent);
        });
    }

    private void setupLatestSearchRecyclerView() {
        latestSearchAdapter = new LatestSearchAdapter(latestSearches);
        rvLatestSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvLatestSearch.setAdapter(latestSearchAdapter);
    }

    private void setupPopularBooksRecyclerView() {
        popularBooksAdapter = new PopularBooksAdapter(books);
        rvPopularBooks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvPopularBooks.setAdapter(popularBooksAdapter);

        // Thiết lập listener cho sự kiện click vào sách
        popularBooksAdapter.setOnBookClickListener(book -> {
            Intent intent = new Intent(SearchBookActivity.this, ProductDetailActivity.class);
            intent.putExtra("book_image", book.getImage());
            intent.putExtra("book_title", book.getName());
            intent.putExtra("book_price", book.getPrice()); // Giả sử `price` đã là kiểu float
            intent.putExtra("book_id", book.getId());
            startActivity(intent);
        });
    }

    private void addToLatestSearches(String query) {
        if (!latestSearches.contains(query)) {
            latestSearches.add(0, query);
            if (latestSearches.size() > 5) {
                latestSearches.remove(latestSearches.size() - 1);
            }
            latestSearchAdapter.notifyDataSetChanged();
        }
    }

    private void loadPopularBooks(String query) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bookstore-api-nodejs.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchBookApi service = retrofit.create(SearchBookApi.class);
        Call<SearchBookResponse> call = service.getPopularBooks(query);

        call.enqueue(new Callback<SearchBookResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<SearchBookResponse> call, Response<SearchBookResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    books.clear();
                    books.addAll(response.body().getProducts());
                    popularBooksAdapter.setBooks(books);
                    popularBooksAdapter.notifyDataSetChanged();
                    Log.e("check", "Response successful: " + response.body().getProducts());
                } else {
                    Log.e("check", "Response not successful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<SearchBookResponse> call, Throwable t) {
                Log.e("check", "API call failed", t);
            }
        });
    }
}