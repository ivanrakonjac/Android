package ika.test.runningapp.routes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ika.test.runningapp.LifeCycleAwareLogger;
import ika.test.runningapp.MainActivity;
import ika.test.runningapp.R;
import ika.test.runningapp.databinding.FragmentRouteBrowseBinding;

public class RouteBrowseFragment extends Fragment {

    private FragmentRouteBrowseBinding binding;

    public RouteBrowseFragment() {
        getLifecycle().addObserver(new LifeCycleAwareLogger("LIFE_CYCLE_TAG", "RouteBrowseFragment"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRouteBrowseBinding.inflate(inflater, container, false);

        MainActivity parentActivity = (MainActivity) getActivity();

        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            routes.add(Route.createFromResources(getResources(), i));
        }

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new RouteAdapter(routes));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));

        return binding.getRoot();
    }
}