package com.bookstore.constract;
import com.bookstore.model.Book;

import java.util.List;

public interface BookContract {
    void showBooks(List<Book> books);
    void showMessage(String message);
}
