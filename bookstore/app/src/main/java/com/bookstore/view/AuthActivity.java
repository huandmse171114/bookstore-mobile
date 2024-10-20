package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bookstore.MainActivity;
import com.bookstore.R;
import com.bookstore.api.AuthApi;
import com.bookstore.presenter.AuthPresenter;
import com.bookstore.databinding.SigninLayoutBinding; // Import the generated binding class for sign-in layout
import com.bookstore.databinding.SignupLayoutBinding; // Import the generated binding class for sign-up layout

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthActivity extends AppCompatActivity implements com.bookstore.constract.AuthContract.View {

    private SigninLayoutBinding signInBinding; // View binding for sign-in layout
    private SignupLayoutBinding signUpBinding; // View binding for sign-up layout
    private AuthPresenter presenter;
    private boolean isSignIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadSignInLayout();

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bookstore-api-nodejs.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthApi authApi = retrofit.create(AuthApi.class);
        presenter = new AuthPresenter(this, authApi);
    }

    private void loadSignInLayout() {
        signInBinding = SigninLayoutBinding.inflate(getLayoutInflater()); // Inflate the sign-in layout
        setContentView(signInBinding.getRoot()); // Set the content view to the root of the binding

        // Set up sign-in button click event
        signInBinding.signinbtn.setOnClickListener(v -> {
            String email = signInBinding.email.getText().toString().trim(); // Access email field from binding
            String password = signInBinding.password.getText().toString().trim(); // Access password field from binding
            presenter.signIn(email, password);
        });

        // Switch to sign-up layout
        signInBinding.didntaccount.setOnClickListener(v -> {
            isSignIn = false;
            loadSignUpLayout();
        });
    }

    private void loadSignUpLayout() {
        signUpBinding = SignupLayoutBinding.inflate(getLayoutInflater()); // Inflate the sign-up layout
        setContentView(signUpBinding.getRoot()); // Set the content view to the root of the binding

        // Set up sign-up button click event
        signUpBinding.signupbtn.setOnClickListener(v -> {
            String username = signUpBinding.username.getText().toString().trim(); // Access username field from binding
            String email = signUpBinding.email.getText().toString().trim(); // Access email field from binding
            String password = signUpBinding.password.getText().toString().trim(); // Access password field from binding
            String confirmPassword = signUpBinding.cfpassword.getText().toString().trim(); // Access confirm password field from binding

            // Validate passwords match
            if (password.equals(confirmPassword)) {
                presenter.signUp(username, email, password, confirmPassword);
            } else {
                showError("Passwords do not match");
            }
        });

        // Switch back to sign-in layout
        signUpBinding.alreadyaccount.setOnClickListener(v -> {
            isSignIn = true;
            loadSignInLayout();
        });
    }

    @Override
    public void showSignInSuccess() {
        Toast.makeText(this, "Sign-in successful", Toast.LENGTH_SHORT).show();
        // Redirect to MainActivity after successful sign-in
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showSignUpSuccess() {
        Toast.makeText(this, "Sign-up successful", Toast.LENGTH_SHORT).show();
        // Redirect back to sign-in page after successful sign-up
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
