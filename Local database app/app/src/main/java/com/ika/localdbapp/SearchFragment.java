package com.ika.localdbapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.localdbapp.databinding.FragmentFavoritesBinding;
import com.ika.localdbapp.databinding.FragmentSearchBinding;
import com.ika.localdbapp.db.UserDatabase;
import com.ika.localdbapp.entities.User;
import com.ika.localdbapp.recyclerview.UserAdapter;

import java.util.Date;
import java.util.List;


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

        List<User> userList = userDatabase.userDao().getAll();


        UserAdapter userAdapter = new UserAdapter();
        userAdapter.setUserList(userList);

        binding.recyclerView.setAdapter(userAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));




        return binding.getRoot();
    }
}