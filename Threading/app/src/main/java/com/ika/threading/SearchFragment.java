package com.ika.threading;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ika.threading.databinding.FragmentFavoritesBinding;
import com.ika.threading.databinding.FragmentSearchBinding;
import com.ika.threading.db.UserDatabase;
import com.ika.threading.recyclerview.UserAdapter;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        UserAdapter userAdapter = new UserAdapter();

        UserDatabase userDatabase = UserDatabase.getInstance((MainActivity) getActivity());

        userDatabase.userDao().getAllLiveData().observe(getViewLifecycleOwner(), userList -> {
            userAdapter.setUserList(userList);
        } );

        binding.recyclerView.setAdapter(userAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = SearchFragmentDirections.actionSearchFragmentToAddNewUserFragment();
                Navigation.findNavController(v).navigate(action);
            }
        });




        return binding.getRoot();
    }
}