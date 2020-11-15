package ika.narucivanje.proizvodi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ika.narucivanje.databinding.ProizvodViewHolderBinding;

public class ProizvodiAdapter extends RecyclerView.Adapter<ProizvodiAdapter.ProizvodViewHOlder> {

    private List<Proizvod> proizvodi;

    public ProizvodiAdapter(List<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }

    @NonNull
    @Override
    public ProizvodViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProizvodViewHolderBinding proizvodViewHolderBinding = ProizvodViewHolderBinding.inflate(layoutInflater, parent, false);
        return new ProizvodViewHOlder(proizvodViewHolderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProizvodViewHOlder holder, int position) {
        ProizvodViewHolderBinding binding = holder.binding;

        Proizvod proizvod = proizvodi.get(position);
        binding.proizvodIme.setText(proizvodi.get(position).getIme());
    }

    @Override
    public int getItemCount() {
        return proizvodi.size();
    }

    public class ProizvodViewHOlder extends RecyclerView.ViewHolder{

        private ProizvodViewHolderBinding binding;

        public ProizvodViewHOlder(@NonNull ProizvodViewHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
