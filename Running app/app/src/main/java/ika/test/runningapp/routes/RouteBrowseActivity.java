package ika.test.runningapp.routes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ika.test.runningapp.R;
import ika.test.runningapp.databinding.ActivityRouteBrowseBinding;

public class RouteBrowseActivity extends AppCompatActivity {

    private ActivityRouteBrowseBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_browse);

        binding = ActivityRouteBrowseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            routes.add(Route.createFromResources(getResources(), i));
        }

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new RouteAdapter(routes));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}