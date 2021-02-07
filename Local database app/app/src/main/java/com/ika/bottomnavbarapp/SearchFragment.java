package com.ika.bottomnavbarapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.bottomnavbarapp.databinding.FragmentFavoritesBinding;
import com.ika.bottomnavbarapp.databinding.FragmentSearchBinding;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false);


        //int searchIndex = SearchFragmentArgs.fromBundle(requireArguments()).getSearchIndex();

        //binding.searchTextView.setText("Search fragment " + searchIndex);


        return binding.getRoot();
    }
}