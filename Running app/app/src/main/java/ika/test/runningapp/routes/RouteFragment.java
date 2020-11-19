package ika.test.runningapp.routes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ika.test.runningapp.LifeCycleAwareLogger;
import ika.test.runningapp.MainActivity;
import ika.test.runningapp.R;
import ika.test.runningapp.calories.CaloriesFragment;
import ika.test.runningapp.databinding.FragmentRouteBinding;


public class RouteFragment extends Fragment {

    private RouteViewModel routeViewModel;

    private FragmentRouteBinding binding;
    private FragmentManager childFragmentManager;

    private static final String ROUTE_BROWSE_TAG = "route-browse-tag";
    private static final String ROUTE_DETAILS_TAG = "route-details-tag";

    private RouteBrowseFragment routeBrowseFragment;
    private CaloriesFragment caloriesFragment;

    public RouteFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRouteBinding.inflate(inflater, container, false);

        MainActivity parentActivity = (MainActivity) getActivity();

        routeViewModel = new ViewModelProvider(parentActivity).get(RouteViewModel.class);

        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            routes.add(Route.createFromResources(getResources(), i));
        }
        routeViewModel.setRoutes(routes);


        childFragmentManager = getChildFragmentManager();

        if (childFragmentManager.getFragments().size() == 0) {
            routeBrowseFragment = new RouteBrowseFragment();
            childFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_frame_layout, routeBrowseFragment, ROUTE_BROWSE_TAG)
                    .commit();
        } else {
            routeBrowseFragment = (RouteBrowseFragment) childFragmentManager
                    .findFragmentByTag(ROUTE_BROWSE_TAG);
        }

        routeViewModel.getSelectedRoute().observe(this, new Observer<Route>() {
            @Override
            public void onChanged(Route route) {
                if(route!=null && childFragmentManager.getBackStackEntryCount() == 0){
                    childFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_frame_layout, new RouteDetailsFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });


        return binding.getRoot();
    }
}