package com.bookstore.presenter;

import com.bookstore.MyApplication;
import com.bookstore.api.AuthApi;
import com.bookstore.contract.AuthContract;
import com.bookstore.model.SignInRequest;
import com.bookstore.model.SignInResponse;
import com.bookstore.model.SignUpRequest;
import com.bookstore.model.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthPresenter implements AuthContract.Presenter {
    private AuthContract.View view;
    private AuthApi authApi;

    public AuthPresenter(AuthContract.View view, AuthApi authApi) {
        this.view = view;
        this.authApi = authApi;
    }

    // Updated method to accept email instead of username
    @Override
    public void signIn(String email, String password) {
        SignInRequest request = new SignInRequest(email, password); // Updated parameter to email
        authApi.signIn(request).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //store user id
                    storeUserId(response.body().get_id());

                    view.showSignInSuccess();
                } else {
                    view.showError("Sign in failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                view.showError("Sign in failed: " + t.getMessage());
            }
        });
    }

    // Updated method signature for signUp
    @Override
    public void signUp(String username, String email, String password, String confirmPassword) {
        // Validate password and confirmPassword match
        if (!password.equals(confirmPassword)) {
            view.showError("Passwords do not match");
            return;
        }

        SignUpRequest request = new SignUpRequest(username, email, password); // Removed fullname
        authApi.signUp(request).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.isSuccessful()) {
                    view.showSignUpSuccess();
                } else {
                    view.showError("Sign-up failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                view.showError("Sign-up failed: " + t.getMessage());
            }
        });
    }

    private void storeUserId(String id) {
        MyApplication app = (MyApplication) view.getApplicationContext();
        app.setUserId(id);
    }
}
