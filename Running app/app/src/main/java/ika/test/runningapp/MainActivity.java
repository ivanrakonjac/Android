package ika.test.runningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ika.test.runningapp.calories.CaloriesFragment;
import ika.test.runningapp.databinding.ActivityMainBinding;
import ika.test.runningapp.routes.RouteBrowseFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;

    private CaloriesFragment caloriesFragment;
    private RouteBrowseFragment routeBrowseFragment;

    private static final String CALORIES_TAG = "fragment-calories-tag";
    private static final String ROUTE_BROWSE_TAG = "route-browse-tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();

        if(fragmentManager.getFragments().size()==0){
            caloriesFragment = new CaloriesFragment();
            routeBrowseFragment = new RouteBrowseFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout, routeBrowseFragment, ROUTE_BROWSE_TAG)
                    .add(R.id.frame_layout, caloriesFragment, CALORIES_TAG)
                    .hide(caloriesFragment)
                    .show(routeBrowseFragment)
                    .commit();
        }else{
            caloriesFragment =(CaloriesFragment) fragmentManager.findFragmentByTag(CALORIES_TAG);
            routeBrowseFragment = (RouteBrowseFragment) fragmentManager.findFragmentByTag(ROUTE_BROWSE_TAG);
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.menu_item_routes:
                        fragmentManager
                                .beginTransaction()
                                //.replace(R.id.frame_layout, routeBrowseFragment, ROUTE_BROWSE_TAG)
                                .show(routeBrowseFragment)
                                .hide(caloriesFragment)
                                .commit();
                        return true;
                    case R.id.menu_item_calories:
                        fragmentManager
                                .beginTransaction()
                                //.replace(R.id.frame_layout, caloriesFragment, CALORIES_TAG)
                                .hide(routeBrowseFragment)
                                .show(caloriesFragment)
                                .commit();
                        return true;
                    case R.id.menu_item_settings:
                        return true;
                }
                return false;
            }
        });

    }
}