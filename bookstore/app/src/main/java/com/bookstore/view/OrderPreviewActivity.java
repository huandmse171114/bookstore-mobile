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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bookstore.MyApplication;
import com.bookstore.R;
import com.bookstore.adapter.OrderItemAdapter;
import com.bookstore.contract.OrderPreviewContract;
import com.bookstore.databinding.OrderPreviewBinding;
import com.bookstore.model.CartItem;
import com.bookstore.model.OrderItemData;
import com.bookstore.model.OrderItemView;
import com.bookstore.model.OrderPreviewModel;
import com.bookstore.model.ShippingAddress;
import com.bookstore.presenter.OrderPreviewPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderPreviewActivity extends AppCompatActivity implements OrderPreviewContract.View {

    private OrderPreviewBinding binding;
    private OrderPreviewContract.Presenter presenter;
    private ShippingAddress shippingAddress;
    private OrderItemAdapter orderItemAdapter;
    private MyApplication app;
    private String userId; // User ID for checking login status
    private int totalItems = 0, totalAmount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = OrderPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new OrderPreviewPresenter(this, new OrderPreviewModel());
        userId = ((MyApplication) getApplication()).getUserId();

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.orderPreview.getId()), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MyApplication.getCartItems().forEach(item -> {
            totalAmount += item.getPrice() * item.getQuantity();
            totalItems += item.getQuantity();
        });

        NumberFormat numberFormat = NumberFormat.getInstance();

        String totalItemsText = String.valueOf("Total " + totalItems + " items");
        String totalAmountItemsText = numberFormat.format(totalAmount) + " VND";

        binding.textTotalItems.setText(totalItemsText);
        binding.textTotalAmountItems.setText(totalAmountItemsText);

        List<CartItem> orderItemsData = MyApplication.getCartItems();

        binding.btnProceed.setOnClickListener(v -> proceedCheckout());

        binding.btnBack.setOnClickListener(v -> finish());

        binding.orderItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (orderItemsData != null && !orderItemsData.isEmpty()) {
            orderItemAdapter = new OrderItemAdapter(orderItemsData.stream()
                    .map(item -> new OrderItemView(
                            item.getImage(),
                            item.getName(),
                            item.getQuantity(),
                            item.getPrice()
                    ))
                    .collect(Collectors.toList()));
        }else {
            orderItemAdapter = new OrderItemAdapter(new ArrayList<>());
        }

        binding.orderItemRecyclerView.setAdapter(orderItemAdapter);

    }

    private void proceedCheckout() {
        MyApplication app = (MyApplication) getApplicationContext();

        getUserInputShippingAddress();

        app.setShippingAddress(shippingAddress);

        Intent intent = new Intent(this, PaymentQRCodeActivity.class);
        startActivity(intent);
    }

    private void getUserInputShippingAddress() {
        shippingAddress = new ShippingAddress(
                binding.textName.getText().toString(),
                binding.textAddr.getText().toString(),
                binding.textCity.getText().toString(),
                binding.textPostalcode.getText().toString(),
                binding.textCountry.getText().toString(),
                binding.textPhonenum.getText().toString()
        );
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateOrderItemRecyclerView(List<OrderItemData> data) {
        orderItemAdapter.setOrderItemList(data.stream()
                .map(item -> new OrderItemView(
                        item.getImage(),
                        item.getName(),
                        item.getQty(),
                        item.getPrice()
                ))
                .collect(Collectors.toList()));
        binding.orderItemRecyclerView.setAdapter(orderItemAdapter);
    }
}
