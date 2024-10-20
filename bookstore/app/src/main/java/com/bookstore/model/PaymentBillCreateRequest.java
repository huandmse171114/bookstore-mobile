package com.bookstore.model;

public class PaymentBillCreateRequest {

    private String senderName;
    private String senderBank;
    private String senderAccount;
    private String receiverName;
    private String receiverBank;
    private String receiverAccount;
    private String date;
    private String amount;

    public PaymentBillCreateRequest(String senderName, String senderBank, String senderAccount, String receivername, String receiverBank, String receiverAccount, String date, String amount) {
        this.senderName = senderName;
        this.senderBank = senderBank;
        this.senderAccount = senderAccount;
        this.receiverName = receivername;
        this.receiverBank = receiverBank;
        this.receiverAccount = receiverAccount;
        this.date = date;
        this.amount = amount;
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
        return receiverName;
    }

    public void setReceivername(String receivername) {
        this.receiverName = receivername;
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
}
