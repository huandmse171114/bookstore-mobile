package com.bookstore.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bookstore.MainActivity;
import com.bookstore.R;
import com.bookstore.api.AuthApi;
import com.bookstore.presenter.AuthPresenter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthActivity extends AppCompatActivity implements com.bookstore.constract.AuthContract.View {

    private EditText usernameField, passwordField, emailField, confirmPasswordField;
    private Button signInButton, signUpButton;
    private TextView toggleText;
    private AuthPresenter presenter;
    private boolean isSignIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadSignInLayout();

        // Khởi tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bookstore-api-nodejs.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthApi authApi = retrofit.create(AuthApi.class);
        presenter = new AuthPresenter(this, authApi);
    }

    private void loadSignInLayout() {
        setContentView(R.layout.signin_layout); // Load layout đăng nhập

        // Khởi tạo các trường nhập liệu cho đăng nhập
        usernameField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        signInButton = findViewById(R.id.signinbtn);
        toggleText = findViewById(R.id.didntaccount);

        // Thiết lập sự kiện cho nút đăng nhập
        signInButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            presenter.signIn(username, password);
        });

        // Thiết lập sự kiện cho chuyển đổi sang đăng ký
        toggleText.setOnClickListener(v -> {
            isSignIn = false;
            loadSignUpLayout();
        });
    }

    private void loadSignUpLayout() {
        setContentView(R.layout.signup_layout); // Load layout đăng ký

        // Khởi tạo các trường nhập liệu cho đăng ký
        usernameField = findViewById(R.id.username);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        confirmPasswordField = findViewById(R.id.cfpassword);
        signUpButton = findViewById(R.id.signupbtn);
        toggleText = findViewById(R.id.alreadyaccount);

        // Thiết lập sự kiện cho nút đăng ký
        signUpButton.setOnClickListener(v -> {
            String username = usernameField.getText().toString().trim();
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String confirmPassword = confirmPasswordField.getText().toString().trim();

            // Kiểm tra mật khẩu
            if (password.equals(confirmPassword)) {
                presenter.signUp(username,email, password, confirmPassword);
            } else {
                showError("Passwords do not match");
            }
        });

        // Thiết lập sự kiện cho chuyển đổi sang đăng nhập
        toggleText.setOnClickListener(v -> {
            isSignIn = true;
            loadSignInLayout();
        });
    }

    @Override
    public void showSignInSuccess() {
        Toast.makeText(this, "Sign-in successful", Toast.LENGTH_SHORT).show();
        // Chuyển đến MainActivity sau khi đăng nhập thành công
        Intent intent = new Intent(this, MainActivity.class);
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
