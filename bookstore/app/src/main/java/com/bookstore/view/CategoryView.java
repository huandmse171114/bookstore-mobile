
package com.bookstore.view;

import com.bookstore.model.Category;
import com.bookstore.model.Book;

import java.util.List;

public interface CategoryView {
    void showCategories(List<Category> categories);
    void showMessage(String message);
}
