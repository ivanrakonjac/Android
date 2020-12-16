package ika.t.newsapp.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import ika.t.newsapp.MainActivity;
import ika.t.newsapp.databinding.NewsLayoutBinding;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public interface Callback<T>{
        void invoke(T parameter);
    }

    private final MainActivity mainActivity;
    private final NewsViewModel newsViewModel;
    private final Callback<Integer> callback;

    public NewsAdapter(MainActivity mainActivity, Callback<Integer> callback) {
        this.mainActivity = mainActivity;
        this.newsViewModel = new ViewModelProvider(mainActivity).get(NewsViewModel.class);
        this.callback = callback;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsLayoutBinding newsLayoutBinding = NewsLayoutBinding.inflate(layoutInflater, parent, false);
        return new NewsViewHolder(newsLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.binding.newsImage.setImageDrawable(newsViewModel.getNewsList().get(position).getImage());
        holder.binding.newsTitle.setText(newsViewModel.getNewsList().get(position).getTitle());
        holder.binding.newsSlug.setText(newsViewModel.getNewsList().get(position).getSlug());
    }

    @Override
    public int getItemCount() {
        return newsViewModel.getNewsList().size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        private NewsLayoutBinding binding;

        public NewsViewHolder(@NonNull NewsLayoutBinding newsLayoutBinding) {
            super(newsLayoutBinding.getRoot());
            this.binding = newsLayoutBinding;

            binding.procitajViseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedNewsIndex = getAbsoluteAdapterPosition();
                    callback.invoke(selectedNewsIndex);
                }
            });

            binding.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mainActivity, "Like button", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
