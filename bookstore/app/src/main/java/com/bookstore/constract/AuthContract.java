package com.bookstore.constract;

public interface AuthContract {
    interface View {
        void showSignInSuccess();
        void showSignUpSuccess();
        void showError(String message);
    }

    public interface Presenter {
        void signIn(String username, String password);
        void signUp(String username, String fullname, String email, String password, String confirmPassword, String role);
    }
}