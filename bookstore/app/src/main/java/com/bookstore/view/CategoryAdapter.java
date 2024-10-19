package com.bookstore.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.R;
import com.bookstore.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private OnCategoryClickListener listener;
    private int selectedPosition = -1;

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    public CategoryAdapter(List<Category> categories, OnCategoryClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoryButton.setText(category.getName());


        // Kiểm tra nếu item được chọn
        if (selectedPosition == position) {
            // Thay đổi viền và nền khi item được chọn
            holder.itemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_selected));
            holder.categoryButton.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        } else {
            // Nếu không được chọn thì chỉ hiện text
            holder.itemView.setBackground(null); // Không viền
            holder.categoryButton.setBackground(null); // Không nền
            holder.categoryButton.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }
        holder.categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousPosition = selectedPosition;
                selectedPosition = position;

                // Update only the previous and new selected item
                notifyItemChanged(previousPosition);
                notifyItemChanged(selectedPosition);

                listener.onCategoryClick(category);
            }
        });

//        holder.heartIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isFavorite = (boolean) holder.heartIcon.getTag();
//                if (isFavorite) {
//                    holder.heartIcon.setImageResource(R.drawable.ic_heart_before_click);
//                    Toast.makeText(holder.itemView.getContext(), "Loại bỏ khỏi mục yêu thích", Toast.LENGTH_SHORT).show();
//                } else {
//                    holder.heartIcon.setImageResource(R.drawable.ic_heart_after_click);
//                    Toast.makeText(holder.itemView.getContext(), "Đã thêm vào mục yêu thích", Toast.LENGTH_SHORT).show();
//                }
//                holder.heartIcon.setTag(!isFavorite);
//            }
//        });
//
//        // Set default tag value
//        holder.heartIcon.setTag(false);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        Button categoryButton;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryButton = itemView.findViewById(R.id.categoryButton);

        }
    }

//    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
//        Button categoryButton;
//        ImageView heartIcon;
//
//        public CategoryViewHolder(@NonNull View itemView) {
//            super(itemView);
//            categoryButton = itemView.findViewById(R.id.categoryButton);
//            heartIcon = itemView.findViewById(R.id.heartIcon);
//        }
//    }
}

