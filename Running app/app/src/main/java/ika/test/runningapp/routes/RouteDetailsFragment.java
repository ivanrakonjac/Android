package ika.test.runningapp.routes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ika.test.runningapp.MainActivity;
import ika.test.runningapp.R;
import ika.test.runningapp.databinding.FragmentRouteBrowseBinding;
import ika.test.runningapp.databinding.FragmentRouteDetailsBinding;

public class RouteDetailsFragment extends Fragment {

    private FragmentRouteDetailsBinding binding;
    private RouteViewModel routeViewModel;

    private NavController navController;
    private MainActivity mainActivity;

    public RouteDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Prebaceno u onCreate jer se ove stvari najcesce 1 rade za zivotni ciklus nekog fragmenta
        mainActivity = (MainActivity) requireActivity(); // Zahtevamo da se fragment nalazi u okviru neke aktivnosti, baca se izuzetak ako nije tako
        routeViewModel = new ViewModelProvider(mainActivity).get(RouteViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRouteDetailsBinding.inflate(inflater, container, false);

        int routeIndex = RouteDetailsFragmentArgs.fromBundle(requireArguments()).getRouteIndex();
        Route selectedRoute = routeViewModel.getRoutes().get(routeIndex);

        binding.toolbar.setTitle(selectedRoute.getLabel());
        binding.toolbar.setNavigationOnClickListener(view -> {
            navController.navigateUp();
        });

        binding.image.setImageDrawable(selectedRoute.getImage());
        binding.name.setText(selectedRoute.getName());
        binding.label.setText(selectedRoute.getLabel());
        binding.description.setText(selectedRoute.getDescription());
        binding.difficulty.setText(selectedRoute.getDifficulty());
        binding.length.setText(selectedRoute.getLength() + " km");

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}