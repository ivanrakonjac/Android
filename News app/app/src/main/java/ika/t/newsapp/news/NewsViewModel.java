package ika.t.newsapp.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private List<News> newsList;

    private MutableLiveData<News> selectedNews = new MutableLiveData<>();

    public MutableLiveData<News> getSelectedNews() {
        return selectedNews;
    }

    public void setSelectedNews(News selectedNews) {
        this.selectedNews.setValue(selectedNews);
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
