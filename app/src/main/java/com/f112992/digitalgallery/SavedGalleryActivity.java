package com.f112992.digitalgallery;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.f112992.digitalgallery.database.ArtPieceDBModel;

import java.util.List;

public class SavedGalleryActivity extends AppCompatActivity {
    DBHelper dbHelper = new DBHelper(SavedGalleryActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_gallery);
        loadArtComponents();
    }

    private void loadArtComponents() {
        ViewGroup main = this.findViewById(R.id.main);
        List<ArtPieceDBModel> savedArtInfo = dbHelper.getAllArtPieces(false);
        for (ArtPieceDBModel model: savedArtInfo) {
            ArtData data = new ArtData(model);
            GalleryItemComponent component = new GalleryItemComponent(data, this);
            main.addView(component);
        }
    }
}