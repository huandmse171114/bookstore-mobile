package com.bookstore.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bookstore.constract.PaymentInfoConfirmContract;
import com.bookstore.databinding.PaymentInfoConfirmBinding;
import com.bookstore.model.PaymentGPTResponse;
import com.bookstore.model.PaymentInfoConfirmModel;
import com.bookstore.presenter.PaymentInfoConfirmPresenter;

public class PaymentInfoConfirmActivity extends AppCompatActivity implements PaymentInfoConfirmContract.View {

    private PaymentInfoConfirmBinding binding;
    private PaymentInfoConfirmContract.Presenter presenter;
    private PaymentGPTResponse response;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = PaymentInfoConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new PaymentInfoConfirmPresenter(this, new PaymentInfoConfirmModel());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.paymentInfoConfirm.getId()), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the PaymentGPTResponse from the Intent
        response = (PaymentGPTResponse) getIntent().getSerializableExtra("payment_response");

        setScreenFieldData(response);
    }

    private void setScreenFieldData(PaymentGPTResponse response) {
        binding.textDate.setText(response.getDate());
        binding.textReceiverName.setText(response.getReceiverName());
        binding.textReceiverBankAccount.setText(response.getReceiverAccount() + " " + response.getReceiverBank());
        binding.textSenderName.setText(response.getSenderName());
        binding.textSenderBankAccount.setText(response.getSenderAccount() + " " + response.getSenderBank());
        binding.textTotalAmount.setText(response.getTotalAmount());
    }
}
