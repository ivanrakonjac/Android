package ika.t.newsapp.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ika.t.newsapp.MainActivity;
import ika.t.newsapp.R;
import ika.t.newsapp.databinding.FragmentNewsBinding;
import ika.t.newsapp.databinding.FragmentNewsDetailsBinding;

public class NewsDetailsFragment extends Fragment {

    private FragmentNewsDetailsBinding binding;
    private NewsViewModel newsViewModel;

    public NewsDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false);
        MainActivity parentActivity = (MainActivity) getActivity();
        newsViewModel = new ViewModelProvider(parentActivity).get(NewsViewModel.class);

        int selectedNewsIndex = NewsDetailsFragmentArgs.fromBundle(requireArguments()).getNewsIndex();
        News selectedNews = newsViewModel.getNewsList().get(selectedNewsIndex);

        binding.newsImage.setImageDrawable(selectedNews.getImage());
        binding.newsTitle.setText(selectedNews.getTitle());
        binding.newsText.setText(selectedNews.getText());

        return binding.getRoot();
    }
}