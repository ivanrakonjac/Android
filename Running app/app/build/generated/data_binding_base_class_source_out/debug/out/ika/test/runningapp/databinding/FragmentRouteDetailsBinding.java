// Generated by view binder compiler. Do not edit!
package ika.test.runningapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import ika.test.runningapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRouteDetailsBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final TextView description;

  @NonNull
  public final TextView difficulty;

  @NonNull
  public final ImageView image;

  @NonNull
  public final TextView label;

  @NonNull
  public final TextView length;

  @NonNull
  public final LinearLayout linLayout;

  @NonNull
  public final TextView name;

  private FragmentRouteDetailsBinding(@NonNull ScrollView rootView, @NonNull TextView description,
      @NonNull TextView difficulty, @NonNull ImageView image, @NonNull TextView label,
      @NonNull TextView length, @NonNull LinearLayout linLayout, @NonNull TextView name) {
    this.rootView = rootView;
    this.description = description;
    this.difficulty = difficulty;
    this.image = image;
    this.label = label;
    this.length = length;
    this.linLayout = linLayout;
    this.name = name;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRouteDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRouteDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_route_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRouteDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.description;
      TextView description = rootView.findViewById(id);
      if (description == null) {
        break missingId;
      }

      id = R.id.difficulty;
      TextView difficulty = rootView.findViewById(id);
      if (difficulty == null) {
        break missingId;
      }

      id = R.id.image;
      ImageView image = rootView.findViewById(id);
      if (image == null) {
        break missingId;
      }

      id = R.id.label;
      TextView label = rootView.findViewById(id);
      if (label == null) {
        break missingId;
      }

      id = R.id.length;
      TextView length = rootView.findViewById(id);
      if (length == null) {
        break missingId;
      }

      id = R.id.linLayout;
      LinearLayout linLayout = rootView.findViewById(id);
      if (linLayout == null) {
        break missingId;
      }

      id = R.id.name;
      TextView name = rootView.findViewById(id);
      if (name == null) {
        break missingId;
      }

      return new FragmentRouteDetailsBinding((ScrollView) rootView, description, difficulty, image,
          label, length, linLayout, name);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
