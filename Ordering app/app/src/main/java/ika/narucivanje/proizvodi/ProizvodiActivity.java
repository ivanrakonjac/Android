package ika.narucivanje.proizvodi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ika.narucivanje.R;
import ika.narucivanje.databinding.ActivityProizvodiBinding;

public class ProizvodiActivity extends AppCompatActivity {

    private ActivityProizvodiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proizvodi);

        binding = ActivityProizvodiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Proizvod> proizvodList = new ArrayList<>();
        for (int i=0; i<9; i++){
            proizvodList.add(Proizvod.createFromResources(getResources(), i));
            Log.d("dodavanje", proizvodList.get(i).toString());
        }

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new ProizvodiAdapter(proizvodList));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this ));

    }
}