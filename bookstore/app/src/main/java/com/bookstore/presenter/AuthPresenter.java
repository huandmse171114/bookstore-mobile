package com.bookstore.presenter;

import com.bookstore.api.AuthApi;
import com.bookstore.model.SignInRequest;
import com.bookstore.model.SignInResponse;
import com.bookstore.model.SignUpRequest;
import com.bookstore.model.SignUpResponse;
import com.bookstore.constract.AuthContract;

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

    @Override
    public void signIn(String username, String password) {
        SignInRequest request = new SignInRequest(username, password); // Create the SignInRequest object
        authApi.signIn(request).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
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



    @Override
    public void signUp(String username, String fullname, String email, String password, String confirmPassword, String role) {
        SignUpRequest request = new SignUpRequest(username, fullname, email, password, confirmPassword, role); // Create the SignUpRequest object
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
}
