package ika.test.runningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ika.test.runningapp.calories.CaloriesActivity;
import ika.test.runningapp.databinding.ActivityMainBinding;

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
                //intent.setComponent(new ComponentName("ika.test.runningapp","ika.test.runningapp.calories.CaloriesActivity"));
                explicitIntent.setClass(v.getContext(), CaloriesActivity.class);
                startActivity(explicitIntent);
            }
        });
    }
}