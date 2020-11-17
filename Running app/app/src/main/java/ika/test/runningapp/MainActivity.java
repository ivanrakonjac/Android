package ika.test.runningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ika.test.runningapp.calories.CaloriesActivity;
import ika.test.runningapp.databinding.ActivityMainBinding;
import ika.test.runningapp.routes.RouteBrowseActivity;
import ika.test.runningapp.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(v.getContext(), CaloriesActivity.class);
                startActivity(explicitIntent);
            }
        });

        binding.buttonRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(v.getContext(), RouteBrowseActivity.class);
                startActivity(explicitIntent);
            }
        });

        binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}