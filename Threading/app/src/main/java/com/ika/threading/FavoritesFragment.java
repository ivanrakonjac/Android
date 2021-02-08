package com.ika.threading;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.threading.db.UserDatabase;
import com.ika.threading.entities.User;

import java.util.Date;


public class FavoritesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        UserDatabase userDatabase = UserDatabase.getInstance((MainActivity) getActivity());

        User user = new User(0, "Marko123", "test", "Marko", "Markovic", new Date());
        userDatabase.userDao().insert(user);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }
}