package ika.test.runningapp.calories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import ika.test.runningapp.MyLifeCycleAwareComponent;
import ika.test.runningapp.R;
import ika.test.runningapp.databinding.ActivityCaloriesBinding;

public class CaloriesActivity extends AppCompatActivity {

    private ActivityCaloriesBinding activityCaloriesBinding;

    private CaloriesViewModel caloriesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        getLifecycle().addObserver(new MyLifeCycleAwareComponent());

        activityCaloriesBinding = ActivityCaloriesBinding.inflate(getLayoutInflater());
        setContentView(activityCaloriesBinding.getRoot());

        caloriesViewModel = new ViewModelProvider(this).get(CaloriesViewModel.class);

        caloriesViewModel.initByBundle(savedInstanceState);

        caloriesViewModel.getCaloriesBurned().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer caloriesBurned) {
                if(caloriesBurned != -1){
                    activityCaloriesBinding.rezultat.setText("Sagoreno je: " + caloriesBurned + " kcal");
                }
            }
        });

        caloriesViewModel.getCaloriesNeeded().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer caloriesNeeded) {
                if(caloriesNeeded != -1){
                    activityCaloriesBinding.rezultatPotrebnoKalorija.setText("Potrebno je: " + caloriesNeeded + " kcal");
                }
            }
        });


        //isto kao gore sampo lambda izraz
        activityCaloriesBinding.izracunaj.setOnClickListener( view -> {

            TypedArray metValues = getResources().obtainTypedArray(R.array.met_values);

            double weight = Double.parseDouble(activityCaloriesBinding.weightEditText.getText().toString());
            double height = Double.parseDouble(activityCaloriesBinding.heightEditText.getText().toString());
            int age = Integer.parseInt(activityCaloriesBinding.ageEditText.getText().toString());
            boolean isMale = activityCaloriesBinding.male.isChecked();
            double duration = Double.parseDouble(activityCaloriesBinding.durationEditText.getText().toString());
            double met = metValues.getFloat(activityCaloriesBinding.spinner.getSelectedItemPosition(), 0);
            int caloriesBurned = (int) (duration * met * 3.5 * weight / 200);
            caloriesViewModel.updateValues(weight, height, age, isMale, duration, met);
        });

        String[] metStrings = getResources().getStringArray(R.array.met_strings);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, metStrings);
        activityCaloriesBinding.spinner.setAdapter(arrayAdapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if(caloriesViewModel.getCaloriesBurned().getValue() != null){
            outState.putInt(caloriesViewModel.CALORIES_BURNED_KEY, caloriesViewModel.getCaloriesBurned().getValue());
        }
    }
}