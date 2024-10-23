package com.bookstore.contract;

import android.net.Uri;

import com.bookstore.model.PaymentGPTResponse;

import java.io.IOException;

public interface UploadPaymentContract {

    interface View {
        void showSelectImageScreen();
        void showToastMessage(String message);
        void showSelectedImage(Uri uri);
        void showProcessDialog();
        void setProcessDialogMessage(String message);
        void hideProcessDialog();
        void redirectPaymentInfoConfirmActivity(PaymentGPTResponse response);

    }

    interface Model {
        void uploadToFirebaseStorage(UploadPaymentContract.Presenter presenter, Uri imageUri) throws IOException;
    }

    interface Presenter {
        void handleUploadBtn(Uri uri);
        void updateProcessDialogMessage(double progressPercent);
        void handleOnSuccessGPTResponse(String response);
        void handleOnShowImage(Uri uri);
    }

}
