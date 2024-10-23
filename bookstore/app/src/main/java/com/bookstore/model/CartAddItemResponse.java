package com.bookstore.model;

public class CartAddItemResponse {
    private String _id;
    private String name;
    private String user;
    private int qty;
    private String image;
    private int price;
    private String product;
    private String createAt;
    private String updateAt;


    public CartAddItemResponse(String _id, String name, String user, int qty, String image, int price, String product, String createAt, String updateAt) {
        this._id = _id;
        this.name = name;
        this.user = user;
        this.qty = qty;
        this.image = image;
        this.price = price;
        this.product = product;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
