package com.bookstore.model;

public class PaymentBillCreateResponseData {

    private String senderName;
    private String senderBank;
    private String senderAccount;
    private String receivername;
    private String receiverBank;
    private String receiverAccount;
    private String date;
    private String amount;
    private String _id;
    private String createdAt;
    private String updatedAt;
    private String __v;

    public PaymentBillCreateResponseData(String senderName, String senderBank, String senderAccount, String receivername, String receiverBank, String receiverAccount, String date, String amount, String _id, String createdAt, String updatedAt, String __v) {
        this.senderName = senderName;
        this.senderBank = senderBank;
        this.senderAccount = senderAccount;
        this.receivername = receivername;
        this.receiverBank = receiverBank;
        this.receiverAccount = receiverAccount;
        this.date = date;
        this.amount = amount;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
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

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
