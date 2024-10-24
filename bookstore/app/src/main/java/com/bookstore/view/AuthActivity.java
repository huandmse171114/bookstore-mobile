package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bookstore.R;
import com.bookstore.api.AuthApi;
import com.bookstore.api.RetrofitClient;
import com.bookstore.contract.AuthContract;
import com.bookstore.databinding.SigninLayoutBinding;
import com.bookstore.databinding.SignupLayoutBinding;
import com.bookstore.presenter.AuthPresenter;

import retrofit2.Retrofit;

public class AuthActivity extends AppCompatActivity implements AuthContract.View {

    private SigninLayoutBinding signinBinding; // For sign-in layout binding
    private SignupLayoutBinding signupBinding; // For sign-up layout binding
    private AuthPresenter presenter;
    private boolean isSignIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadSignInLayout();

        // Use RetrofitClient to create an instance of AuthApi
        Retrofit retrofit = RetrofitClient.getClient();
        AuthApi authApi = retrofit.create(AuthApi.class);
        presenter = new AuthPresenter(this, authApi);
    }

    private void loadSignInLayout() {
        signinBinding = SigninLayoutBinding.inflate(getLayoutInflater()); // Initialize view binding for sign-in
        setContentView(signinBinding.getRoot()); // Set the root view

        // Set click listener for sign-in button
        signinBinding.signinbtn.setOnClickListener(v -> {
            String username = signinBinding.email.getText().toString().trim();
            String password = signinBinding.password.getText().toString().trim();
            presenter.signIn(username, password);
        });

        // Set click listener for switching to sign up
        signinBinding.didntaccount.setOnClickListener(v -> {
            isSignIn = false;
            loadSignUpLayout();
        });
    }

    private void loadSignUpLayout() {
        signupBinding = SignupLayoutBinding.inflate(getLayoutInflater()); // Initialize view binding for sign-up
        setContentView(signupBinding.getRoot()); // Set the root view

        // Set click listener for sign-up button
        signupBinding.signupbtn.setOnClickListener(v -> {
            String username = signupBinding.username.getText().toString().trim();
            String email = signupBinding.email.getText().toString().trim();
            String password = signupBinding.password.getText().toString().trim();
            String confirmPassword = signupBinding.cfpassword.getText().toString().trim();

            // Check password confirmation
            if (password.equals(confirmPassword)) {
                presenter.signUp(username, email, password, confirmPassword);
            } else {
                showError("Passwords do not match");
            }
        });

        // Set click listener for switching back to sign-in
        signupBinding.alreadyaccount.setOnClickListener(v -> {
            isSignIn = true;
            loadSignInLayout();
        });
    }

    @Override
    public void showSignInSuccess() {
        Toast.makeText(this, "Sign-in successful", Toast.LENGTH_SHORT).show();
        // Redirect to HomePageActivity after successful sign in
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showSignUpSuccess() {
        Toast.makeText(this, "Sign-up successful", Toast.LENGTH_SHORT).show();
        // Redirect back to the sign-in page
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
