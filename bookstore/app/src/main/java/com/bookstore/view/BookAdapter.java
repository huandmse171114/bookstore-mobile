package com.bookstore.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bookstore.R;
import com.bookstore.databinding.ItemBookBinding;
import com.bookstore.model.Book;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> books;

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBookBinding binding = ItemBookBinding.inflate(inflater, parent, false);
        return new BookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.binding.bookTitle.setText(book.getTitle());
        holder.binding.bookPrice.setText(String.format("%,.0f VND", book.getPrice()));
        holder.binding.bookImage.setImageResource(book.getImageResourceId());

        holder.binding.heartIcon.setOnClickListener(v -> {
            boolean isFavorite = (boolean) holder.binding.heartIcon.getTag();
            if (isFavorite) {
                holder.binding.heartIcon.setImageResource(R.drawable.ic_heart_before_click);
                Toast.makeText(holder.itemView.getContext(), "Loại bỏ khỏi mục yêu thích", Toast.LENGTH_SHORT).show();
            } else {
                holder.binding.heartIcon.setImageResource(R.drawable.ic_heart_after_click);
                Toast.makeText(holder.itemView.getContext(), "Đã thêm vào mục yêu thích", Toast.LENGTH_SHORT).show();
            }
            holder.binding.heartIcon.setTag(!isFavorite);
        });

        holder.binding.heartIcon.setTag(false);
    }

    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ItemBookBinding binding;

        public BookViewHolder(ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}