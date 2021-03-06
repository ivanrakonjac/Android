package com.ika.localdbapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.localdbapp.databinding.FragmentAddNewUserBinding;
import com.ika.localdbapp.databinding.FragmentSearchBinding;
import com.ika.localdbapp.db.UserDatabase;
import com.ika.localdbapp.entities.User;

import java.util.Date;

public class AddNewUserFragment extends Fragment {

    FragmentAddNewUserBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddNewUserBinding.inflate(inflater, container, false);

        UserDatabase userDatabase = UserDatabase.getInstance((MainActivity) getActivity());

        binding.addNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(0, "Stanko123", "test", "Stanko", "Stankovic", new Date());
                userDatabase.userDao().insert(user);
            }
        });

        return binding.getRoot();
    }
}