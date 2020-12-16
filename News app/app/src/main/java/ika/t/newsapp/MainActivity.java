package ika.t.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ika.t.newsapp.databinding.ActivityMainBinding;
import ika.t.newsapp.news.EmptyFragment;
import ika.t.newsapp.news.News;
import ika.t.newsapp.news.NewsDetailsFragment;
import ika.t.newsapp.news.NewsFragment;
import ika.t.newsapp.news.NewsViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private NewsViewModel newsViewModel;

    private NewsFragment newsFragment;
    private String NEWS_FRAGMENT_STRING = "NEWS_FRAGMENT_STRING";
    private String NEWS_FRAGMENT_STRING_LANDSCAPE = "NEWS_FRAGMENT_STRING_LANDSCAPE";

    private EmptyFragment emptyFragment;
    private String NEWS_EMPTY_FRAGMENT_STRING = "NEWS_EMPTY_FRAGMENT_STRING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        newsViewModel.getSelectedNews().observe(this, new Observer<News>() {
            @Override
            public void onChanged(News selectedNews) {
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    if(selectedNews != null){
                        fragmentManager
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main_frame_layout, new NewsDetailsFragment(), "DETAILS")
                                .commit();
                    }
                }else{
                    if(selectedNews != null){
                        fragmentManager
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.main_frame_layout_empty, new NewsDetailsFragment(), "DETAILS")
                                .commit();
                    }
                }

            }
        });


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if (fragmentManager.getFragments().size() == 0) {
                newsFragment = new NewsFragment();
                fragmentManager
                        .beginTransaction()
                        .add(R.id.main_frame_layout, newsFragment, NEWS_FRAGMENT_STRING)
                        .commit();
            } else {
                newsFragment = (NewsFragment) fragmentManager
                        .findFragmentByTag(NEWS_FRAGMENT_STRING);
            }
        }else{
            if (fragmentManager.getFragments().size() == 0) {
                newsFragment = new NewsFragment();
                emptyFragment = new EmptyFragment();
                fragmentManager
                        .beginTransaction()
                        .add(R.id.main_frame_layout, newsFragment, NEWS_FRAGMENT_STRING_LANDSCAPE)
                        .add(R.id.main_frame_layout_empty, emptyFragment, NEWS_EMPTY_FRAGMENT_STRING)
                        .commit();
            } else {
                newsFragment = (NewsFragment) fragmentManager.findFragmentByTag(NEWS_FRAGMENT_STRING_LANDSCAPE);
                emptyFragment = (EmptyFragment) fragmentManager.findFragmentByTag(NEWS_EMPTY_FRAGMENT_STRING);
            }
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem selectedItem) {
                switch (selectedItem.getItemId()){
                    case R.id.menu_item_politika:
                        Toast.makeText(getBaseContext(), "Politika", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_item_sport:
                        Toast.makeText(getBaseContext(), "Sport", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.like_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.like:
                Toast.makeText(this, "Odaberi neku vest", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }


}