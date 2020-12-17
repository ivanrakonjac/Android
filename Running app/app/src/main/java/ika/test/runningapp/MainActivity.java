package ika.test.runningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ika.test.runningapp.calories.CaloriesFragmentDirections;
import ika.test.runningapp.databinding.ActivityMainBinding;
import ika.test.runningapp.routes.RouteViewModel;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_TAG = "fragmentExample";

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;

    private RouteViewModel routeViewModel;

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        switch (navController.getCurrentDestination().getId()){
                            case R.id.caloriesFragment:
                                NavDirections action = CaloriesFragmentDirections.actionCaloriesFragmentPop();
                                navController.navigate(action);
                                break;
                            default:
                                //Do nothing
                                break;
                        }
                        return true;
                    case R.id.menu_item_calories:
                        switch (navController.getCurrentDestination().getId()){
                            case R.id.routeBrowseFragment:
                            case R.id.routeDetailsFragment:
                                NavDirections action = NavGraphDirections.actionGlobalCaloriesFragment();
                                navController.navigate(action);
                                break;
                            default:
                                //Do nothing
                                break;
                        }
                        return true;
                    case R.id.menu_item_workouts:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {

        switch (navController.getCurrentDestination().getId()){
            case R.id.routeDetailsFragment:
                routeViewModel.setSelectedRoute(null);
                break;
            case R.id.caloriesFragment:
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                break;
        }
        super.onBackPressed();
    }
}