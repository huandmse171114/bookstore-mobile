package com.bookstore.presenter;

import com.bookstore.contract.SuccessPaymentContract;

public class SuccessPaymentPresenter implements SuccessPaymentContract.Presenter {

    private SuccessPaymentContract.View view;
    private SuccessPaymentContract.Model model;

    public SuccessPaymentPresenter(SuccessPaymentContract.View view, SuccessPaymentContract.Model model) {
        this.view = view;
        this.model = model;
    }


}
