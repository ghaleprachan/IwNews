package com.example.iwnews.activities.newsDashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwnews.R;
import com.example.iwnews.activities.aboutApp.AboutSection;
import com.example.iwnews.adapters.NewsAdapter;
import com.example.iwnews.models.NewsModel;
import com.example.iwnews.viewModel.NewsViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

public class NewsDashboard extends AppCompatActivity {
    private ShimmerFrameLayout shimmerEffect;
    private RecyclerView newsRecyclerView;
    private Toolbar toolbar;
    private NewsViewModel newsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_dashboard);
        UiInit();
        toolbarSetup();
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.makeApiCall();

        isLoadingObserver();
        getNewsObserver();
    }

    private void toolbarSetup() {
        setSupportActionBar(toolbar);
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
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.more) {
            startActivity(new Intent(this, AboutSection.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
