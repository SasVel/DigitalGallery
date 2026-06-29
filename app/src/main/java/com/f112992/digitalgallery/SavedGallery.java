package com.f112992.digitalgallery;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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