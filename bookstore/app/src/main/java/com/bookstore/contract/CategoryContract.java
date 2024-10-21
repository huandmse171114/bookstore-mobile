
package com.bookstore.contract;

import com.bookstore.model.Book;
import com.bookstore.model.Category;

import java.util.List;

public interface CategoryContract {
    void showCategories(List<Category> categories);
    void showMessage(String message);
    void showBooks(List<Book> books);
}
