package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bookstore.CongfigAPI.RetrofitClient;
import com.bookstore.MyApplication;
import com.bookstore.R;
import com.bookstore.adapter.OrderProfileAdapter;
import com.bookstore.api.AuthApi;
import com.bookstore.databinding.ProfileLayoutBinding; // Import the generated binding class
import com.bookstore.model.OrderProfile;
import com.bookstore.model.UserProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private ProfileLayoutBinding binding; // Declare the binding object
    private AuthApi userService;
    private String userId;
    private OrderProfileAdapter orderProfileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfileLayoutBinding.inflate(getLayoutInflater()); // Inflate the binding layout
        setContentView(binding.getRoot()); // Set the content view to the root of the binding

        OrderProfile newOrderProfile = new OrderProfile(
                MyApplication.getOrderCreateResponses().get(0),
                MyApplication.getCartItems()
        );

        List<OrderProfile> orderProfiles = new ArrayList<>();
        orderProfiles.add(newOrderProfile);

        orderProfileAdapter = new OrderProfileAdapter(orderProfiles);

        binding.orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.orderRecyclerView.setAdapter(orderProfileAdapter);

        // Get userId from MyApplication
        userId = ((MyApplication) getApplication()).getUserId();

        // Initialize Retrofit for AuthApi
        userService = RetrofitClient.getClient().create(AuthApi.class);

        // Fetch profile details on activity start
        fetchUserProfile(userId);

        // Handle back button click
        binding.btnBack.setOnClickListener(v -> finish());

        // Handle logout button click
        binding.btnLogout.setOnClickListener(v -> logout());

        // Set up bottom navigation behavior
        setupBottomNavigation();
    }

    // Fetch user profile from API using the userId
    private void fetchUserProfile(String userId) {
        userService.getUserProfile(userId).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    UserProfile userProfile = response.body();
                    if (userProfile != null) {
                        // Update the UI with user profile details using View Binding
                        binding.tvIdValue.setText(userProfile.getId());
                        binding.tvUsernameValue.setText(userProfile.getUsername());
                        binding.tvEmailValue.setText(userProfile.getEmail());
                    }
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed to fetch profile!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                Toast.makeText(ProfileActivity.this, "Error fetching profile!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Handle logout API call
    private void logout() {
        userService.logout().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();

                    // Clear userId in MyApplication
                    ((MyApplication) getApplication()).setUserId(null);

                    // Redirect to HomePageActivity
                    Intent intent = new Intent(ProfileActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ProfileActivity.this, "Logout failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                Toast.makeText(ProfileActivity.this, "Error during logout!", Toast.LENGTH_SHORT).show();
            }
        });
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
                Intent exploreIntent = new Intent(ProfileActivity.this, SearchBookActivity.class);
                startActivity(exploreIntent);
                return true;
            } else if (itemId == R.id.nav_login) {
                if (userId == null || userId.isEmpty()) {
                    Intent loginIntent = new Intent(ProfileActivity.this, AuthActivity.class);
                    startActivity(loginIntent);
                } else {
                    Intent profileIntent = new Intent(ProfileActivity.this, ProfileActivity.class);
                    startActivity(profileIntent);
                }
                return true;
            }
            return false;
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Prevent memory leaks
    }
}
