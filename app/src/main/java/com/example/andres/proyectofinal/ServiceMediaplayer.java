package com.example.andres.proyectofinal;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import java.io.IOException;

public class ServiceMediaplayer extends Service {

    // Binder given to clients
    private final IBinder mBinder = new MediaPlayerBinder();
    private MediaPlayer mediaPlayer;
    private boolean muted = false;

    public boolean play() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            return false;
        }
        else{
            mediaPlayer.start();
            return true;
        }
    }

    public boolean setVol() {
        if (muted){
            mediaPlayer.setVolume(1,1);
            return true;
        }
        else{
            mediaPlayer.setVolume(0, 0);
            return false;
        }
    }

    public void replaySong() {
        mediaPlayer.stop();
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();




        /*boolean playing = mediaPlayer.isPlaying();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer = null;
        mediaPlayer = MediaPlayer.create(this, R.raw.musica1);
        mediaPlayer.start();*/
    }

    public void Stop() {
        mediaPlayer.stop();
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*boolean playing = mediaPlayer.isPlaying();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer = null;
        mediaPlayer = MediaPlayer.create(this, R.raw.musica1);*/
    }

    public class MediaPlayerBinder extends Binder {
        ServiceMediaplayer getService() {
            return ServiceMediaplayer.this;
        }
    }


    public ServiceMediaplayer() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void playRaw() {
        mediaPlayer = MediaPlayer.create(this, R.raw.musica1);
    }
}
