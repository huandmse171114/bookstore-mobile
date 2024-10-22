package com.bookstore.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bookstore.R;
import com.bookstore.databinding.ItemOtherProductBinding;
import com.bumptech.glide.Glide;

import java.util.List;

public class OtherProductsAdapter extends RecyclerView.Adapter<OtherProductsAdapter.ViewHolder> {

    private List<BookDetail> products;

    public OtherProductsAdapter(List<BookDetail> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemOtherProductBinding binding = ItemOtherProductBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookDetail book = products.get(position);
        holder.binding.tvTitle.setText(book.getName());
        holder.binding.tvAuthor.setText(book.getCategory());
        holder.binding.tvPrice.setText(book.getPrice() + " VND");
        // Load image using Glide
        Glide.with(holder.binding.getRoot().getContext())
                .load(book.getImage())
                .placeholder(R.drawable.book_placeholder)
                .error(R.drawable.error_image)
                .into(holder.binding.ivCover);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemOtherProductBinding binding;

        ViewHolder(ItemOtherProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setProducts(List<BookDetail> products) {
        this.products = products;
        notifyDataSetChanged();
    }
}