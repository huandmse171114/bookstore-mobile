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
import com.bookstore.databinding.OrderItemProfileBinding;
import com.bookstore.model.OrderItemView;
import com.bookstore.model.OrderProfile;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;

public class OrderProfileAdapter extends RecyclerView.Adapter<OrderProfileAdapter.OrderProfileItemViewHolder> {

    private List<OrderProfile> orders;
    private static String itemsText = "Items: ";

    public OrderProfileAdapter(List<OrderProfile> orderItemList) {
        this.orders = orderItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderProfileItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        OrderItemProfileBinding binding = OrderItemProfileBinding.inflate(inflater, parent, false);
        return new OrderProfileItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProfileItemViewHolder holder, int position) {
        OrderProfile item = orders.get(position);

        NumberFormat numberFormat = NumberFormat.getInstance();

        itemsText = "    Items: ";
        item.getItems().forEach(it -> {
            itemsText += "\n        Name: " + it.getName() + "\n        Quantity: " + it.getQuantity() + "\n        Price: " + it.getPrice();
        });

        holder.binding.orderId.setText("Order ID: " + item.getOrder().getUser());
        holder.binding.receiverName.setText("    Receiver name: " + "\n        " + item.getOrder().getShippingAddress().getName());
        holder.binding.orderItems.setText(itemsText);
        holder.binding.receiverAddress.setText("    Shipping Address: " + "\n        " + item.getOrder().getShippingAddress().getAddress() + " "
                + item.getOrder().getShippingAddress().getCity() + " "
                + item.getOrder().getShippingAddress().getCountry());

    }

    public static class OrderProfileItemViewHolder extends RecyclerView.ViewHolder {
        OrderItemProfileBinding binding;

        public OrderProfileItemViewHolder(@NonNull OrderItemProfileBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public List<OrderProfile> getOrderItemList() {
        return orders;
    }

    public void setOrderItemList(List<OrderProfile> orderItemList) {
        this.orders = orderItemList;
    }
}
