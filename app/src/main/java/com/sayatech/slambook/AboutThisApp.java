package com.sayatech.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class AboutThisApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_this_app);


    MaterialToolbar toolbar = findViewById(R.id.topAppBar2);
    toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
