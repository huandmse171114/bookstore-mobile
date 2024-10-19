package com.bookstore.constract;

public interface AuthContract {
    interface View {
        void showSignInSuccess();  // Notify the user of successful sign-in
        void showSignUpSuccess();  // Notify the user of successful sign-up
        void showError(String message);  // Show error messages (e.g., validation, server issues)
    }

    interface Presenter {

        void signIn(String email, String password);

        void signUp(String username, String email, String password, String confirmPassword);
    }
}
