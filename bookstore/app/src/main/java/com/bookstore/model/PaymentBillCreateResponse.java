package com.bookstore.model;

public class PaymentBillCreateResponse {

    private String success;
    private String message;
    private PaymentBillCreateResponseData data;

    public PaymentBillCreateResponse(String success, String message, PaymentBillCreateResponseData data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentBillCreateResponseData getData() {
        return data;
    }

    public void setData(PaymentBillCreateResponseData data) {
        this.data = data;
    }

}
