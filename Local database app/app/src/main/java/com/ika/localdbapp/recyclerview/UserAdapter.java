package com.ika.localdbapp.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ika.localdbapp.databinding.RecyclerviewRowBinding;
import com.ika.localdbapp.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList = new ArrayList<>();

    public void setUserList(List<User> userList) {
        this.userList = userList;
        //kada se promene stvari, obavesti da bi se opet iscrtalo
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerviewRowBinding userViewHolderBinding = RecyclerviewRowBinding.inflate(
                layoutInflater,
                parent,
                false);
        return new UserViewHolder(userViewHolderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        RecyclerviewRowBinding binding;

        public UserViewHolder(@NonNull RecyclerviewRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind (User user) {
            binding.username.setText(user.getUsername());
            binding.password.setText(user.getPassword());
            binding.firstName.setText(user.getFirsName());
            binding.lastName.setText(user.getLastName());
        }
    }
}

