package com.ika.bottomnavbarapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.bottomnavbarapp.databinding.FragmentFavoritesBinding;
import com.ika.bottomnavbarapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);


       // String homeString = HomeFragmentArgs.fromBundle(requireArguments()).getIndexHome();

        //binding.homeFragmentTextView.setText("Home fragment " + homeString);


        return binding.getRoot();
    }
}