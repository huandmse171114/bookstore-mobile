package com.bookstore;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.api.CartApiService;
import com.bookstore.api.CartItem;
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

        Call<List<CartItem>> call = apiService.getCartDetails(username);
        call.enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Cart details fetched successfully
                    List<CartItem> cartItems = response.body();
                    Log.d("Cart", "Cart details fetched successfully");

                    // Update the total item count
                    int totalItems = cartItems.size();
                    txtTotalItems.setText("Total " + totalItems + " items"); // Update TextView

                    // Calculate the total price
                    double totalPrice = 0;
                    for (CartItem item : cartItems) {
                        totalPrice += item.getBookPackagePrice() * item.getQuantity();
                    }
                    txtTotalPrice.setText("Total: " + String.format("%.2f VND", totalPrice)); // Update TextView

                    // Set the adapter with fetched cart items
                    CartAdapter cartAdapter = new CartAdapter(cartItems);
                    recyclerViewCartItems.setAdapter(cartAdapter);
                } else {
                    // Failed to fetch cart details
                    Log.e("Cart", "Failed to fetch cart details. Response Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
                Log.e("Cart", "API call failed: " + t.getMessage());
            }

        });
    }
}
