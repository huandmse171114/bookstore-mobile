package com.bookstore.view;

import com.bookstore.model.SearchBook;
import java.util.List;
public class SearchBookResponse  {
    private List<SearchBook> products;

    public List<SearchBook> getProducts() {
        return products;
    }

    public void setProducts(List<SearchBook> products) {
        this.products = products;
    }
}