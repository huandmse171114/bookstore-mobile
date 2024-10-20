package com.bookstore.model;

import java.util.List;

public class OrderItemsGetResponse {

    private boolean success;
    private List<OrderItemData> data;

    public OrderItemsGetResponse(boolean success, List<OrderItemData> data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<OrderItemData> getData() {
        return data;
    }

    public void setData(List<OrderItemData> data) {
        this.data = data;
    }
}
