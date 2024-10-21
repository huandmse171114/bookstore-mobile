package com.bookstore.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bookstore.contract.UploadPaymentContract;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadPaymentModel implements UploadPaymentContract.Model {

    private OkHttpClient okHttpClient;
    private final String stringURLEndPointGPT = "https://api.openai.com/v1/chat/completions";
    private final String stringAPIKey = "";
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Context context;

    public UploadPaymentModel(Context context) {
        this.context = context;
        okHttpClient = new OkHttpClient();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
    }

    @Override
    public void uploadToFirebaseStorage(UploadPaymentContract.Presenter presenter, Uri imageUri) throws IOException {
        byte[] compressedData = compressImageToUnder1MB(imageUri);

        String fileName = Instant.now().toString();

        // Defining the child of storage reference.
        StorageReference ref = storageReference.child("images/" + fileName);

        // adding listener on upload
        // or failure of image
        ref.putBytes(compressedData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // Points to the root reference
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference dataRef = storageReference.child("/images/" + fileName);

                dataRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // do something with download url
                        int SDK_INT = Build.VERSION.SDK_INT;
                        if (SDK_INT > 8) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                        }

                        try {
                            sendPostOCR(presenter, uri.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Error, image not uploaded
                e.printStackTrace();
                Log.d("ERROR", e.getMessage());
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                // progress listener for dialog.
                // percentage on the dialog box.
                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                presenter.updateProcessDialogMessage(progress);
            }
        });
    }

    private byte[] compressImageToUnder1MB(Uri imageUri) throws IOException {
        // Step 1: Load the bitmap from the file
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);

        // Step 2: Scale the bitmap down if necessary (optional, but helps reduce size)
        int maxWidth = 1024; // Set your desired maximum width
        int maxHeight = 1024; // Set your desired maximum height
        bitmap = scaleBitmapDown(bitmap, maxWidth, maxHeight);

        // Step 3: Compress the bitmap into JPEG format and reduce quality
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100; // Start with highest quality (100%)
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

        // Step 4: Loop to reduce quality until it's under 1MB (1024KB)
        while (baos.toByteArray().length / 1024 > 1024 && quality > 10) {
            // Clear the stream
            baos.reset();
            quality -= 5; // Decrease quality by 5%
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        }

        // Step 5: Return the byte array (compressed image)
        return baos.toByteArray();
    }

    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxWidth, int maxHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float aspectRatio = (float) width / height;
        if (width > height) {
            width = maxWidth;
            height = Math.round(maxWidth / aspectRatio);
        } else {
            height = maxHeight;
            width = Math.round(maxHeight * aspectRatio);
        }

        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    private String generatePrompt(String convertedText) {
        return "Extract these information (transaction time, sender name, sender bank account number, sender bank name, receiver name, receiver bank account number,  receiver bank name, transaction time, total amount, message from sender) from the given text: "
                + convertedText + " into this format: [transaction time];[sender name];[sender bank account number];[sender bank name];[receiver name];[receiver bank account number];[receiver bank name];[total amount];[message from sender]. Return only the given format, no more extras character.";

    }

    private void sendPostOCR(UploadPaymentContract.Presenter presenter, String imageUrl) throws Exception {
        // Form parameters
        Log.d("Uri_Tag", imageUrl + ".jpg");
        RequestBody formBody = new FormBody.Builder()
                .add("isOverlayRequired", "false")
                .add("filetype", "JPG")
                .add("url", imageUrl)
                .add("scale", "true")
                .add("iscreatesearchablepdf", "false")
                .add("issearchablepdfhidetextlayer", "false")
                .add("OCREngine", "2")
                .build();

        okhttp3.Request request = new Request.Builder()
                .url("https://api.ocr.space/parse/image")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("apikey", "K87640058388957")
                .post(formBody)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);

            // get response body
            String parsedResults = "";
            String res = response.body().string();
            JSONObject obj = new JSONObject(res);
            JSONArray jsonArray = (JSONArray) obj.get("ParsedResults");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                parsedResults = jsonObject.get("ParsedText").toString();
            }

            sendPostGPT(presenter, parsedResults);
        }
    }

    private void sendPostGPT(UploadPaymentContract.Presenter presenter, String convertedText) throws Exception {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArrayMessage = new JSONArray();

        try {
            JSONObject jsonObjectMessage = new JSONObject();
            JSONObject jsonObjectMessage2 = new JSONObject();

            jsonObjectMessage.put("role", "system");
            jsonObjectMessage.put("content", "You are a helpful assistant.");

            jsonObjectMessage2.put("role", "user");
            jsonObjectMessage2.put("content", generatePrompt(convertedText));

            jsonArrayMessage.put(jsonObjectMessage);
            jsonArrayMessage.put(jsonObjectMessage2);

            jsonObject.put("model", "gpt-4o");
            jsonObject.put("messages", jsonArrayMessage);

            // Create a request body with JSON content
            RequestBody requestBody = RequestBody.create(
                    jsonObject.toString(),
                    MediaType.parse("application/json; charset=utf-8")
            );

            Request request = new Request.Builder()
                    .url(stringURLEndPointGPT)
                    .addHeader("Authorization", "Bearer " + stringAPIKey)
                    .addHeader("Content-Type", "application/json")
                    .post(requestBody)
                    .build();

            // Executing the request
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    JSONObject responseObject = new JSONObject(response.body().string());
                    JSONArray choicesArray = responseObject.getJSONArray("choices");
                    JSONObject choiceObject = choicesArray.getJSONObject(0);  // Get the first choice
                    JSONObject messageObject = choiceObject.getJSONObject("message");
                    String extractedText = messageObject.getString("content");
                    presenter.handleOnSuccessGPTResponse(extractedText);
                } else {
                    throw new IOException("Unexpected code: " + response.code());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}
