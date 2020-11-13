package ika.test.runningapp.calories;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CaloriesViewModel extends ViewModel {

    public static final String CALORIES_BURNED_KEY = "calories-burned";
    public static final String CALORIES_NEEDED_KEY = "calories-needed";

    private final MutableLiveData<Integer> caloriesBurned = new MutableLiveData<>(-1);
    private final MutableLiveData<Integer> caloriesNeeded = new MutableLiveData<>(-1);

    private  boolean dataValid = false;

    public LiveData<Integer> getCaloriesBurned() {
        return caloriesBurned;
    }

    public LiveData<Integer> getCaloriesNeeded() {
        return caloriesNeeded;
    }

    public void setCaloriesBurned(int value) {
        dataValid = true;
        caloriesBurned.setValue(value);
    }

    public void initByBundle(Bundle bundle){
        if(bundle != null){
            if(!dataValid){
                if(bundle.containsKey(CALORIES_BURNED_KEY)){
                    dataValid = true;
                    caloriesBurned.setValue(bundle.getInt(CALORIES_BURNED_KEY));
                }
            }
        }
    }

    public void updateValues(double weight, double height, int age, boolean isMale, double duration, double met){

        double caloriesNeededValue = 0;

        if(isMale){
            caloriesNeededValue = (int) 66 + 13.7 * weight + 5 * height - 6.8 * age;
        }else{
            caloriesNeededValue = (int) 655 + 9.6 * weight + 1.9 * height - 4.7 * age;
        }
        double caloriesBurnedValue = duration * met * 3.5 * weight / 200;

        caloriesBurned.setValue((int) caloriesBurnedValue);
        caloriesNeeded.setValue((int) caloriesNeededValue);
    }
}
