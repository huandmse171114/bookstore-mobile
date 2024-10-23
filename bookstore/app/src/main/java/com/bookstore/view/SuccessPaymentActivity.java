package com.bookstore.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bookstore.constract.SuccessPaymentContract;
import com.bookstore.databinding.SuccessPaymentBinding;
import com.bookstore.model.SuccessPaymentModel;
import com.bookstore.presenter.SuccessPaymentPresenter;

public class SuccessPaymentActivity extends AppCompatActivity implements SuccessPaymentContract.View {

    private SuccessPaymentBinding binding;
    private SuccessPaymentContract.Presenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SuccessPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new SuccessPaymentPresenter(this, new SuccessPaymentModel());

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
        Toast.makeText(this, "Back To Home Page", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent();
//        startActivity(intent);
//        finish();
    }
}
