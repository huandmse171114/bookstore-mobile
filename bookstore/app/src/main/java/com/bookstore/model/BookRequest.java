package com.bookstore.model;

import java.util.List;

public class BookRequest {
    private List<Book> products;

    public BookRequest(List<Book> products) {
        this.products = products;
    }

    public List<Book> getProducts() {
        return products;
    }

    public void setProducts(List<Book> products) {
        this.products = products;
    }
}
