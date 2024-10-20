package com.bookstore.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.R;
import com.bookstore.constract.OrderHistoryContract;
import com.bookstore.presenter.OrderHistoryPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity implements OrderHistoryContract {
    private OrderHistoryPresenter presenter;
    private OrderHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        RecyclerView recyclerView = findViewById(R.id.orderHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderHistoryAdapter();
        recyclerView.setAdapter(adapter);

        presenter = new OrderHistoryPresenter(this);
        presenter.loadOrderHistory("userId"); // Thay "userId" bằng ID người dùng thực tế

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    // Handle home action
                    return true;
                case R.id.nav_explore:
                    // Handle explore action
                    return true;
                case R.id.nav_profile:
                    // Handle profile action
                    return true;
                case R.id.nav_history:
                    // Handle history action
                    return true;
            }
            return false;
        });
    }

    @Override
    public void showOrderHistory(List<OrderHistory> orderHistories) {
        adapter.setOrderHistories(orderHistories);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}