package com.example.iwnews.activities.aboutApp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iwnews.R;
import com.example.iwnews.services.BulletListUtil;

public class AboutSection extends AppCompatActivity {
    private ImageView back;
    private TextView aboutApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_section);
        uiInIt();
        onBackClick();
        fillAboutApp();


    }

    @SuppressLint("SetTextI18n")
    private void fillAboutApp() {
        CharSequence bulletedList = BulletListUtil.makeBulletList(40,
                "The application is developed using JAVA as programming language.",
                "API integration in application id done using Retrofit library.",
                "JSON data is parsed using GSON Library.",
                "MVVM architecture is used for the development of application.",
                "The list of news is shown on Recycler view with the custom design.",
                "Date is formatted into the user understandable form.",
                "GLIDE is used for loading image from the respective url.",
                "Image animation is used to show the loading to image from the url.",
                "To show the detailed news, webview is used to load webpage from the given link into the app itself.",
                "A progress bar is used to show the loading progress of webpage into the application."
        );
        aboutApp.setText(bulletedList);
    }

    private void onBackClick() {
        back.setOnClickListener(v -> onBackPressed());
    }

    private void uiInIt() {
        back = findViewById(R.id.back);
        aboutApp = findViewById(R.id.aboutApplication);
    }
}
