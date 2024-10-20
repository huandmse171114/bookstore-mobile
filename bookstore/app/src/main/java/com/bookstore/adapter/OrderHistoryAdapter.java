//package com.bookstore.adapter;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bookstore.databinding.ItemOrderHistoryBinding;
//import com.bookstore.model.OrderHistory;
//import com.bookstore.model.Book;
//import com.bumptech.glide.Glide;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {
//    private List<OrderHistory> orderHistories = new ArrayList<>();
//
//    @NonNull
//    @Override
//    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        ItemOrderHistoryBinding binding = ItemOrderHistoryBinding.inflate(inflater, parent, false);
//        return new OrderHistoryViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
//        OrderHistory orderHistory = orderHistories.get(position);
//        holder.binding.orderIdTextView.setText(orderHistory.getOrderId());
//        holder.binding.orderDateTextView.setText(orderHistory.getOrderDate().toString());
//        holder.binding.totalAmountTextView.setText(String.valueOf(orderHistory.getTotalAmount()));
//
//        // Hiển thị thông tin sản phẩm
//        if (!orderHistory.getBooks().isEmpty()) {
//            Book book = orderHistory.getBooks().get(0); // Lấy sản phẩm đầu tiên làm ví dụ
//            holder.binding.productNameTextView.setText(book.getName());
//            holder.binding.productQuantityTextView.setText(String.valueOf(book.getQuantity()));
//            holder.binding.productPriceTextView.setText(String.valueOf(book.getPrice()));
//            Glide.with(holder.binding.productImageView.getContext()).load(book.getImage().toString()).into(holder.binding.productImageView);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return orderHistories.size();
//    }
//
//    public void setOrderHistories(List<OrderHistory> orderHistories) {
//        this.orderHistories = orderHistories;
//        notifyDataSetChanged();
//    }
//
//    static class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
//        private final ItemOrderHistoryBinding binding;
//
//        public OrderHistoryViewHolder(@NonNull ItemOrderHistoryBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//    }
//}