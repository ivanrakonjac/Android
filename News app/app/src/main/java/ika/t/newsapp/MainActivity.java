package ika.t.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

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
import ika.t.newsapp.news.SaobracajFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private NewsViewModel newsViewModel;

    private NewsFragment newsFragment;
    private String NEWS_FRAGMENT_STRING = "NEWS_FRAGMENT";

    private SaobracajFragment saobracajFragment;
    private String SAOBRACAJ_FRAGMENT_STRING = "SAOBRACAJ_FRAGMENT";

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();

        newsFragment = new NewsFragment();
        saobracajFragment = new SaobracajFragment();

        /*fragmentManager
                .beginTransaction()
                .add(R.id.main_frame_layout, newsFragment, NEWS_FRAGMENT_STRING)
                .commit();*/

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        final NavHostFragment navHost = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        navController = navHost.getNavController();

        newsViewModel.getSelectedNews().observe(this, new Observer<News>() {
            @Override
            public void onChanged(News selectedNews) {
                navController.navigate(R.id.action_newsFragment_to_newsDetailsFragment);
            }
        });

        BottomNavigationView bottomMenu = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);

        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem selectedItem) {
                switch (selectedItem.getItemId()){
                    case R.id.menu_item_politika:
                        switch (navController.getCurrentDestination().getId()){
                            case R.id.saobracajFragment:
                                navController.navigate(R.id.action_saobracajFragment_pop);
                                break;
                            default:
                                //Do nothing
                                break;
                        }
                        return true;
                    case R.id.menu_item_saobracaj:
                        switch (navController.getCurrentDestination().getId()){
                            case R.id.newsFragment:
                            case R.id.newsDetailsFragment:
                                navController.navigate(R.id.action_global_saobracajFragment);
                                break;
                            default:
                                //Do nothing
                                break;
                        }
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