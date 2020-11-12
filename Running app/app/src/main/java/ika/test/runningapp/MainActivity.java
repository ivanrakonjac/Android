package ika.test.runningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

        myViewModel.getCaloriesBurned().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer caloriesBurned) {
                if(caloriesBurned != -1){
                    activityMainBinding.rezultat.setText("Sagoreno je: " + caloriesBurned + " kcal");
                }
            }
        });

        myViewModel.getCaloriesNeeded().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer caloriesNeeded) {
                if(caloriesNeeded != -1){
                    activityMainBinding.rezultatPotrebnoKalorija.setText("Potrebno je: " + caloriesNeeded + " kcal");
                }
            }
        });


        //isto kao gore sampo lambda izraz
        activityMainBinding.izracunaj.setOnClickListener( view -> {

            TypedArray metValues = getResources().obtainTypedArray(R.array.met_values);

            double weight = Double.parseDouble(activityMainBinding.weightEditText.getText().toString());
            double height = Double.parseDouble(activityMainBinding.heightEditText.getText().toString());
            int age = Integer.parseInt(activityMainBinding.ageEditText.getText().toString());
            boolean isMale = activityMainBinding.male.isChecked();
            double duration = Double.parseDouble(activityMainBinding.durationEditText.getText().toString());
            double met = metValues.getFloat(activityMainBinding.spinner.getSelectedItemPosition(), 0);
            int caloriesBurned = (int) (duration * met * 3.5 * weight / 200);
            myViewModel.updateValues(weight, height, age, isMale, duration, met);
        });

        String[] metStrings = getResources().getStringArray(R.array.met_strings);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, metStrings);
        activityMainBinding.spinner.setAdapter(arrayAdapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if(myViewModel.getCaloriesBurned().getValue() != null){
            outState.putInt(myViewModel.CALORIES_BURNED_KEY, myViewModel.getCaloriesBurned().getValue());
        }
    }
}