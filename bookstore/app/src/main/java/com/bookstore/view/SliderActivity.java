package com.bookstore.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.bookstore.databinding.SliderActivityBinding;
import com.bookstore.adapter.SliderViewAdapter;


public class SliderActivity extends AppCompatActivity {
    private ViewPager   screenPager;
    private SliderViewAdapter sliderViewAdapter;
    private SliderActivityBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = SliderActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.sliderLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo ViewPager
        screenPager = binding.viewPager;  // Lưu reference
        sliderViewAdapter = new SliderViewAdapter(this, screenPager);
        screenPager.setAdapter(sliderViewAdapter);
    }
}