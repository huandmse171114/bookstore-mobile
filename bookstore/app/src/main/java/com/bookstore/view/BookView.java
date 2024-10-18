package com.bookstore.view;
import com.bookstore.model.Book;

import java.util.List;

public interface BookView {
    void showBooks(List<Book> books);
    void showMessage(String message);
}
