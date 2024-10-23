//package com.bookstore.view;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import com.bookstore.R;
//import com.bookstore.adapter.OrderHistoryAdapter;
//import com.bookstore.contract.OrderHistoryContract;
//import com.bookstore.databinding.ActivityOrderHistoryBinding;
//import com.bookstore.presenter.OrderHistoryPresenter;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//import java.util.List;
//
//public class OrderHistoryActivity extends AppCompatActivity implements OrderHistoryContract {
//    private OrderHistoryPresenter presenter;
//    private OrderHistoryAdapter adapter;
//    private ActivityOrderHistoryBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityOrderHistoryBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        binding.orderHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new OrderHistoryAdapter();
//        binding.orderHistoryRecyclerView.setAdapter(adapter);
//
//        presenter = new OrderHistoryPresenter(this);
//        presenter.loadOrderHistory("userId"); // Thay "userId" bằng ID người dùng thực tế
//
//        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.nav_home:
//                    // Handle home action
//                    return true;
//                case R.id.nav_explore:
//                    // Handle explore action
//                    return true;
//                case R.id.nav_profile:
//                    // Handle profile action
//                    return true;
//                case R.id.nav_history:
//                    // Handle history action
//                    return true;
//            }
//            return false;
//        });
//    }
//
//    @Override
//    public void showOrderHistory(List<OrderHistory> orderHistories) {
//        adapter.setOrderHistories(orderHistories);
//    }
//
//    @Override
//    public void showMessage(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//}