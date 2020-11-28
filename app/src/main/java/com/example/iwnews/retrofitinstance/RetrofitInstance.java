package com.example.iwnews.retrofitinstance;

import com.example.iwnews.apiUrls.APIUrls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;

    public static Retrofit callNewsAPI() {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient
                    .Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
            Gson gson = new GsonBuilder().serializeNulls().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIUrls.newsAPI)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
