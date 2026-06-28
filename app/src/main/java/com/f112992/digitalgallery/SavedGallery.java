package com.f112992.digitalgallery;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.f112992.digitalgallery.databinding.ActivitySavedGalleryBinding;

public class SavedGallery extends AppCompatActivity {

    private ActivitySavedGalleryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySavedGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}