package com.bookstore.presenter;

import com.bookstore.model.Book;
import com.bookstore.contract.BookContract;

import java.util.List;

public class BookPresenter {
    private BookContract view;
    private List<Book> allBooks;

    public BookPresenter(BookContract view) {
        this.view = view;

    }


//    public void loadBooks() {
//        List<Book> books = new ArrayList<>();
//        books.add(new Book(1, "Atomic Habits", 80000, R.drawable.img1));
//        books.add(new Book(2, "The Power of Habit", 80000, R.drawable.img1));
//        books.add(new Book(3, "Business Adventures", 80000, R.drawable.img1));
//        books.add(new Book(4, "The Lean Startup", 80000, R.drawable.img1));
//        books.add(new Book(5, "Rich Dad Poor Dad", 80000, R.drawable.img1));
//        books.add(new Book(6, "Zero to One", 80000, R.drawable.img1));
//        view.showBooks(books);
//    }
//
//    public void loadOtherBooks() {
//        List<Book> books = new ArrayList<>();
//        books.add(new Book(1, "Atomic Habits", 80000, R.drawable.img1));
//        books.add(new Book(2, "The Power of Habit", 80000, R.drawable.img1));
//        view.showBooks(books);
//    }


}
