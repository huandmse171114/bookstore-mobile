package com.bookstore.presenter;
import com.bookstore.R;
import com.bookstore.model.Category;
import com.bookstore.model.Book;
import com.bookstore.view.CategoryView;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter {
    private CategoryView view;


    public CategoryPresenter(CategoryView view) {
        this.view = view;

    }

    public void loadCategories() {
        // Simulate data loading, could be from an API or database
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Self-Help"));
        categories.add(new Category(2, "Business"));
        categories.add(new Category(3, "Investment"));
        categories.add(new Category(4, "Technology"));
        categories.add(new Category(5, "Science"));
        categories.add(new Category(6, "History"));
        categories.add(new Category(7, "Biography"));
        categories.add(new Category(8, "Fiction"));
        categories.add(new Category(9, "Romance"));
        categories.add(new Category(10, "Fantasy"));
        categories.add(new Category(11, "Mystery"));
        categories.add(new Category(12, "Thriller"));
        categories.add(new Category(13, "Horror"));
        categories.add(new Category(14, "Children"));

        view.showCategories(categories);
    }

    public void onCategorySelected(Category category) {
        // Handle what happens when a category is clicked
//        view.showMessage("Selected: " + category.getName());

    }

    private List<Category> loadCategoriesFromDataSource() {
        // Replace with logic to load categories from your data source
        return new ArrayList<>(); // Placeholder for categories loading
    }
}

