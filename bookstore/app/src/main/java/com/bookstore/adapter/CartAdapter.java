package com.bookstore.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.R;
import com.bookstore.contract.CartContract;
import com.bookstore.databinding.CartItemBinding;
import com.bookstore.model.CartItem;
import com.bookstore.view.CartActivity;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private CartContract.View view;

    public CartAdapter(List<CartItem>cartItems, CartContract.View view) {
        this.cartItems = cartItems;
        this.view = view;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CartItemBinding binding = CartItemBinding.inflate(inflater, parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        NumberFormat numberFormat = NumberFormat.getInstance();
        String name = cartItem.getName();
        if (name.length() > 40) name = name.substring(0, 40) + "...";
        holder.binding.itemName.setText(name);
        holder.binding.itemPrice.setText("Price: " + numberFormat.format(cartItem.getPrice()) + " VND");
        holder.binding.itemQuantity.setText(String.valueOf(cartItem.getQuantity()));
        Glide.with(holder.binding.getRoot())
                .load(Uri.parse(cartItem.getImage()))
                .into(holder.binding.itemImage);

        holder.binding.increaseBtn.setOnClickListener(v -> {
            holder.binding.itemQuantity.setText(String.valueOf(Integer.parseInt(
                    holder.binding.itemQuantity.getText().toString()
            ) + 1));
            cartItem.setQuantity(Integer.parseInt(
                    holder.binding.itemQuantity.getText().toString()
            ));
            cartItems.set(position, cartItem);
            view.updateCartItemsRecyclerView2(cartItems);

        });

        holder.binding.decreaseBtn.setOnClickListener(v -> {
            int currQty = Integer.parseInt(
                    holder.binding.itemQuantity.getText().toString()
            );
            if (currQty > 1) {
                holder.binding.itemQuantity.setText(String.valueOf( currQty - 1));
                cartItem.setQuantity(Integer.parseInt(
                        holder.binding.itemQuantity.getText().toString()
                ));
                cartItems.set(position, cartItem);
                view.updateCartItemsRecyclerView2(cartItems);
            }else {
                cartItems.remove(position);
                notifyItemRemoved(position);
                view.updateCartItemsRecyclerView2(cartItems);
                Toast.makeText(v.getContext(), "Product remove from cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        CartItemBinding binding;

        public CartViewHolder(@NonNull CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

