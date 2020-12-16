package ika.t.newsapp.news;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ika.t.newsapp.MainActivity;
import ika.t.newsapp.R;
import ika.t.newsapp.databinding.FragmentNewsBinding;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel newsViewModel;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewsBinding.inflate(inflater, container, false);

        MainActivity parentActivity = (MainActivity) getActivity();
        newsViewModel = new ViewModelProvider(parentActivity).get(NewsViewModel.class);

        String[] newsTitles = getResources().getStringArray(R.array.news_titles);
        String[] newsTexts = getResources().getStringArray(R.array.news_text);
        TypedArray newsImages = getResources().obtainTypedArray(R.array.news_images);

        List<News> newsList = new ArrayList<>();
        for (int i=0 ; i< newsTitles.length ; i++){
            newsList.add(new News(newsTitles[i], newsTexts[i], newsImages.getDrawable(i)));
        }
        newsViewModel.setNewsList(newsList);

        NewsAdapter newsAdapter = new NewsAdapter(
                parentActivity,
                newsIndex -> {
                    NewsFragmentDirections.ActionNewsFragmentToNewsDetailsFragment action = NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment();
                    action.setNewsIndex(newsIndex);
                    MainActivity.getNavController().navigate(action);
                }
        );

        binding.newsRecyclerView.setHasFixedSize(true);
        binding.newsRecyclerView.setAdapter(newsAdapter);
        binding.newsRecyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));

        return binding.getRoot();
    }
}