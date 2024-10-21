package com.bookstore.model;

public class Cart {
    private int id;
    private String bookTitle;
    private String bookAuthor;
    private String bookCoverImg;
    private int bookPackageId;
    private String bookPackageTitle;
    private String bookPackageDescription;
    private double bookPackagePrice;
    private int bookPackageStock;
    private int quantity;
    private String lastUpdatedTime;

    // Constructor
    public Cart(int id, String bookTitle, String bookAuthor, String bookCoverImg,
                int bookPackageId, String bookPackageTitle, String bookPackageDescription,
                double bookPackagePrice, int bookPackageStock, int quantity,
                String lastUpdatedTime) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookCoverImg = bookCoverImg;
        this.bookPackageId = bookPackageId;
        this.bookPackageTitle = bookPackageTitle;
        this.bookPackageDescription = bookPackageDescription;
        this.bookPackagePrice = bookPackagePrice;
        this.bookPackageStock = bookPackageStock;
        this.quantity = quantity;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getBookAuthor() { return bookAuthor; }
    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }

    public String getBookCoverImg() { return bookCoverImg; }
    public void setBookCoverImg(String bookCoverImg) { this.bookCoverImg = bookCoverImg; }

    public int getBookPackageId() { return bookPackageId; }
    public void setBookPackageId(int bookPackageId) { this.bookPackageId = bookPackageId; }

    public String getBookPackageTitle() { return bookPackageTitle; }
    public void setBookPackageTitle(String bookPackageTitle) { this.bookPackageTitle = bookPackageTitle; }

    public String getBookPackageDescription() { return bookPackageDescription; }
    public void setBookPackageDescription(String bookPackageDescription) { this.bookPackageDescription = bookPackageDescription; }

    public double getBookPackagePrice() { return bookPackagePrice; }
    public void setBookPackagePrice(double bookPackagePrice) { this.bookPackagePrice = bookPackagePrice; }

    public int getBookPackageStock() { return bookPackageStock; }
    public void setBookPackageStock(int bookPackageStock) { this.bookPackageStock = bookPackageStock; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getLastUpdatedTime() { return lastUpdatedTime; }
    public void setLastUpdatedTime(String lastUpdatedTime) { this.lastUpdatedTime = lastUpdatedTime; }
}

