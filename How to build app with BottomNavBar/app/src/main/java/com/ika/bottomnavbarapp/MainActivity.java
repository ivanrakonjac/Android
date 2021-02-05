package com.ika.bottomnavbarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ika.bottomnavbarapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Toast.makeText(getBaseContext(), "HOME", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_favorites:
                        Toast.makeText(getBaseContext(), "FAV", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_search:
                        Toast.makeText(getBaseContext(), "SEARCH", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });
    }
}