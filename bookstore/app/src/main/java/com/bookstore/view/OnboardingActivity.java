package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bookstore.databinding.OnboardingLayoutBinding;

public class OnboardingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Khởi tạo binding cho layout
        com.bookstore.databinding.OnboardingLayoutBinding binding = OnboardingLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Đặt padding cho view
        ViewCompat.setOnApplyWindowInsetsListener(binding.onboarding, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Animation
        binding.img.animate().translationY(-2500).setDuration(1000).setStartDelay(4000);
        binding.logo.animate().translationY(2400).setDuration(1000).setStartDelay(4000);
        binding.appName.animate().translationY(2400).setDuration(1000).setStartDelay(4000);
        binding.lottie.animate().translationY(2400).setDuration(1000).setStartDelay(4000);

        // forward slider screen
        long totalDuration = 5500;
        new Handler(Looper.getMainLooper()).postDelayed(this::navigateToSliderActivity, totalDuration);
     }
    private void navigateToSliderActivity() {
        Intent intent = new Intent(this, SliderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent); // start the activity
        finish();
    }
}


