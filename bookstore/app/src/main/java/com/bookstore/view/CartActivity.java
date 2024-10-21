package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.adapter.CartAdapter;
import com.bookstore.R;
import com.bookstore.api.CartApiService;
import com.bookstore.model.Cart;
import com.bookstore.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private CartApiService apiService;
    private RecyclerView recyclerViewCartItems;
    private int totalItemCount = 0; // Initialize total item count


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);

        // Initialize TextView for total items
        TextView txtTotalItems = findViewById(R.id.txtTotalItems);

        // Initialize Retrofit API service
        apiService = RetrofitClient.getClient().create(CartApiService.class);

        // Set up RecyclerView
        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems);
        recyclerViewCartItems.setLayoutManager(new LinearLayoutManager(this));

        // Fetch cart details for user "vanh"
        getCartDetails("vanh");

        // xử lý sự kiện khi ngươi dùng ấn nút quay lại
          findViewById(R.id.btnBack).setOnClickListener(v -> backHomePage());
          //nào sửa sang binding thì bỏ comment dòng dưới
        // binding.btnBack.setOnClickListener(v -> backHomePage());
    }

    private void backHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    // Update the total item count
    private void updateTotalItemCount() {
        totalItemCount = 0;

        // Loop through all items in the RecyclerView

    }

    private void getCartDetails(String username) {

        // Initialize TextView for total items
        TextView txtTotalItems = findViewById(R.id.txtTotalItems);

        // Initialize TextView for total price
        TextView txtTotalPrice = findViewById(R.id.txtTotalPrice);

        Call<List<Cart>> call = apiService.getCartDetails(username);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Cart details fetched successfully
                    List<Cart> carts = response.body();
                    Log.d("Cart", "Cart details fetched successfully");

                    // Update the total item count
                    int totalItems = carts.size();
                    txtTotalItems.setText("Total " + totalItems + " items"); // Update TextView

                    // Calculate the total price
                    double totalPrice = 0;
                    for (Cart item : carts) {
                        totalPrice += item.getBookPackagePrice() * item.getQuantity();
                    }
                    txtTotalPrice.setText("Total: " + String.format("%.2f VND", totalPrice)); // Update TextView

                    // Set the adapter with fetched cart items
                    CartAdapter cartAdapter = new CartAdapter(carts);
                    recyclerViewCartItems.setAdapter(cartAdapter);
                } else {
                    // Failed to fetch cart details
                    Log.e("Cart", "Failed to fetch cart details. Response Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Log.e("Cart", "API call failed: " + t.getMessage());
            }

        });
    }
}
