package com.bookstore.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bookstore.R;
import com.bookstore.databinding.ItemBookBinding;
import com.bookstore.model.Book;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> books;
    private OnBookClickListener onBookClickListener;

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    public void setOnBookClickListener(OnBookClickListener listener) {
        this.onBookClickListener = listener;
    }

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
        holder.bind(book);
        holder.binding.getRoot().setOnClickListener(v -> {
            if (onBookClickListener != null) {
                onBookClickListener.onBookClick(book);
            }
        });
//        if (book.getName().length() > 15) book.setName(book.getName().substring(0, 15) + " ...");
//        holder.binding.bookTitle.setText(book.getName());
//        holder.binding.bookPrice.setText(String.format("%,.0f VND", book.getPrice()));
//        Glide.with(holder.binding.getRoot())
//                .load(Uri.parse(book.getImage()))
//                .into(holder.binding.bookImage);
//        holder.binding.bookImage.set(book.getImage());

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

        public void bind(Book book) {
            String title = book.getName();
            if (title.length() > 15) {
                title = title.substring(0, 15) + "...";
            }
            binding.bookTitle.setText(title);
            NumberFormat numberFormat = NumberFormat.getInstance();
            binding.bookPrice.setText(numberFormat.format(book.getPrice()) + " VND");
            binding.bookRating.setText(String.valueOf(book.getRating()));
            binding.reviewsCount.setText(String.valueOf(book.getNumReviews()));
            binding.bookId.setText(book.getId());
            Glide.with(binding.bookImage.getContext())
                    .load(Uri.parse(book.getImage()))
                    .into(binding.bookImage);
        }
    }

}