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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    private MainActivity mainActivity;

    public RouteBrowseFragment() {
        //getLifecycle().addObserver(new LifeCycleAwareLogger("LIFE_CYCLE_TAG", "RouteBrowseFragment"));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Izmestili smo ove stvari ovde, da se ne bi generisale svaki put kada se pravi View

        mainActivity = (MainActivity) requireActivity();
        routeViewModel = new ViewModelProvider(mainActivity).get(RouteViewModel.class);

        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            routes.add(Route.createFromResources(getResources(), i));
        }
        routeViewModel.setRoutes(routes);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRouteBrowseBinding.inflate(inflater, container, false);

        RouteAdapter routeAdapter = new RouteAdapter(
                routeViewModel,
                routeIndex -> {
                    RouteBrowseFragmentDirections.ActionShowRouteDetails action = RouteBrowseFragmentDirections.actionShowRouteDetails();
                    action.setRouteIndex(routeIndex);
                    navController.navigate(action);
                },
                routeIndex -> {
                    Toast.makeText(mainActivity, "Pokreni mapu", Toast.LENGTH_SHORT).show();
                }
        );
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(routeAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

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