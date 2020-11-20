package ika.t.newsapp.news;

import android.graphics.drawable.Drawable;

public class News {

    private final String title;
    private final String text;
    private final Drawable image;

    public News(String title, String text, Drawable image) {
        this.title = title;
        this.text = text;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Drawable getImage() {
        return image;
    }

    public String getSlug(){
        return text.substring(0,150);
    }
}
