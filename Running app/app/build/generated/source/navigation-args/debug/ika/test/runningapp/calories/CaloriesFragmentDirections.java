package ika.test.runningapp.calories;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import ika.test.runningapp.NavGraphDirections;
import ika.test.runningapp.R;

public class CaloriesFragmentDirections {
  private CaloriesFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionCaloriesFragmentPop() {
    return new ActionOnlyNavDirections(R.id.action_caloriesFragment_pop);
  }

  @NonNull
  public static NavDirections actionGlobalCaloriesFragment() {
    return NavGraphDirections.actionGlobalCaloriesFragment();
  }
}
