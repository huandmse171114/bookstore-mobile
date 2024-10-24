package com.bookstore.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bookstore.MyApplication;
import com.bookstore.R;
import com.bookstore.contract.SuccessPaymentContract;
import com.bookstore.databinding.SuccessPaymentBinding;
import com.bookstore.model.SuccessPaymentModel;
import com.bookstore.presenter.SuccessPaymentPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SuccessPaymentActivity extends AppCompatActivity implements SuccessPaymentContract.View {

    private SuccessPaymentBinding binding;
    private SuccessPaymentContract.Presenter presenter;
    private ProgressDialog progressDialog;
    private String userId; // User ID for checking login status

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SuccessPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new SuccessPaymentPresenter(this, new SuccessPaymentModel());
        userId = ((MyApplication) getApplication()).getUserId();

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.successPayment.getId()), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnBack.setOnClickListener(v -> redirectHomeActivity());

        binding.btnHomePage.setOnClickListener(v -> redirectHomeActivity());
    }

    private void redirectHomeActivity() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

}
