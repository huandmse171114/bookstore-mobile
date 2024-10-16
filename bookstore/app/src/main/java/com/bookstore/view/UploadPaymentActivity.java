package com.bookstore.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bookstore.R;
import com.bookstore.constract.UploadPaymentContract;
import com.bookstore.databinding.UploadPaymentBinding;
import com.bookstore.model.UploadPaymentModel;
import com.bookstore.presenter.UploadPaymentPresenter;

public class UploadPaymentActivity extends AppCompatActivity implements UploadPaymentContract.View {

    private UploadPaymentBinding binding;
    private UploadPaymentContract.Presenter presenter;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);

        binding = UploadPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new UploadPaymentPresenter(new UploadPaymentModel(this), this);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.uploadPayment.getId()), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // On pressing btnChoose
        binding.btnChoose.setOnClickListener(v -> showSelectImageScreen());

        // On pressing btnUpload
        binding.btnUpload.setOnClickListener(v -> uploadImage());
    }

    private void uploadImage() {
        if (filePath != null) {
            presenter.handleUploadBtn(filePath);
        }
    }

    @Override
    public void showSelectImageScreen() {
        // Defining Implicit intent to mobile gallery.
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select image from here..."), PICK_IMAGE_REQUEST);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSelectedImage() {

    }

    @Override
    public void showProcessDialog() {
        // code for showing progressDialog while uploading
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
    }

    @Override
    public void setProcessDialogMessage(String message) {
        progressDialog.setMessage(message);
    }

    @Override
    public void hideProcessDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void setGPTResponse(String response) {
        binding.convertedText.setText(response);
    }

    @Override
    public void setOCRResponse(String response) {
        binding.gptResponse.setText(response);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // get the uri of data
            filePath = data.getData();
        }
    }
}
