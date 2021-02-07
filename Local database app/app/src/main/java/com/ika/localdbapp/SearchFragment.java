package com.ika.localdbapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.localdbapp.databinding.FragmentFavoritesBinding;
import com.ika.localdbapp.databinding.FragmentSearchBinding;
import com.ika.localdbapp.db.UserDatabase;
import com.ika.localdbapp.entities.User;

import java.util.Date;


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

        UserDatabase userDatabase = UserDatabase.getInstance((MainActivity) getActivity());

        User user = new User(0, "Marko123", "test", "Marko", "Markovic", new Date());
        userDatabase.userDao().insert(user);

        return binding.getRoot();
    }
}