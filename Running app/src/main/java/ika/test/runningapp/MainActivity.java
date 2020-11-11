package ika.test.runningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import ika.test.runningapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLifecycle().addObserver(new MyLifeCycleAwareComponent());

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        myViewModel.initByBundle(savedInstanceState);

        myViewModel.getCaloriesBurned().observe(this,
                integer -> activityMainBinding.rezultat.setText("Sagoreno je: " + integer + " kcal"));


        //isto kao gore sampo lambda izraz
        activityMainBinding.izracunaj.setOnClickListener( view -> {
            double duration = Double.parseDouble(activityMainBinding.trajanje.getText().toString());
            double met = 9.8;
            double weight = 80;
            int caloriesBurned = (int) (duration * met * 3.5 * weight / 200);
            myViewModel.setCaloriesBurned(caloriesBurned);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if(myViewModel.getCaloriesBurned().getValue() != null){
            outState.putInt(myViewModel.CALORIES_BURNED_KEY, myViewModel.getCaloriesBurned().getValue());
        }
    }
}