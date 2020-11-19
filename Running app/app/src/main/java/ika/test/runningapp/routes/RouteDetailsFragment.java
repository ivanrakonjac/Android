package ika.test.runningapp.routes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

    public RouteDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRouteDetailsBinding.inflate(inflater, container, false);

        //posto ce ici u RouteFragment mora se na ovakav nacin dogva
        MainActivity parentActivity = (MainActivity) getParentFragment().getActivity();

        routeViewModel = new ViewModelProvider(parentActivity).get(RouteViewModel.class);

        routeViewModel.getSelectedRoute().observe(this, new Observer<Route>() {
            @Override
            public void onChanged(Route selectedRoute) {
                if(selectedRoute!=null){
                    binding.image.setImageDrawable(selectedRoute.getImage());
                    binding.name.setText(selectedRoute.getName());
                    binding.label.setText(selectedRoute.getLabel());
                    binding.description.setText(selectedRoute.getDescription());
                    binding.difficulty.setText(selectedRoute.getDifficulty());
                    binding.length.setText(selectedRoute.getLength() + " km");
                }
            }
        });

        return binding.getRoot();
    }


}