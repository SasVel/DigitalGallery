package com.f112992.digitalgallery;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SavedGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_gallery);
    }

    public void onBackPressed(View view) {
        getOnBackPressedDispatcher().onBackPressed();
    }
}
