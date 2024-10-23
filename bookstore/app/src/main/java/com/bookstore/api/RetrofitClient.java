package com.bookstore.api;

import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            // Cookie Manager to handle cookies
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL); // Accept all cookies

            // Logging Interceptor to log the HTTP request/response
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // Logs headers, body, and cookies

            // OkHttpClient setup
            OkHttpClient client = new OkHttpClient.Builder()
                    .cookieJar(new JavaNetCookieJar(cookieManager)) // Manage cookies
                    .addInterceptor(loggingInterceptor) // Add logging interceptor
                    .build();

            // Retrofit setup
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://bookstore-api-nodejs.onrender.com") // Base URL
                    .client(client) // OkHttpClient with logging
                    .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON parsing
                    .build();
        }
        return retrofit;
    }
}
