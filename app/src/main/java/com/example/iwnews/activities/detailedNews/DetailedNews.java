package com.example.iwnews.activities.detailedNews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iwnews.R;
import com.example.iwnews.viewModel.NewsViewModel;

public class DetailedNews extends AppCompatActivity {
    private WebView webView;
    private ImageView backBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        uiInit();
        onBackClick();
        webViewSetup();
    }

    private void onBackClick() {
        backBtn.setOnClickListener(v -> onBackPressed());
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void webViewSetup() {
        try {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    progressBar.setProgress(progress);
                    if (progress == 100) {
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return false;
                }
            });
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.loadUrl(NewsViewModel.newsLD.getValue().getArticles().get(getSelectedNews()).getUrl());
        } catch (Exception ex) {
            Toast.makeText(this, "Exception Handled: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void uiInit() {
        webView = findViewById(R.id.webView);
        backBtn = findViewById(R.id.back);
        progressBar = findViewById(R.id.progress);
    }

    private Integer getSelectedNews() {
        Integer position = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("selectedNews", 0);
        }
        return position;
    }
}
