package com.bookstore.constract;

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

    }

}
