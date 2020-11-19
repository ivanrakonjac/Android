package ika.test.runningapp.routes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ika.test.runningapp.MainActivity;
import ika.test.runningapp.databinding.ViewHolderRouteBinding;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {

    private final RouteViewModel routeViewModel;
    private final MainActivity mainActivity;

    public RouteAdapter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.routeViewModel = new ViewModelProvider(this.mainActivity).get(RouteViewModel.class);
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderRouteBinding viewHolderRouteBinding = ViewHolderRouteBinding.inflate(layoutInflater,parent,false);
        return new RouteViewHolder(viewHolderRouteBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        ViewHolderRouteBinding binding = holder.binding;

        Route route = routeViewModel.getRoutes().get(position);

        binding.routImage.setImageDrawable(route.getImage());
        binding.routName.setText(route.getName());
        binding.routLabel.setText(route.getLabel());
        binding.routDuzina.setText(route.getLength() + "");
        binding.routTezina.setText(route.getDifficulty());
    }

    @Override
    public int getItemCount() {
        return routeViewModel.getRoutes().size();
    }

    public class RouteViewHolder extends RecyclerView.ViewHolder{

        private ViewHolderRouteBinding binding;

        public RouteViewHolder(@NonNull ViewHolderRouteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.opisButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int routeIndex = getAdapterPosition();
                    routeViewModel.setSelectedRoute(routeViewModel.getRoutes().get(routeIndex));
                }
            });

        }
    }
}
