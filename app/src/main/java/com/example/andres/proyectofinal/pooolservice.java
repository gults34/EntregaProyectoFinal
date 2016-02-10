/*
package com.example.polpincho.proyectofinal;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
public class MediaPlayerServ extends Service {
    // Binder given to clients
    File SDcard = Environment.getExternalStorageDirectory();
    File Music = new File(SDcard.getAbsolutePath()+"/Music");
    private final IBinder mBinder = new MediaPlayerBinder();
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Boolean muted = false;
    private ArrayList<String> s;
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
    public boolean SetVolume() {
        if (muted){
            mediaPlayer.setVolume(1,1);
            muted = false;
            return false;
        }
        else{
            mediaPlayer.setVolume(0, 0);
            muted = true;
            return true;
        }
    }
    public boolean Replay() {
        boolean playing = mediaPlayer.isPlaying();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer = null;
        mediaPlayer = MediaPlayer.create(this, R.raw.data0);
        mediaPlayer.start();
        return playing;
    }
    public boolean Stop() {
        boolean playing = mediaPlayer.isPlaying();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer = null;
        mediaPlayer = MediaPlayer.create(this, R.raw.data0);
        return playing;
    }
    public class MediaPlayerBinder extends Binder {
        MediaPlayerServ getService() {
            return MediaPlayerServ.this;
        }
    }
    public MediaPlayerServ() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.data0);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}*/
