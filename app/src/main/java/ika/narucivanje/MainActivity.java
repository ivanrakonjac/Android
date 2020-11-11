package ika.narucivanje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ika.narucivanje.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "activity-lifecycle";

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.button.setOnClickListener(view -> {
            activityMainBinding.textView.setText("Promenjeno nakon klika iz ClickListenera");
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState() called");
    }


    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "OnStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "OnResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "OnPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "OnStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "OnDestroy() called");
    }
}