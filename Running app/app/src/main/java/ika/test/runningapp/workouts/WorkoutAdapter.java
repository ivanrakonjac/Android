package ika.test.runningapp.workouts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ika.test.runningapp.data.Workout;
import ika.test.runningapp.databinding.ViewHolderWorkoutBinding;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private List<Workout> workoutList;

    public WorkoutAdapter() {

    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public WorkoutAdapter.WorkoutViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderWorkoutBinding viewHolderWorkoutBinding = ViewHolderWorkoutBinding.inflate(
                layoutInflater,
                parent,
                false);
        return new WorkoutAdapter.WorkoutViewHolder(viewHolderWorkoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.WorkoutViewHolder holder, int position) {
        holder.bind(workoutList.get(position));
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public class WorkoutViewHolder extends RecyclerView.ViewHolder {

        public ViewHolderWorkoutBinding binding;

        public WorkoutViewHolder(@NonNull ViewHolderWorkoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Workout workout){
            binding.workoutDate.setText(DateTimeUtil.getSimpleDateFormat().format(workout.getDate()));
            binding.workoutLabel.setText(workout.getLabel());
            binding.workoutDistance.setText(String.format("%.2f km", workout.getDistance()));
            binding.workoutPace.setText(String.format("%s min/km", DateTimeUtil.realMinutesToString(workout.getDuration()/workout.getDistance())));
            binding.workoutDuration.setText(String.format("%.2f min", workout.getDuration()));
        }
    }
}

