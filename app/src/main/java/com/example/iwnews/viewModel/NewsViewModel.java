package com.example.iwnews.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iwnews.apiUrls.APIUrls;
import com.example.iwnews.models.NewsModel;
import com.example.iwnews.retrofitAPIService.JsonPlaceHolderAPI;
import com.example.iwnews.retrofitinstance.RetrofitInstance;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class NewsViewModel extends ViewModel {
    public static MutableLiveData<NewsModel> newsLD = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isFinished = new MutableLiveData<>();

    @EverythingIsNonNull
    public void makeApiCall() {
        isFinished.setValue(false);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("country", "us");
        parameters.put("category", "business");
        parameters.put("apiKey", APIUrls.apiKey);
        JsonPlaceHolderAPI jsonPlaceHolderAPI = RetrofitInstance.callNewsAPI().create(JsonPlaceHolderAPI.class);

        Call<NewsModel> call = jsonPlaceHolderAPI.getNews(parameters);
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                try {
                    if (response.isSuccessful()) {
                        newsLD.postValue(response.body());
                    }
                    isFinished.postValue(true);
                } catch (Exception ex) {
                    newsLD.postValue(null);
                    isFinished.postValue(false);
                    Log.d("ON_RESPONSE", ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                newsLD.postValue(null);
                Log.d("ON_RESPONSE", t.getMessage());
                isFinished.postValue(true);
            }
        });
    }

    public LiveData<NewsModel> getNewsObserver() {
        return newsLD;
    }

    public LiveData<Boolean> getIsFinishedObserver() {
        return isFinished;
    }
}
