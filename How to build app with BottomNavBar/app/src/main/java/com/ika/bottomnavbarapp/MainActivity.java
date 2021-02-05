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

    //private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;
    private NavController navigationCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();

        // Dohvatamo NavHostFragment pomocu fragmentManagera, da bi dohvatili NavController (a on kontrolise navigaciju)
        NavHostFragment navHostFragment = (NavHostFragment)fragmentManager.findFragmentById(R.id.nav_host_fragment);
        navigationCtrl = navHostFragment.getNavController();

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        switch (navigationCtrl.getCurrentDestination().getId()){
                            case R.id.favoritesFragment:
                                navigationCtrl.navigate(R.id.action_favoritesFragment_pop); // Dodaje se novi fragment (R.id.homeFragment) na back stack
                                break;
                            case R.id.searchFragment:
                                navigationCtrl.navigate(R.id.action_searchFragment_pop);
                            default:
                                // Empty
                                break;
                        }
                        return true;
                    case R.id.nav_favorites:
                        switch (navigationCtrl.getCurrentDestination().getId()){
                            case R.id.homeFragment:
                            case R.id.searchFragment:
                                navigationCtrl.navigate(R.id.action_global_favoritesFragment); // Skidaj sve fragmente sa back stacka dok ne skines favoritesFragment
                                break;
                            default:
                                // Empty
                                break;
                        }
                        return true;
                    case R.id.nav_search:
                        switch (navigationCtrl.getCurrentDestination().getId()){
                            case R.id.homeFragment:
                            case R.id.favoritesFragment:
                                navigationCtrl.navigate(R.id.action_global_searchFragment); // Skidaj sve fragmente sa back stacka dok ne skines favoritesFragment
                                break;
                            default:
                                // Empty
                                break;
                        }
                        return true;
                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        switch (navigationCtrl.getCurrentDestination().getId()){
            case R.id.favoritesFragment:
                navigationCtrl.navigate(R.id.action_global_homeFragment); // Skidaj sve fragmente sa back stacka dok ne skines favoritesFragment
                break;
            case R.id.searchFragment:
                navigationCtrl.navigate(R.id.action_global_homeFragment); // Skidaj sve fragmente sa back stacka dok ne skines favoritesFragment
                break;
            default:
                super.onBackPressed();
                break;
        }

    }
}