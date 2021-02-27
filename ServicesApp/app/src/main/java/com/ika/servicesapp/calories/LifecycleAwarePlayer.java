package com.ika.servicesapp.calories;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

public class LifecycleAwarePlayer implements DefaultLifecycleObserver {

    private MediaPlayer mediaPlayer = null;

    @Inject
    public LifecycleAwarePlayer() {
    }

    public void startSong(Context context){
       if(mediaPlayer == null){
           try {
               String song = "Bad Copy - Uno Due Tre [Necenzurisan] [SPOT].mp3";
               String path = context.getFilesDir().getAbsolutePath() + File.separator + song;

               mediaPlayer = new MediaPlayer();
               mediaPlayer.setDataSource(path);
               mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                   @Override
                   public void onPrepared(MediaPlayer mp) {
                       mp.start();
                   }
               });
               mediaPlayer.prepareAsync();

           } catch (IOException e) {
               e.printStackTrace();
           }
       }

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
       if(mediaPlayer!=null){
           mediaPlayer.stop();
           // Svi resursi koje je mp koristio otpustamo
           mediaPlayer.release();
           mediaPlayer = null;
       }
    }
}
