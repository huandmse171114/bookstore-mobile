package com.bookstore.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.bookstore.databinding.SliderActivityBinding;
import com.bookstore.model.SliderViewAdapter;


public class SliderActivity extends AppCompatActivity {
    public static ViewPager screenPager;
    private SliderViewAdapter sliderViewAdapter;
    private SliderActivityBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // Khởi tạo binding cho layout slider_activity
        binding = SliderActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Đặt padding cho view
        ViewCompat.setOnApplyWindowInsetsListener(binding.sliderLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // init view
        sliderViewAdapter = new SliderViewAdapter(this);
        binding.viewPager.setAdapter(sliderViewAdapter);

        // này nếu muốn người dùng chỉ xem được một lần
        // use this code
//        if (isOpenAlready()) {
//            Intent intent = new Intent(SliderActivity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        } else {
//            SharedPreferences.Editor editor = getSharedPreferences("slide", MODE_PRIVATE).edit();
//            editor.putBoolean("slide", true);
//            editor.apply();
//        }
    }
    // when this activity is about to be launch we need to check if its opened before or not
//    private boolean isOpenAlready() {
//        SharedPreferences sharedPreferences = getSharedPreferences("slide", MODE_PRIVATE);
//        return sharedPreferences.getBoolean("slide", false);
//    }
}