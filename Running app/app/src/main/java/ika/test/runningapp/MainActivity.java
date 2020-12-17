package ika.test.runningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import ika.test.runningapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_TAG = "running-app-eample";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}