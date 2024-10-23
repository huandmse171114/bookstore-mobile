package com.bookstore.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.databinding.ItemBookBinding;
import com.bookstore.databinding.OrderItemBinding;
import com.bookstore.model.OrderItemView;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    private List<OrderItemView> orderItemList;

    public OrderItemAdapter(List<OrderItemView> orderItemList) {
        this.orderItemList = orderItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        OrderItemBinding binding = OrderItemBinding.inflate(inflater, parent, false);
        return new OrderItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        OrderItemView item = orderItemList.get(position);

        NumberFormat numberFormat = NumberFormat.getInstance();

        String name = item.getName();
        if (name.length() > 25) name = name.substring(0, 25) + "...";
        holder.binding.itemName.setText(name);
        holder.binding.itemPrice.setText(numberFormat.format(item.getPrice()) + " VND");
        holder.binding.itemQuantity.setText(String.valueOf(item.getQuantity()) + " items");
        Glide.with(holder.binding.getRoot())
                .load(Uri.parse(item.getImageURL()))
                .into(holder.binding.itemImage);
    }

    public static class OrderItemViewHolder extends RecyclerView.ViewHolder {
        OrderItemBinding binding;

        public OrderItemViewHolder(@NonNull OrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public List<OrderItemView> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemView> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
