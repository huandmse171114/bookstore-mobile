package com.bookstore.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bookstore.databinding.ItemLatestSearchBinding;

import java.util.List;

public class LatestSearchAdapter extends RecyclerView.Adapter<LatestSearchAdapter.ViewHolder> {

    private List<String> searches;

    public LatestSearchAdapter(List<String> searches) {
        this.searches = searches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemLatestSearchBinding binding = ItemLatestSearchBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvSearch.setText(searches.get(position));
        // Handle remove button click event
        holder.binding.btnRemove.setOnClickListener(v -> {
            searches.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, searches.size()); // Update item positions
        });
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemLatestSearchBinding binding;

        ViewHolder(ItemLatestSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}