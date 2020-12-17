package ika.test.runningapp.routes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ika.test.runningapp.databinding.ViewHolderRouteBinding;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {

    public interface Callback<T> {
        void invoke(T parameter);
    }

    private final RouteViewModel routeViewModel;

    private final Callback<Integer> callbackDescription;
    private final Callback<Integer> callbackLocation;

    public RouteAdapter(RouteViewModel routeViewModel, Callback<Integer> callbackDescription, Callback<Integer> callbackLocation){
        this.routeViewModel = routeViewModel;
        this.callbackDescription = callbackDescription;
        this.callbackLocation = callbackLocation;
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
                    callbackDescription.invoke(routeIndex);
                }
            });

            binding.lokacijaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int routeIndex = getAdapterPosition();
                    callbackLocation.invoke(routeIndex);
                }
            });


        }
    }
}
