package com.bookstore.api;

public class CartItem {
    private String productId; // Product ID
    private String name;      // Product Name
    private String image;     // Product Image URL
    private double price;     // Product Price
    private int quantity;     // Quantity

    // Constructor
    public CartItem(String productId, String name, String image, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
