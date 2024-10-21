package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bookstore.contract.PaymentQRCodeContract;
import com.bookstore.databinding.PaymentQrcodeBinding;

public class PaymentQRCodeActivity extends AppCompatActivity implements PaymentQRCodeContract.View {

    private PaymentQrcodeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = PaymentQrcodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.qrCode.getId()), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnBack.setOnClickListener(v -> redirectPaymentPreviewActivity());

        binding.btnForward.setOnClickListener((v -> redirectUploadPaymentActivity()));

    }

    private void redirectPaymentPreviewActivity() {
//        Intent intent = new Intent();
//
//        startActivity(intent);
//        finish();

        showToastMessage("Back To Payment Preview Page");
    }

    private void redirectUploadPaymentActivity() {
        Intent intent = new Intent(this, UploadPaymentActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
