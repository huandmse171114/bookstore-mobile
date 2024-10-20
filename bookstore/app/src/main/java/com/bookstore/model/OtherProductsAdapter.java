package com.bookstore.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.R;

import java.util.List;
import java.util.Locale;

public class OtherProductsAdapter extends RecyclerView.Adapter<OtherProductsAdapter.ProductViewHolder> {

    private List<BookDetail> products;

    public OtherProductsAdapter(List<BookDetail> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_other_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        BookDetail book = products.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle, tvAuthor, tvPrice;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }

        void bind(BookDetail product) {
            ivCover.setImageResource(product.getCoverResourceId());
            tvTitle.setText(product.getTitle());
            tvAuthor.setText(product.getAuthor());
            tvPrice.setText(String.format(Locale.getDefault(), "IDR %,d", product.getPrice()));
        }
    }
}