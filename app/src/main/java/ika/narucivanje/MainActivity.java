package ika.narucivanje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ika.narucivanje.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "activity-lifecycle";

    private ActivityMainBinding activityMainBinding;
    private  MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLifecycle().addObserver(new MyLifeCycleAwareComponent());

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        /*
        * Dohvatamo LiveData objekat quantity koji je svestan Life-Cyclea
        * Kacimo observer na njega, i prosledjujemo zivotni ciklus objekta koji ga posmatra (observuje)
        * Nakon toga definisemo sta se desava onChanged
         */
        myViewModel.getQuantity().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                activityMainBinding.textView.setText("Kolicina: " + integer);
            }
        });

        activityMainBinding.button.setOnClickListener(view -> {
            int quantity = Integer.parseInt(activityMainBinding.editText.getText().toString());
            myViewModel.setQuantity(quantity);
        });
    }

    /*
    * Poziva se pre nego sto onDestory() moze biti pozvan
    * Ovu metodu treba iskoristiti za cuvanje svih neophodnih podataka koje ne bismo smeli izgubiti
    * */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if(myViewModel.getQuantity().getValue() != null){
            outState.putInt("QUANTITY_KEY",myViewModel.getQuantity().getValue());
        }

        Log.d(LOG_TAG, "onSaveInstanceState() called");
    }
}