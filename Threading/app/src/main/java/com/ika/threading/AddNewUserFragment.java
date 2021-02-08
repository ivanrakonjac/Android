package com.ika.threading;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.threading.databinding.FragmentAddNewUserBinding;
import com.ika.threading.databinding.FragmentSearchBinding;
import com.ika.threading.db.UserDatabase;
import com.ika.threading.entities.User;
import com.ika.threading.threads.CustomDequeThread;
import com.ika.threading.threads.CustomLooperThread;

import java.io.Console;
import java.util.Date;

public class AddNewUserFragment extends Fragment {

    MainActivity mainActivity;
    FragmentAddNewUserBinding binding;

    private HandlerThread handlerThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handlerThread = new HandlerThread("ikas-handler-thread");
        handlerThread.start();
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

                Handler newThreadHandler = new Handler(handlerThread.getLooper());
                Handler uiThreadHandler = new Handler(Looper.getMainLooper());

                newThreadHandler.post( () -> {
                    //posao koji treba da se odradi

                    mainActivity = (MainActivity) getActivity();

                    SystemClock.sleep(1000);
                    uiThreadHandler.post( () -> binding.addNewUser.setBackgroundColor(Color.RED) );

                    SystemClock.sleep(1000);
                    uiThreadHandler.post( () -> binding.addNewUser.setBackgroundColor(Color.YELLOW) );

                    SystemClock.sleep(1000);
                    uiThreadHandler.post( () -> binding.addNewUser.setBackgroundColor(Color.BLACK) );

                    SystemClock.sleep(1000);
                    uiThreadHandler.post( () -> binding.addNewUser.setText("THE PROPER WAY") );

                    SystemClock.sleep(1000);
                    uiThreadHandler.post( () -> binding.addNewUser.setText("THE PROPER WAY 2") );

                    Log.v ("THREADING", "HEEJ");

                });

            }
        });

        return binding.getRoot();
    }
}