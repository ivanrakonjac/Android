package ika.test.runningapp.workouts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import ika.test.runningapp.MainActivity;
import ika.test.runningapp.R;
import ika.test.runningapp.data.RunDatabase;
import ika.test.runningapp.data.Workout;
import ika.test.runningapp.data.WorkoutRepository;
import ika.test.runningapp.databinding.FragmentWorkoutListBinding;


public class WorkoutListFragment extends Fragment {

    private FragmentWorkoutListBinding binding;
    private WorkoutViewModel workoutViewModel;

    private NavController navController;
    private MainActivity mainActivity;

    public WorkoutListFragment() {
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
        workoutViewModel = new ViewModelProvider(mainActivity, factory).get(WorkoutViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWorkoutListBinding.inflate(inflater, container, false);

        WorkoutAdapter workoutAdapter = new WorkoutAdapter();
        LiveData<List<Workout>> workoutLiveDataList = workoutViewModel.getWorkoutList();

        workoutLiveDataList.observe(getViewLifecycleOwner(), workoutAdapter::setWorkoutList);

        binding.recyclerView.setAdapter(workoutAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        binding.floatingActionButton.setOnClickListener(view ->{
            NavDirections action = WorkoutListFragmentDirections.actionWorkoutListFragmentToWorkoutCreateFragment();
            navController.navigate(action);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

}