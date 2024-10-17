package com.bookstore.presenter;

import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bookstore.constract.UploadPaymentContract;
import com.bookstore.model.PaymentGPTResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.Instant;
import java.util.List;

public class UploadPaymentPresenter implements UploadPaymentContract.Presenter {

    private UploadPaymentContract.View view;
    private UploadPaymentContract.Model model;

    public UploadPaymentPresenter(UploadPaymentContract.Model model, UploadPaymentContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handleUploadBtn(Uri uri) {
        view.showProcessDialog();

        try {
            model.uploadToFirebaseStorage(this, uri);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProcessDialogMessage(double progressPercent) {
        view.setProcessDialogMessage("Uploaded " + (int) progressPercent + "%");
    }

    @Override
    public void handleOnSuccessGPTResponse(String response) {
        view.hideProcessDialog();
        view.showToastMessage("Uploaded Image Successfully! Information extracted.");
        String[] responseData = response.split(";");
        PaymentGPTResponse gptResponse = new PaymentGPTResponse(
                responseData[7],
                responseData[0],
                responseData[5],
                responseData[6],
                responseData[4],
                responseData[2],
                responseData[3],
                responseData[1]
        );
        view.redirectPaymentInfoConfirmActivity(gptResponse);
    }

    @Override
    public void handleOnShowImage(Uri uri) {
        view.showSelectedImage(uri);
    }
}
