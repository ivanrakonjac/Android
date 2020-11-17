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

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;

    private CaloriesFragment caloriesFragment;

    private static final String CALORIES_TAG = "fragment-calories-tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();

        if(fragmentManager.getFragments().size()==0){
            caloriesFragment = new CaloriesFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout, caloriesFragment, CALORIES_TAG)
                    .commit();
        }else{
            caloriesFragment =(CaloriesFragment) fragmentManager.findFragmentByTag(CALORIES_TAG);
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_item_calories:
                        return true;
                    case  R.id.menu_item_routes:
                        return true;
                    case R.id.menu_item_settings:
                        return true;
                }
                return false;
            }
        });

    }
}