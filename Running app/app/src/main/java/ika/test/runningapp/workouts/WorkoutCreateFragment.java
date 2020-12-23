package ika.test.runningapp.workouts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;

import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import ika.test.runningapp.MainActivity;
import ika.test.runningapp.data.RunDatabase;
import ika.test.runningapp.data.Workout;
import ika.test.runningapp.data.WorkoutRepository;
import ika.test.runningapp.databinding.FragmentWorkoutCreateBinding;

public class WorkoutCreateFragment extends Fragment {

    public static final String REQUEST_KEY = "date-picker-request";

    private FragmentWorkoutCreateBinding binding;
    private WorkoutViewModel workoutViewModel;
    private NavController navController;
    private MainActivity mainActivity;

    public WorkoutCreateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) requireActivity();
        RunDatabase runDatabase = RunDatabase.getInstance(mainActivity);
        WorkoutRepository workoutRepository = new WorkoutRepository(runDatabase.workoutDao());
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new WorkoutViewModel(workoutRepository);
            }
        };
        workoutViewModel = new ViewModelProvider(mainActivity).get(WorkoutViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWorkoutCreateBinding.inflate(inflater, container, false);

        binding.toolbar.setNavigationOnClickListener(
                view -> navController.navigateUp());

        binding.workoutDateEditText.setOnClickListener(view -> {
            new DatePickerFragment().show(getChildFragmentManager(), null);
        });

        getChildFragmentManager().setFragmentResultListener(REQUEST_KEY, this, (requestKey, result)->{
            Date date = (Date) result.getSerializable(DatePickerFragment.SET_DATE_KEY);
            String dateForEditText = DateTimeUtil.getSimpleDateFormat().format(date);
            binding.workoutDateEditText.setText(dateForEditText);
        });

        binding.create.setOnClickListener(view -> {
            Date date = (Date) parse(binding.workoutDate, DateTimeUtil.getSimpleDateFormat());
            String label = (String) parse(binding.workoutLabel, null);
            Number distance = (Number) parse(binding.workoutDistance, NumberFormat.getIntegerInstance());
            Number duration = (Number) parse(binding.workoutDuration , NumberFormat.getIntegerInstance());

            if(!(date ==null || label==null || distance==null || duration==null)){
                Workout workout = new Workout(0, date, label, distance.doubleValue(), duration.doubleValue());
                workoutViewModel.insertWorkout(workout);

                navController.navigateUp();
            }

        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private Object parse(TextInputLayout textInputLayout, Format format){
        Object result;
        try{
            String inputString = textInputLayout.getEditText().getText().toString();
            if(!inputString.equals("")){
                if(format != null){
                    result = format.parseObject(inputString);
                }else{
                    result = inputString;
                }
                textInputLayout.setError(null);
            }else{
                textInputLayout.setError("Obavezno polje!");
                result = null;
            }
        } catch (ParseException e) {
            textInputLayout.setError("Pogresan format!");
            result = null;
        }
        return result;
    }

}
