// Generated by view binder compiler. Do not edit!
package ika.test.runningapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import ika.test.runningapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextInputLayout age;

  @NonNull
  public final TextInputEditText ageEditText;

  @NonNull
  public final TextInputLayout duration;

  @NonNull
  public final TextInputEditText durationEditText;

  @NonNull
  public final TextInputLayout height;

  @NonNull
  public final TextInputEditText heightEditText;

  @NonNull
  public final Button izracunaj;

  @NonNull
  public final RadioButton male;

  @NonNull
  public final TextView rezultat;

  @NonNull
  public final TextView rezultatPotrebnoKalorija;

  @NonNull
  public final RadioGroup sex;

  @NonNull
  public final Spinner spinner;

  @NonNull
  public final TextInputLayout weight;

  @NonNull
  public final TextInputEditText weightEditText;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull TextInputLayout age,
      @NonNull TextInputEditText ageEditText, @NonNull TextInputLayout duration,
      @NonNull TextInputEditText durationEditText, @NonNull TextInputLayout height,
      @NonNull TextInputEditText heightEditText, @NonNull Button izracunaj,
      @NonNull RadioButton male, @NonNull TextView rezultat,
      @NonNull TextView rezultatPotrebnoKalorija, @NonNull RadioGroup sex, @NonNull Spinner spinner,
      @NonNull TextInputLayout weight, @NonNull TextInputEditText weightEditText) {
    this.rootView = rootView;
    this.age = age;
    this.ageEditText = ageEditText;
    this.duration = duration;
    this.durationEditText = durationEditText;
    this.height = height;
    this.heightEditText = heightEditText;
    this.izracunaj = izracunaj;
    this.male = male;
    this.rezultat = rezultat;
    this.rezultatPotrebnoKalorija = rezultatPotrebnoKalorija;
    this.sex = sex;
    this.spinner = spinner;
    this.weight = weight;
    this.weightEditText = weightEditText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.age;
      TextInputLayout age = rootView.findViewById(id);
      if (age == null) {
        break missingId;
      }

      id = R.id.ageEditText;
      TextInputEditText ageEditText = rootView.findViewById(id);
      if (ageEditText == null) {
        break missingId;
      }

      id = R.id.duration;
      TextInputLayout duration = rootView.findViewById(id);
      if (duration == null) {
        break missingId;
      }

      id = R.id.durationEditText;
      TextInputEditText durationEditText = rootView.findViewById(id);
      if (durationEditText == null) {
        break missingId;
      }

      id = R.id.height;
      TextInputLayout height = rootView.findViewById(id);
      if (height == null) {
        break missingId;
      }

      id = R.id.heightEditText;
      TextInputEditText heightEditText = rootView.findViewById(id);
      if (heightEditText == null) {
        break missingId;
      }

      id = R.id.izracunaj;
      Button izracunaj = rootView.findViewById(id);
      if (izracunaj == null) {
        break missingId;
      }

      id = R.id.male;
      RadioButton male = rootView.findViewById(id);
      if (male == null) {
        break missingId;
      }

      id = R.id.rezultat;
      TextView rezultat = rootView.findViewById(id);
      if (rezultat == null) {
        break missingId;
      }

      id = R.id.rezultatPotrebnoKalorija;
      TextView rezultatPotrebnoKalorija = rootView.findViewById(id);
      if (rezultatPotrebnoKalorija == null) {
        break missingId;
      }

      id = R.id.sex;
      RadioGroup sex = rootView.findViewById(id);
      if (sex == null) {
        break missingId;
      }

      id = R.id.spinner;
      Spinner spinner = rootView.findViewById(id);
      if (spinner == null) {
        break missingId;
      }

      id = R.id.weight;
      TextInputLayout weight = rootView.findViewById(id);
      if (weight == null) {
        break missingId;
      }

      id = R.id.weightEditText;
      TextInputEditText weightEditText = rootView.findViewById(id);
      if (weightEditText == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, age, ageEditText, duration,
          durationEditText, height, heightEditText, izracunaj, male, rezultat,
          rezultatPotrebnoKalorija, sex, spinner, weight, weightEditText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
