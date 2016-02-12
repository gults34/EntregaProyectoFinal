package com.example.andres.proyectofinal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;


public class MusicaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button botonPlay,botonStop,botonMute,botonReplay;
    private ServiceMediaplayer sMediaPlayer = new ServiceMediaplayer();
    private boolean bound;
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ServiceMediaplayer.MediaPlayerBinder binder = (ServiceMediaplayer.MediaPlayerBinder) service;

            sMediaPlayer = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Media Player");

        botonReplay = (Button) findViewById(R.id.buttonReplay);
        botonMute = (Button) findViewById(R.id.buttonMute);
        botonPlay = (Button) findViewById(R.id.buttonPlay);
        botonStop = (Button) findViewById(R.id.buttonStop);

        botonReplay.setOnClickListener(this);
        botonMute.setOnClickListener(this);
        botonPlay.setOnClickListener(this);
        botonStop.setOnClickListener(this);

        botonReplay.setBackground(getDrawable(R.drawable.replay));
        botonPlay.setBackground(getDrawable(R.drawable.play));
        botonStop.setBackground(getDrawable(R.drawable.stop));
        botonMute.setBackground(getDrawable(R.drawable.mute));
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPlay:
                if (!sMediaPlayer.playSong()){
                    botonPlay.setBackground(getDrawable(R.drawable.play));
                } else{
                    botonPlay.setBackground(getDrawable(R.drawable.pause));
                }
                break;
            case R.id.buttonStop:
                sMediaPlayer.Stop();
                botonPlay.setBackground(getDrawable(R.drawable.play));
                break;
            case R.id.buttonMute:
                if (sMediaPlayer.setVol()){
                    botonMute.setBackground(getDrawable(R.drawable.mute));
                }
                else{
                    botonMute.setBackground(getDrawable(R.drawable.unmute));
                }
                break;
            case R.id.buttonReplay:
                sMediaPlayer.replaySong();
                botonPlay.setBackground(getDrawable(R.drawable.pause));
                break;

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent inten = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(inten);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MusicaActivity.this, ServiceMediaplayer.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        sMediaPlayer.salir();
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
    }
}
