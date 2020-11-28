package com.example.iwnews.activities.newsDashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwnews.R;
import com.example.iwnews.adapters.NewsAdapter;
import com.example.iwnews.models.NewsModel;
import com.example.iwnews.viewModel.NewsViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

public class NewsDashboard extends AppCompatActivity {
    private ShimmerFrameLayout shimmerEffect;
    private RecyclerView newsRecyclerView;

    private NewsViewModel newsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_dashboard);
        UiInit();

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.makeApiCall();

        isLoadingObserver();
        getNewsObserver();
    }

    private void getNewsObserver() {
        newsViewModel.getNewsObserver().observe(this, newsModel -> {
            if (newsModel != null) {
                inItRecyclerView(newsModel);
            }
        });
    }

    private void isLoadingObserver() {
        newsViewModel.getIsFinishedObserver().observe(this, aBoolean -> {
            if (aBoolean) {
                shimmerEffect.setVisibility(View.GONE);
                shimmerEffect.startShimmer();
                newsRecyclerView.setVisibility(View.VISIBLE);
            } else {
                shimmerEffect.setVisibility(View.VISIBLE);
                shimmerEffect.stopShimmer();
                newsRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    private void inItRecyclerView(NewsModel newsModel) {
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewsAdapter mNewsAdapter = new NewsAdapter(this, newsModel);
        newsRecyclerView.setAdapter(mNewsAdapter);
    }

    private void UiInit() {
        shimmerEffect = findViewById(R.id.loadingShimmer);
        newsRecyclerView = findViewById(R.id.newsListRecycler);
    }
}
