package com.bookstore.presenter;

import com.bookstore.constract.PaymentInfoConfirmContract;
import com.bookstore.view.PaymentInfoConfirmActivity;

public class PaymentInfoConfirmPresenter implements PaymentInfoConfirmContract.Presenter {
    private PaymentInfoConfirmContract.Model model;
    private PaymentInfoConfirmContract.View view;

    public PaymentInfoConfirmPresenter(PaymentInfoConfirmContract.View view, PaymentInfoConfirmContract.Model model) {
        this.view = view;
        this.model = model;
    }


}
