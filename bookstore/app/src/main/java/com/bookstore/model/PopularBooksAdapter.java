package com.bookstore.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.R;
import com.bookstore.databinding.ItemPopularBookBinding;
import com.bumptech.glide.Glide;

import java.util.List;

public class PopularBooksAdapter extends RecyclerView.Adapter<PopularBooksAdapter.ViewHolder> {

    private List<SearchBook> books;
    private OnBookClickListener listener;

    public PopularBooksAdapter(List<SearchBook> books) {
        this.books = books;
    }

    // Interface để xử lý sự kiện click vào sách
    public interface OnBookClickListener {
        void onBookClick(SearchBook book);
    }

    // Phương thức để thiết lập listener
    public void setOnBookClickListener(OnBookClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPopularBookBinding binding = ItemPopularBookBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchBook book = books.get(position);
        holder.binding.bookTitleTextView.setText(book.getName());
        holder.binding.PriceTextView.setText(book.getPrice() + " VND");
        holder.binding.bookRatingBar.setRating(book.getRating());
        holder.binding.bookRatingTextView.setText(String.valueOf(book.getReviewCount()));

        // Load image using Glide
        Glide.with(holder.binding.getRoot().getContext())
                .load(book.getImage())
                .placeholder(R.drawable.book_placeholder)
                .error(R.drawable.error_image)
                .into(holder.binding.bookCoverImageView);

        // Thiết lập sự kiện click cho itemView
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onBookClick(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemPopularBookBinding binding;

        ViewHolder(ItemPopularBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setBooks(List<SearchBook> books) {
        this.books = books;
        notifyDataSetChanged();
    }
}
