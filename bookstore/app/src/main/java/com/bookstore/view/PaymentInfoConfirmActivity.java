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

import com.bookstore.MyApplication;
import com.bookstore.R;
import com.bookstore.contract.PaymentInfoConfirmContract;
import com.bookstore.databinding.PaymentInfoConfirmBinding;
import com.bookstore.model.PaymentGPTResponse;
import com.bookstore.model.PaymentInfoConfirmModel;
import com.bookstore.presenter.PaymentInfoConfirmPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PaymentInfoConfirmActivity extends AppCompatActivity implements PaymentInfoConfirmContract.View {

    private PaymentInfoConfirmBinding binding;
    private PaymentInfoConfirmContract.Presenter presenter;
    private PaymentGPTResponse response;
    private String userId; // User ID for checking login status

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = PaymentInfoConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new PaymentInfoConfirmPresenter(this, new PaymentInfoConfirmModel());
        userId = ((MyApplication) getApplication()).getUserId();

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.paymentInfoConfirm.getId()), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the PaymentGPTResponse from the Intent
        response = (PaymentGPTResponse) getIntent().getSerializableExtra("payment_response");

        setScreenFieldData(response);

        binding.btnBack.setOnClickListener(v -> finish());

        binding.imgBtnBack.setOnClickListener(v -> finish());

        binding.btnConfirm.setOnClickListener(v -> confirmPaymentInfo());

    }

    private void confirmPaymentInfo() {
        // Create payment bills...
        String id = presenter.createOrder(response);

        // Get Order ID
        String orderId = "ABCKDS123N";
        //redirect to success payment activity
        Intent intent = new Intent(this, SuccessPaymentActivity.class);

        // Attach the PaymentGPTResponse object to the intent
        intent.putExtra("order_id", id);

        startActivity(intent);
    }

    private void setScreenFieldData(PaymentGPTResponse response) {
        binding.textDate.setText(response.getDate());
        binding.textReceiverName.setText(response.getReceiverName());
        binding.textReceiverBankAccount.setText(response.getReceiverAccount() + " " + response.getReceiverBank());
        binding.textSenderName.setText(response.getSenderName());
        binding.textSenderBankAccount.setText(response.getSenderAccount() + " " + response.getSenderBank());
        binding.textTotalAmount.setText(response.getTotalAmount());
        binding.textMessage.setText(response.getMessage());
    }

    @Override
    public void showProcessDialog() {

    }

    @Override
    public void setProcessDialogMessage(String message) {

    }

    @Override
    public void hideProcessDialog() {

    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void redirectSuccessPaymentActivity() {

    }
}
