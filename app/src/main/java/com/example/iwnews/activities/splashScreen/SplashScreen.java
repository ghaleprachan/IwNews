package com.example.iwnews.activities.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iwnews.activities.newsDashboard.NewsDashboard;
import com.example.iwnews.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        finishSplashScreen();
    }

    private void finishSplashScreen() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreen.this, NewsDashboard.class));
            finish();
        }, 2000);
    }
}