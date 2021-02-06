package com.ika.bottomnavbarapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ika.bottomnavbarapp.databinding.FragmentFavoritesBinding;


public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);


        int index = FavoritesFragmentArgs.fromBundle(requireArguments()).getIndex();

        binding.favoritesTextView.setText("Favorites fragment " + index);


        return binding.getRoot();
    }
}