package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bookstore.adapter.BookAdapter;
import com.bookstore.api.AuthApi;
import com.bookstore.databinding.ProductListBinding;
import com.bookstore.model.Book;
import com.bookstore.model.BookRequest;
import com.bookstore.api.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    private ProductListBinding binding;
    private BookAdapter bookAdapter;
    private AuthApi authApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProductListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bookAdapter = new BookAdapter();

        // Initialize RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // 2 columns
        binding.listBookRecyclerView1.setLayoutManager(gridLayoutManager);
        binding.listBookRecyclerView1.setAdapter(bookAdapter);

        bookAdapter.setOnBookClickListener(book -> {
            Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
            intent.putExtra("book_image", book.getImage());
            intent.putExtra("book_title", book.getName());
            intent.putExtra("book_price",(float) book.getPrice());
            startActivity(intent);
        });

        // Initialize API
        authApi = RetrofitClient.getClient().create(AuthApi.class);

        // Xử lý sự kiện khi người dùng nhấn vào biểu tượng giỏ hàng
        binding.btnCart.setOnClickListener(v -> openCart());

        // Xử lý sự kiện khi người dùng nhấn vào biểu tượng giỏ hàng
        binding.btnBack.setOnClickListener(v -> openHome());

        // Load data
        loadBooks();
    }

    private void openCart() {
        Toast.makeText(this, "Opening Cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
        startActivity(intent);
    }

    private void openHome() {
        Toast.makeText(this, "Opening Cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ProductListActivity.this, HomePageActivity.class);
        startActivity(intent);
    }

    private void loadBooks() {
        authApi.getBooks(null).enqueue(new Callback<BookRequest>() {
            @Override
            public void onResponse(Call<BookRequest> call, Response<BookRequest> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Book> books = response.body().getProducts();
                    bookAdapter.setBooks(books);
                }
            }

            @Override
            public void onFailure(Call<BookRequest> call, Throwable t) {
                // Handle failure
            }
        });
    }
}