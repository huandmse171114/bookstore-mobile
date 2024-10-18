package com.bookstore.presenter;

import com.bookstore.R;
import com.bookstore.model.Book;
import com.bookstore.view.BookView;

import java.util.ArrayList;
import java.util.List;

public class BookPresenter {
    private BookView view;
    private List<Book> allBooks;

    public BookPresenter(BookView view) {
        this.view = view;

    }

    public void loadBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Atomic Habits", R.drawable.img1));
        books.add(new Book(2, "The Power of Habit", R.drawable.img1));
        books.add(new Book(3, "Business Adventures", R.drawable.img1));
        books.add(new Book(4, "The Lean Startup", R.drawable.img1));
        books.add(new Book(5, "Rich Dad Poor Dad", R.drawable.img1));
        books.add(new Book(6, "Zero to One", R.drawable.img1));
        books.add(new Book(7, "Sapiens", R.drawable.img1));
        books.add(new Book(8, "The Book Thief", R.drawable.img1));
        books.add(new Book(9, "The Alchemist", R.drawable.img1));
        books.add(new Book(10, "Pride and Prejudice", R.drawable.img1));
        books.add(new Book(11, "The Notebook", R.drawable.img1));

        view.showBooks(books);
    }


}
