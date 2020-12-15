package ika.test.runningapp;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class NavGraphDirections {
  private NavGraphDirections() {
  }

  @NonNull
  public static NavDirections actionGlobalCaloriesFragment() {
    return new ActionOnlyNavDirections(R.id.action_global_caloriesFragment);
  }
}
