package com.ika.servicesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import android.os.Bundle;

import com.ika.servicesapp.databinding.ActivityMainBinding;
import com.ika.servicesapp.utils.BottomNavigationUtil;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public static final String INTENT_ACTION_NOTIFICATION = "com.ika.servicesapp.NOTIFICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (savedInstanceState == null) {
            setupBottomNavigation();
        }

        if(getIntent().getAction().equals(INTENT_ACTION_NOTIFICATION)){
            NavController navController = BottomNavigationUtil.changeNavHostFragment(R.id.bottom_navigation_calories);
            /*
            Ako hocu sa caloriesa na neki drugi fragment
            if(navController != null) {
                navController.navigate();
            }
            */
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        int[] navResourceIds = new int[]{
                R.navigation.nav_graph_routes,
                R.navigation.nav_graph_workouts,
                R.navigation.nav_graph_calories
        };
        BottomNavigationUtil.setup(
                binding.bottomNavigation,
                getSupportFragmentManager(),
                navResourceIds,
                R.id.nav_host_container
        );
    }
}