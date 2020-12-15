package ika.test.runningapp.routes;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import ika.test.runningapp.NavGraphDirections;

public class RouteDetailsFragmentDirections {
  private RouteDetailsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionGlobalCaloriesFragment() {
    return NavGraphDirections.actionGlobalCaloriesFragment();
  }
}
