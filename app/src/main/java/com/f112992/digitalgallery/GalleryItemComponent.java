package com.f112992.digitalgallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.f112992.digitalgallery.databinding.GalleryItemComponentBinding;

public class GalleryItemComponent extends FrameLayout {

    private ArtData data;
    private GalleryItemComponentBinding binding;

    public GalleryItemComponent(@NonNull Context context) {
        super(context);
        init(null);
    }

    public GalleryItemComponent(ArtData data, @NonNull Context context) {
        super(context);
        this.data = data;
        init(data);
    }

    private void init(ArtData data) {
        binding = GalleryItemComponentBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (data != null) {
            binding.title.setText(data.title);
            if (data.dateAdded != null) {
                binding.date.setText(data.dateAdded.toString());
            }
        }
    }

    public void setData(ArtData data) {
        this.data = data;
        if (data != null && binding != null) {
            binding.title.setText(data.title);
            if (data.dateAdded != null) {
                binding.date.setText(data.dateAdded.toString());
            }
        }
    }
}
