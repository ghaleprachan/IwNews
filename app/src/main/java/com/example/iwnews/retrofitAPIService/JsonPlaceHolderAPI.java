package com.example.iwnews.retrofitAPIService;

import com.example.iwnews.models.NewsModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderAPI {
    @GET("top-headlines")
    Call<NewsModel> getNews(@QueryMap Map<String, String> parameters);
}
