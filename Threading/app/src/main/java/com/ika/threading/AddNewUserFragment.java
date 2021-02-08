package com.ika.threading;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.threading.databinding.FragmentAddNewUserBinding;
import com.ika.threading.databinding.FragmentSearchBinding;
import com.ika.threading.db.UserDatabase;
import com.ika.threading.entities.User;

import java.io.Console;
import java.util.Date;

public class AddNewUserFragment extends Fragment {

    MainActivity mainActivity;
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
                /*User user = new User(0, "Stanko123", "test", "Stanko", "Stankovic", new Date());
                userDatabase.userDao().insert(user);*/

                new Thread( () -> {
                    //posao koji treba da se odradi

                    mainActivity = (MainActivity) getActivity();

                    SystemClock.sleep(1000);
                    mainActivity.runOnUiThread( () -> binding.addNewUser.setBackgroundColor(Color.RED) );

                    SystemClock.sleep(1000);
                    mainActivity.runOnUiThread( () -> binding.addNewUser.setBackgroundColor(Color.YELLOW) );

                    SystemClock.sleep(1000);
                    mainActivity.runOnUiThread( () -> binding.addNewUser.setBackgroundColor(Color.BLACK) );

                    mainActivity.runOnUiThread( () -> binding.addNewUser.setText("THE PROPER WAY") );

                    binding.addNewUser.post( () -> binding.addNewUser.setText("THE PROPER WAY 2") );

                    Log.v ("THREADING", "HEEJ");

                }).start();

            }
        });

        return binding.getRoot();
    }
}