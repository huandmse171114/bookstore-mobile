package com.bookstore.api;
import com.bookstore.model.SignInRequest;
import com.bookstore.model.SignInResponse;
import com.bookstore.model.SignUpRequest;
import com.bookstore.model.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/store/api/v1/auth/sign-in")
    Call<SignInResponse> signIn(@Body SignInRequest request);

    @POST("/store/api/v1/users/register")
    Call<SignUpResponse> signUp(@Body SignUpRequest request);
}

