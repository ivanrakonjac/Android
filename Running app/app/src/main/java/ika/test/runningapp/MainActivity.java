package ika.test.runningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

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

    private RouteViewModel routeViewModel;

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        routeViewModel = new ViewModelProvider(this).get(RouteViewModel.class);

        fragmentManager = getSupportFragmentManager();

        //Dohvatamo NavHostFragment pomocu fragmentManagera, da bi dohvatili NavController
        NavHostFragment navHostFragment = (NavHostFragment)fragmentManager.findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();


        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.menu_item_routes:
                        navController.navigate(R.id.routeBrowseFragment);
                        return true;
                    case R.id.menu_item_calories:
                        navController.navigate(R.id.caloriesFragment);
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
        if(bottomNavigationView.getSelectedItemId() == R.id.menu_item_routes){
            routeViewModel.setSelectedRoute(null);
            //return;
        }
        super.onBackPressed();
    }
}