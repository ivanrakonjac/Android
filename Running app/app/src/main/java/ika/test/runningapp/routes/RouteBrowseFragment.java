package ika.test.runningapp.routes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
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
    private RouteViewModel routeViewModel;

    private NavController navController;

    public RouteBrowseFragment() {
        getLifecycle().addObserver(new LifeCycleAwareLogger("LIFE_CYCLE_TAG", "RouteBrowseFragment"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRouteBrowseBinding.inflate(inflater, container, false);

        MainActivity parentActivity = (MainActivity) getActivity();

        routeViewModel = new ViewModelProvider(parentActivity).get(RouteViewModel.class);

        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            routes.add(Route.createFromResources(getResources(), i));
        }
        routeViewModel.setRoutes(routes);

        routeViewModel.getSelectedRoute().observe(getViewLifecycleOwner(), new Observer<Route>() {
            @Override
            public void onChanged(Route route) {
                if(route!=null){
                    // Da bi mogli da dohvatimo detailsFragment
                    NavDirections action = RouteBrowseFragmentDirections.actionShowRouteDetails();
                    navController.navigate(action);
                }
            }
        });

        routeViewModel = new ViewModelProvider(parentActivity).get(RouteViewModel.class);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(new RouteAdapter(parentActivity));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Dohvatamo NavController da bi mogli da odemo na details o nekoj ruti
        // Radimo to u ovoj metodi, a ne u onCreate, jer se view pravi posle fragmenta
        navController = Navigation.findNavController(view);
    }
}