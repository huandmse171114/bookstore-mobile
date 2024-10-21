package com.bookstore.model;

import java.util.List;

public class ProductDetailResponse {
    private List<BookDetail> products;

    public java.util.List<BookDetail> getProducts() {
        return products;
    }

    public void setProducts(List<BookDetail> products) {
        this.products = products;
    }
}
