package com.bookstore.model;

import java.io.Serializable;

public class PaymentGPTResponse implements Serializable {
    private String senderName;
    private String senderBank;
    private String senderAccount;
    private String receiverName;
    private String receiverBank;
    private String receiverAccount;
    private String date;
    private String totalAmount;
    private String message;

    public PaymentGPTResponse(String totalAmount, String date,
                  String receiverAccount, String receiverBank, String receiverName,
                  String senderAccount, String senderBank, String senderName,
                              String message) {
        this.totalAmount = totalAmount;
        this.date = date;
        this.receiverAccount = receiverAccount;
        this.receiverBank = receiverBank;
        this.receiverName = receiverName;
        this.senderAccount = senderAccount;
        this.senderBank = senderBank;
        this.senderName = senderName;
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderBank() {
        return senderBank;
    }

    public void setSenderBank(String senderBank) {
        this.senderBank = senderBank;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverBank() {
        return receiverBank;
    }

    public void setReceiverBank(String receiverBank) {
        this.receiverBank = receiverBank;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
