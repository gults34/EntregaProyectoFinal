package com.example.andres.proyectofinal;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

public class ServiceMediaplayer extends Service {

    // Binder given to clients
    private final IBinder mBinder = new MediaPlayerBinder();
    private MediaPlayer mediaPlayer;
    private boolean muted = false;
    private boolean primera = true;

    public boolean playSong() {
        if (primera) {
            primera = false;
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.musica1);
        }
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
            muted = false;
            return true;
        }
        else{
            mediaPlayer.setVolume(0, 0);
            muted = true;
            return false;
        }
    }

    public void replaySong() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        mediaPlayer = MediaPlayer.create(this, R.raw.musica1);
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
        mediaPlayer.release();
        mediaPlayer = null;
        mediaPlayer = MediaPlayer.create(this, R.raw.musica1);
        primera = true;
        /*boolean playing = mediaPlayer.isPlaying();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer = null;
        mediaPlayer = MediaPlayer.create(this, R.raw.musica1);*/
    }

    public void salir() {
        if (!primera) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
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
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
