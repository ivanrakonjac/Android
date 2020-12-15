package ika.test.runningapp.routes;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import ika.test.runningapp.NavGraphDirections;
import ika.test.runningapp.R;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class RouteBrowseFragmentDirections {
  private RouteBrowseFragmentDirections() {
  }

  @NonNull
  public static ActionShowRouteDetails actionShowRouteDetails() {
    return new ActionShowRouteDetails();
  }

  @NonNull
  public static NavDirections actionGlobalCaloriesFragment() {
    return NavGraphDirections.actionGlobalCaloriesFragment();
  }

  public static class ActionShowRouteDetails implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionShowRouteDetails() {
    }

    @NonNull
    public ActionShowRouteDetails setRouteIndex(int routeIndex) {
      this.arguments.put("routeIndex", routeIndex);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("routeIndex")) {
        int routeIndex = (int) arguments.get("routeIndex");
        __result.putInt("routeIndex", routeIndex);
      } else {
        __result.putInt("routeIndex", 0);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_show_routeDetails;
    }

    @SuppressWarnings("unchecked")
    public int getRouteIndex() {
      return (int) arguments.get("routeIndex");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionShowRouteDetails that = (ActionShowRouteDetails) object;
      if (arguments.containsKey("routeIndex") != that.arguments.containsKey("routeIndex")) {
        return false;
      }
      if (getRouteIndex() != that.getRouteIndex()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + getRouteIndex();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionShowRouteDetails(actionId=" + getActionId() + "){"
          + "routeIndex=" + getRouteIndex()
          + "}";
    }
  }
}
