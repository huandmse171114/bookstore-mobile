package com.bookstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.R;
import com.bookstore.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Cart> carts;

    public CartAdapter(List<Cart> carts) {
        this.carts = carts;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = carts.get(position);

        // Set the product image, title, author, price, and quantity
        holder.imgProduct.setImageResource(R.drawable.img); // Placeholder, replace with actual image loading
        holder.txtProductName.setText(cart.getBookTitle());
        holder.txtProductPrice.setText(String.format("%,.0f VND", cart.getBookPackagePrice()));
        holder.txtQuantity.setText(String.valueOf(cart.getQuantity()));

        // Optionally set up the quantity buttons if needed
        holder.btnDecreaseQuantity.setOnClickListener(v -> {
            // Decrease quantity logic
        });

        holder.btnIncreaseQuantity.setOnClickListener(v -> {
            // Increase quantity logic
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtProductName, txtProductPrice, txtQuantity;
        ImageButton btnDecreaseQuantity, btnIncreaseQuantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            btnDecreaseQuantity = itemView.findViewById(R.id.btnDecreaseQuantity);
            btnIncreaseQuantity = itemView.findViewById(R.id.btnIncreaseQuantity);
        }
    }
}

