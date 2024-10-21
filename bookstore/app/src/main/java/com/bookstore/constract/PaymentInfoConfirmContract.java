package com.bookstore.constract;

import com.bookstore.model.PaymentGPTResponse;

public interface PaymentInfoConfirmContract {

    interface View {
        void showProcessDialog();
        void setProcessDialogMessage(String message);
        void hideProcessDialog();
        void showToastMessage(String message);
        void redirectSuccessPaymentActivity();
    }

    interface Model {

    }

    interface Presenter {
        String createOrder(PaymentGPTResponse gptResponse);
    }

}
