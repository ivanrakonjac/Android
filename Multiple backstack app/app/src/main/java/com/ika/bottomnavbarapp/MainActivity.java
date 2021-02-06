package com.ika.bottomnavbarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

                NavGraphDirections.ActionGlobalHomeFragment actionHomeWithString = HomeFragmentDirections.actionGlobalHomeFragment();
                actionHomeWithString.setIndexHome("PROBA SLANJA");

                NavGraphDirections.ActionGlobalFavoritesFragment actionFavoritesWithIndex = FavoritesFragmentDirections.actionGlobalFavoritesFragment();
                actionFavoritesWithIndex.setIndex(5);

                NavGraphDirections.ActionGlobalSearchFragment actionSearchIndex = SearchFragmentDirections.actionGlobalSearchFragment();
                actionSearchIndex.setSearchIndex(15);

                switch (item.getItemId()){
                    case R.id.nav_home:
                        switch (navigationCtrl.getCurrentDestination().getId()){
                            case R.id.favoritesFragment:
                            case R.id.searchFragment:
                                navigationCtrl.navigate(actionHomeWithString);
                                break;
                            default:
                                // Empty
                                break;
                        }
                        return true;
                    case R.id.nav_favorites:
                        switch (navigationCtrl.getCurrentDestination().getId()){
                            case R.id.homeFragment:
                            case R.id.searchFragment:
                                navigationCtrl.navigate(actionFavoritesWithIndex); // Skidaj sve fragmente sa back stacka dok ne skines favoritesFragment
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
                                navigationCtrl.navigate(actionSearchIndex); // Skidaj sve fragmente sa back stacka dok ne skines favoritesFragment
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
                NavDirections actionPopFav = FavoritesFragmentDirections.actionFavoritesFragmentPop(); // Skidaj sve fragmente sa back stacka dok ne skines favoritesFragment
                navigationCtrl.navigate(actionPopFav);
                break;
            case R.id.searchFragment:
                NavDirections actionPopSearch = SearchFragmentDirections.actionSearchFragmentPop();
                navigationCtrl.navigate(actionPopSearch); // Skidaj sve fragmente sa back stacka dok ne skines favoritesFragment
                break;
            default:
                super.onBackPressed();
                break;
        }


        switch (navigationCtrl.getCurrentDestination().getId()){
            case R.id.favoritesFragment:
                bottomNavigationView.setSelectedItemId(R.id.nav_favorites);
                break;
            case R.id.searchFragment:
                bottomNavigationView.setSelectedItemId(R.id.nav_search);
                break;
            case R.id.homeFragment:
                bottomNavigationView.setSelectedItemId(R.id.nav_home);
                break;
        }

    }
}