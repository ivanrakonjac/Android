package ika.test.runningapp;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    public static final String CALORIES_BURNED_KEY = "calories-burned";

    private final MutableLiveData<Integer> caloriesBurned = new MutableLiveData<>();
    private  boolean caloriesBurnedValied = false;
    //private int caloriesBUrned = -1;

    public LiveData<Integer> getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int value) {
        caloriesBurnedValied = true;
        caloriesBurned.setValue(value);
    }

    public void initByBundle(Bundle bundle){
        if(bundle != null){
            if(!caloriesBurnedValied){
                if(bundle.containsKey(CALORIES_BURNED_KEY)){
                    caloriesBurnedValied = true;
                    caloriesBurned.setValue(bundle.getInt(CALORIES_BURNED_KEY));
                }
            }
        }
    }
}
