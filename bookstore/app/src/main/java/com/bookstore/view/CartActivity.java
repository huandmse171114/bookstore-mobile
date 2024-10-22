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
import com.bookstore.api.CartItem;
import com.bookstore.api.RetrofitClient;
import com.bookstore.contract.CartContract;
import com.bookstore.databinding.CartLayoutBinding;
import com.bookstore.model.CartItemResponse;
import com.bookstore.model.CartModel;
import com.bookstore.presenter.CartPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartActivity extends AppCompatActivity implements CartContract.View {
    private CartLayoutBinding binding;
    private CartContract.Presenter presenter;
    private CartAdapter cartAdapter;
    private MyApplication app;
    private List<CartItemResponse> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CartLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new CartPresenter(this, new CartModel());

        presenter.getCartItems();

        if (cartItems != null && !cartItems.isEmpty()) {
            cartAdapter = new CartAdapter(cartItems.stream()
                    .map(item -> new CartItem(item.get_id(), item.getName(), item.getImage(), item.getQty(), item.getPrice()))
                    .collect(Collectors.toList()));
        }else {
            cartAdapter = new CartAdapter(new ArrayList<>());
        }

        binding.recyclerViewCartItems.setAdapter(cartAdapter);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateCartItemsRecyclerView(List<CartItemResponse> data) {
        cartAdapter.setCartItems(data.stream()
                .map(item -> new CartItem(item.get_id(), item.getName(), item.getImage(), item.getQty(), item.getPrice()))
                .collect(Collectors.toList()));
        binding.recyclerViewCartItems.setAdapter(cartAdapter);
    }
}
