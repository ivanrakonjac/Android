package ika.test.runningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ika.test.runningapp.calories.CaloriesFragment;
import ika.test.runningapp.databinding.ActivityMainBinding;
import ika.test.runningapp.routes.RouteBrowseFragment;
import ika.test.runningapp.routes.RouteFragment;
import ika.test.runningapp.routes.RouteViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;

    private CaloriesFragment caloriesFragment;
    private RouteFragment routeFragment;

    private static final String CALORIES_TAG = "fragment-calories-tag";
    private static final String ROUTE_TAG = "route-tag";

    private RouteViewModel routeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        routeViewModel = new ViewModelProvider(this).get(RouteViewModel.class);

        fragmentManager = getSupportFragmentManager();

        if(fragmentManager.getFragments().size()==0){
            caloriesFragment = new CaloriesFragment();
            routeFragment = new RouteFragment();

            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout, routeFragment, ROUTE_TAG)
                    //.add(R.id.frame_layout, caloriesFragment, CALORIES_TAG)
                    //.hide(caloriesFragment)
                    //.show(routeFragment)
                    .commit();
        }else{
            caloriesFragment =(CaloriesFragment) fragmentManager.findFragmentByTag(CALORIES_TAG);
            routeFragment = (RouteFragment) fragmentManager.findFragmentByTag(ROUTE_TAG);
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.menu_item_routes:
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.frame_layout, routeFragment, ROUTE_TAG)
                                //.addToBackStack(null)
                                //.show(routeFragment)
                                //.hide(caloriesFragment)
                                .commit();
                        return true;
                    case R.id.menu_item_calories:
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.frame_layout, caloriesFragment, CALORIES_TAG)
                                //.addToBackStack(null)
                                //.hide(routeFragment)
                                //.show(caloriesFragment)
                                .commit();
                        return true;
                    case R.id.menu_item_settings:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(binding.bottomNavigationView.getSelectedItemId() == R.id.menu_item_routes){
            if(routeFragment.getChildFragmentManager().getBackStackEntryCount() > 0){
                routeViewModel.setSelectedRoute(null);
                routeFragment.getChildFragmentManager().popBackStack();
                return;
            }
        }
        super.onBackPressed();
    }
}