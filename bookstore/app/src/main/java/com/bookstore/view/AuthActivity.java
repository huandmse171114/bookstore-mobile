package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bookstore.R;
import com.bookstore.api.AuthApi;
import com.bookstore.api.RetrofitClient;
import com.bookstore.contract.AuthContract;
import com.bookstore.presenter.AuthPresenter;

import retrofit2.Retrofit;

public class AuthActivity extends AppCompatActivity implements AuthContract.View {

    private EditText usernameField, passwordField, emailField, confirmPasswordField;
    private Button signInButton, signUpButton;
    private TextView toggleText;
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
        setContentView(R.layout.signin_layout); // Load login layout

        // Initialize input fields for sign in
        usernameField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        signInButton = findViewById(R.id.signinbtn);
        toggleText = findViewById(R.id.didntaccount);

        // Set click listener for sign in button
        signInButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            presenter.signIn(username, password);
        });

        // Set click listener for switching to sign up
        toggleText.setOnClickListener(v -> {
            isSignIn = false;
            loadSignUpLayout();
        });
    }

    private void loadSignUpLayout() {
        setContentView(R.layout.signup_layout); // Load sign up layout

        // Initialize input fields for sign up
        usernameField = findViewById(R.id.username);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        confirmPasswordField = findViewById(R.id.cfpassword);
        signUpButton = findViewById(R.id.signupbtn);
        toggleText = findViewById(R.id.alreadyaccount);

        // Set click listener for sign up button
        signUpButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString().trim();
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String confirmPassword = confirmPasswordField.getText().toString().trim();

            // Check password confirmation
            if (password.equals(confirmPassword)) {
                presenter.signUp(username, email, password, confirmPassword);
            } else {
                showError("Passwords do not match");
            }
        });

        // Set click listener for switching back to sign in
        toggleText.setOnClickListener(v -> {
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
