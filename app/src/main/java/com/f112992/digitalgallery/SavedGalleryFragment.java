package com.f112992.digitalgallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.f112992.digitalgallery.database.ArtPieceDBModel;
import com.f112992.digitalgallery.databinding.FragmentSavedGalleryBinding;

import java.util.List;

public class SavedGalleryFragment extends Fragment {

    private FragmentSavedGalleryBinding binding;
    private SavedGalleryAdapter adapter;
    private DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSavedGalleryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        dbHelper = new DBHelper(requireContext());
        dbHelper.config();
        
        adapter = new SavedGalleryAdapter();
        binding.recyclerView.setAdapter(adapter);
        
        loadData();
    }

    private void loadData() {
        List<ArtPieceDBModel> savedArtInfo = dbHelper.getAllArtPieces(false);
        adapter.setItems(savedArtInfo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
