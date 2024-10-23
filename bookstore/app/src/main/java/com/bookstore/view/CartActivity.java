package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.MyApplication;
import com.bookstore.adapter.CartAdapter;
import com.bookstore.R;
import com.bookstore.api.CartApiService;
import com.bookstore.api.RetrofitClient;
import com.bookstore.contract.CartContract;
import com.bookstore.databinding.CartLayoutBinding;
import com.bookstore.model.CartItem;
import com.bookstore.model.CartItemResponse;
import com.bookstore.model.CartModel;
import com.bookstore.presenter.CartPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartActivity extends AppCompatActivity implements CartContract.View {
    private CartLayoutBinding binding;
    private CartContract.Presenter presenter;
    private CartAdapter cartAdapter;
    private MyApplication app;
    private List<CartItemResponse> cartItems;
    private int totalItems = 0, totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CartLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new CartPresenter(this, new CartModel());

        presenter.getCartItems();

        if (cartItems != null && !cartItems.isEmpty()) {
            cartAdapter = new CartAdapter(cartItems.stream()
                    .map(item -> new CartItem(item.getId(), item.getName(), item.getImage(), item.getQty(), item.getPrice()))
                    .collect(Collectors.toList()), this);
        }else {
            cartAdapter = new CartAdapter(new ArrayList<>(), this);
        }
        binding.recyclerViewCartItems.setLayoutManager(new LinearLayoutManager(this));

        binding.recyclerViewCartItems.setAdapter(cartAdapter);

        binding.imgBtnBack.setOnClickListener(v -> finish());

        binding.btnProceed.setOnClickListener(v -> redirectOrderPreviewActivity());
    }

    private void redirectOrderPreviewActivity() {
        Intent intent = new Intent(this, OrderPreviewActivity.class);

        MyApplication.setCartItems(cartAdapter.getCartItems());
        MyApplication.setTotalPrice(totalPrice);
        MyApplication.setTotalItems(totalItems);

        startActivity(intent);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCartItemsRecyclerView2(List<CartItem> data) {
        cartAdapter.setCartItems(data);

        totalPrice = 0;
        totalItems = 0;

        data.forEach(item -> {
            totalPrice += item.getPrice() * item.getQuantity();
            totalItems += item.getQuantity();
        });

        NumberFormat numberFormat = NumberFormat.getInstance();

        binding.totalItems.setText(String.valueOf("Total: " + totalItems + " item(s)"));
        binding.totalPrice.setText(String.valueOf(numberFormat.format(totalPrice) + " VND"));
        binding.recyclerViewCartItems.setAdapter(cartAdapter);
    }

    @Override
    public void updateCartItemsRecyclerView(List<CartItemResponse> data) {
        cartAdapter.setCartItems(data.stream()
                .map(item -> new CartItem(item.getId(), item.getName(), item.getImage(), item.getPrice(), item.getQty()))
                .collect(Collectors.toList()));

        totalPrice = 0;
        totalItems = 0;

        data.forEach(item -> {
            totalPrice += item.getPrice() * item.getQty();
            totalItems += item.getQty();
        });

        NumberFormat numberFormat = NumberFormat.getInstance();
        binding.totalItems.setText(String.valueOf("Total: " + totalItems + " item(s)"));
        binding.totalPrice.setText(String.valueOf(numberFormat.format(totalPrice) + " VND"));
        binding.recyclerViewCartItems.setAdapter(cartAdapter);
    }


}
