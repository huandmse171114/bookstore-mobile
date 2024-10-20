package com.bookstore.CongfigAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient client = LoggingInterceptor.getClient(); // Get the logging interceptor client

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://bookstore-api-nodejs.onrender.com")
                    .client(client) // Set the client with the logging interceptor
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}