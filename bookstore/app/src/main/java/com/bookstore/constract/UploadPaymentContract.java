package com.bookstore.constract;

import android.net.Uri;

import java.io.IOException;

public interface UploadPaymentContract {

    interface View {
        void showSelectImageScreen();
        void showToastMessage(String message);
        void showSelectedImage();
        void showProcessDialog();
        void setProcessDialogMessage(String message);
        void hideProcessDialog();
        void setGPTResponse(String response);
        void setOCRResponse(String response);
    }

    interface Model {
        void uploadToFirebaseStorage(UploadPaymentContract.Presenter presenter, Uri imageUri) throws IOException;
    }

    interface Presenter {
        void handleUploadBtn(Uri uri);
        void updateProcessDialogMessage(double progressPercent);
        void handleOnSuccessGPTResponse(String response);
    }

}
