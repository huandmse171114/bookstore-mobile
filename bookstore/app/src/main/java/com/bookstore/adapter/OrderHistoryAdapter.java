package com.bookstore.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {
    private List<OrderHistory> orderHistories = new ArrayList<>();

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        OrderHistory orderHistory = orderHistories.get(position);
        holder.orderIdTextView.setText(orderHistory.getOrderId());
        holder.orderDateTextView.setText(orderHistory.getOrderDate().toString());
        holder.totalAmountTextView.setText(String.valueOf(orderHistory.getTotalAmount()));

        // Hiển thị thông tin sản phẩm
        if (!orderHistory.getBooks().isEmpty()) {
            Book book = orderHistory.getBooks().get(0); // Lấy sản phẩm đầu tiên làm ví dụ
            holder.productNameTextView.setText(book.getName());
            holder.productQuantityTextView.setText(String.valueOf(book.getQuantity()));
            holder.productPriceTextView.setText(String.valueOf(book.getPrice()));
            Glide.with(holder.itemView.getContext()).load(book.getImage().toString()).into(holder.productImageView);
        }
    }

    @Override
    public int getItemCount() {
        return orderHistories.size();
    }

    public void setOrderHistories(List<OrderHistory> orderHistories) {
        this.orderHistories = orderHistories;
        notifyDataSetChanged();
    }

    static class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView orderIdTextView;
        TextView orderDateTextView;
        TextView totalAmountTextView;
        ImageView productImageView;
        TextView productNameTextView;
        TextView productQuantityTextView;
        TextView productPriceTextView;

        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            orderIdTextView = itemView.findViewById(R.id.orderIdTextView);
            orderDateTextView = itemView.findViewById(R.id.orderDateTextView);
            totalAmountTextView = itemView.findViewById(R.id.totalAmountTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productQuantityTextView = itemView.findViewById(R.id.productQuantityTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
        }
    }
}