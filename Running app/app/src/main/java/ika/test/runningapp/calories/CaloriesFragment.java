package ika.test.runningapp.calories;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import ika.test.runningapp.LifeCycleAwareLogger;
import ika.test.runningapp.MainActivity;
import ika.test.runningapp.R;
import ika.test.runningapp.databinding.FragmentCaloriesBinding;

public class CaloriesFragment extends Fragment {

    private FragmentCaloriesBinding binding;
    private CaloriesViewModel caloriesViewModel;

    public CaloriesFragment() {
        getLifecycle().addObserver(new LifeCycleAwareLogger("LIFE_CYCLE_TAG", "CaloriesFragment "));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCaloriesBinding.inflate(inflater, container, false);

        getLifecycle().addObserver(new LifeCycleAwareLogger("lifeCycleLogger", "MainActivity"));

        MainActivity parentActivity = (MainActivity) getActivity();

        caloriesViewModel = new ViewModelProvider(parentActivity).get(CaloriesViewModel.class);

        caloriesViewModel.initByBundle(savedInstanceState);

        caloriesViewModel.getCaloriesBurned().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer caloriesBurned) {
                if(caloriesBurned != -1){
                    binding.rezultat.setText("Sagoreno je: " + caloriesBurned + " kcal");
                }
            }
        });

        caloriesViewModel.getCaloriesNeeded().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer caloriesNeeded) {
                if(caloriesNeeded != -1){
                    binding.rezultatPotrebnoKalorija.setText("Potrebno je: " + caloriesNeeded + " kcal");
                }
            }
        });


        //isto kao gore sampo lambda izraz
        binding.izracunaj.setOnClickListener( view -> {

            TypedArray metValues = getResources().obtainTypedArray(R.array.met_values);

            double weight = Double.parseDouble(binding.weightEditText.getText().toString());
            double height = Double.parseDouble(binding.heightEditText.getText().toString());
            int age = Integer.parseInt(binding.ageEditText.getText().toString());
            boolean isMale = binding.male.isChecked();
            double duration = Double.parseDouble(binding.durationEditText.getText().toString());
            double met = metValues.getFloat(binding.spinner.getSelectedItemPosition(), 0);
            int caloriesBurned = (int) (duration * met * 3.5 * weight / 200);
            caloriesViewModel.updateValues(weight, height, age, isMale, duration, met);
        });

        String[] metStrings = getResources().getStringArray(R.array.met_strings);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(parentActivity, android.R.layout.simple_list_item_1, metStrings);
        binding.spinner.setAdapter(arrayAdapter);

        return binding.getRoot();
    }
}