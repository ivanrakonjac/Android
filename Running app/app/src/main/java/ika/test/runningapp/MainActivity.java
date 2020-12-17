package ika.test.runningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import ika.test.runningapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_TAG = "running-app-eample";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState == null){
            setupBottomNavigation();
        }

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setupBottomNavigation();
    }

    private void setupBottomNavigation(){
        int[] navResourceIds = new int[]{
                R.navigation.nav_graph_routes,
                R.navigation.nav_graph_workouts,
                R.navigation.nav_graph_calories
        };

        BottomNavigationUtil.setup(
                binding.bottomNavigationView,
                getSupportFragmentManager(),
                navResourceIds,
                R.id.nav_host_container
        );
    }
}