package com.f112992.digitalgallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    ArtData loadedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView imageView = findViewById(R.id.imageView);
        TextView titleView = findViewById(R.id.title);
        TextView descView = findViewById(R.id.description);
        TextView creditsView = findViewById(R.id.credits);
        Button readMoreBtn = findViewById(R.id.read_more_btn);

        executor.execute(() -> {
            HarvardArtRouter.config();
            ArtData data = ArtService.getHarvardArtObjectData();
            loadedData = data;
            Bitmap bmp = null;
            if (!data.imageURL.isEmpty()) {
                try {
                    URL url = new URL(data.imageURL);
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
        goToUrl(loadedData.externalLink);
    }
    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}