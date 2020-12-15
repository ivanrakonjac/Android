package ika.test.runningapp.routes;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class RouteDetailsFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private RouteDetailsFragmentArgs() {
  }

  private RouteDetailsFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static RouteDetailsFragmentArgs fromBundle(@NonNull Bundle bundle) {
    RouteDetailsFragmentArgs __result = new RouteDetailsFragmentArgs();
    bundle.setClassLoader(RouteDetailsFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("routeIndex")) {
      int routeIndex;
      routeIndex = bundle.getInt("routeIndex");
      __result.arguments.put("routeIndex", routeIndex);
    } else {
      __result.arguments.put("routeIndex", 0);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getRouteIndex() {
    return (int) arguments.get("routeIndex");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    RouteDetailsFragmentArgs that = (RouteDetailsFragmentArgs) object;
    if (arguments.containsKey("routeIndex") != that.arguments.containsKey("routeIndex")) {
      return false;
    }
    if (getRouteIndex() != that.getRouteIndex()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getRouteIndex();
    return result;
  }

  @Override
  public String toString() {
    return "RouteDetailsFragmentArgs{"
        + "routeIndex=" + getRouteIndex()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(RouteDetailsFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public RouteDetailsFragmentArgs build() {
      RouteDetailsFragmentArgs result = new RouteDetailsFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setRouteIndex(int routeIndex) {
      this.arguments.put("routeIndex", routeIndex);
      return this;
    }

    @SuppressWarnings("unchecked")
    public int getRouteIndex() {
      return (int) arguments.get("routeIndex");
    }
  }
}
