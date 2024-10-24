package com.bookstore.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bookstore.R;
import com.bookstore.databinding.ItemOtherProductBinding;
import com.bookstore.model.BookDetail;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;

public class OtherProductsAdapter extends RecyclerView.Adapter<OtherProductsAdapter.ViewHolder> {

    private List<BookDetail> products;
    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(BookDetail book);
    }
    public OtherProductsAdapter(List<BookDetail> products, OnProductClickListener listener) {
        this.products = products;
        this.listener = listener;
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
        NumberFormat numberFormat = NumberFormat.getInstance();
        holder.binding.tvPrice.setText(numberFormat.format(book.getPrice()) + " VND");
        // Load image using Glide
        Glide.with(holder.binding.getRoot().getContext())
                .load(book.getImage())
                .placeholder(R.drawable.book_placeholder)
                .error(R.drawable.error_image)
                .into(holder.binding.ivCover);
        holder.itemView.setOnClickListener(v -> listener.onProductClick(book));
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