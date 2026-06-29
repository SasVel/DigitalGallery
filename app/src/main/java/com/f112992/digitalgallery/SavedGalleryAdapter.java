package com.f112992.digitalgallery;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.f112992.digitalgallery.database.ArtPieceDBModel;

import java.util.ArrayList;
import java.util.List;

public class SavedGalleryAdapter extends RecyclerView.Adapter<SavedGalleryAdapter.ViewHolder> {

    private final List<ArtData> items = new ArrayList<>();

    public void setItems(List<ArtPieceDBModel> dbModels) {
        items.clear();
        for (ArtPieceDBModel model : dbModels) {
            items.add(new ArtData(model));
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GalleryItemComponent component = new GalleryItemComponent(parent.getContext());
        component.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new ViewHolder(component);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.component.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        GalleryItemComponent component;

        ViewHolder(GalleryItemComponent component) {
            super(component);
            this.component = component;
        }
    }
}
