package com.bookstore.api;
import com.bookstore.model.Book;
import com.bookstore.model.BookRequest;
import com.bookstore.model.Category;
import com.bookstore.model.SignInRequest;
import com.bookstore.model.SignInResponse;
import com.bookstore.model.SignUpRequest;
import com.bookstore.model.SignUpResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AuthApi {
    @POST("/api/users/auth")
    Call<SignInResponse> signIn(@Body SignInRequest request);

    @POST("/api/users/register")
    Call<SignUpResponse> signUp(@Body SignUpRequest request);

    @GET("/api/category/categories")
    Call<List<Category>> getCategories();

    @GET("/api/products")
    Call<BookRequest> getBooks(@Query("keyword") Integer categoryId);
}


