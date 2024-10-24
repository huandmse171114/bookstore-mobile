package com.bookstore.model;

import java.util.List;

public class OrderProfile {
    private OrderCreateResponse order;
    private List<CartItem> items;

    public OrderProfile(OrderCreateResponse order, List<CartItem> items) {
        this.order = order;
        this.items = items;
    }

    public OrderCreateResponse getOrder() {
        return order;
    }

    public void setOrder(OrderCreateResponse order) {
        this.order = order;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
