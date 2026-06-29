package com.f112992.digitalgallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.f112992.digitalgallery.database.ArtPieceDBModel;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArtOfTheDayActivity extends AppCompatActivity {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    DBHelper dbHelper = new DBHelper(ArtOfTheDayActivity.this);
    ArtData data;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.art_display_activity);
        loadArtData();
    }

    private void loadArtData() {
        ImageView imageView = findViewById(R.id.imageView);
        TextView titleView = findViewById(R.id.title);
        TextView descView = findViewById(R.id.description);
        TextView creditsView = findViewById(R.id.credits);

        executor.execute(() -> {
            dbHelper.config();
            HarvardArtRouter.config();
            var test = dbHelper.getAllArtPieces();
            ArtPieceDBModel dbModel = dbHelper.getDailyArtPiece();
            if (dbModel == null) {
                data = ArtService.getHarvardArtRandObjectData();
                dbHelper.insertArtPiece(new ArtPieceDBModel(data));
            }
            else {
                data = ArtService.getHarvardArtObjectData(dbModel.externalID);
            }

            Bitmap bmp = null;
            if (!data.imageURL.equals("null") && !data.imageURL.isEmpty()) {
                try {
                    URL url = new URL(data.imageURL);
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (IOException e) {
                    System.out.println("Failed to retrieve image.");
                }
            }

            Bitmap finalBmp = bmp;
            handler.post(() -> {
                if (!data.imageURL.isEmpty() && finalBmp != null) {
                    imageView.setImageBitmap(finalBmp);
                }
                titleView.setText(data.title);
                descView.setText(data.description);
                creditsView.setText(data.source);
            });
        });
    }

    public void onReadMoreBtnPress(View view) {
        goToUrl(data.externalLink);
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
