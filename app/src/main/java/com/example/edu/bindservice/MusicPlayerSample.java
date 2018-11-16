package com.example.edu.bindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MusicPlayerSample extends AppCompatActivity implements View.OnClickListener {
    private BackgroundMusicWithBindService mServiceBinder;


    private ServiceConnection myConnection = new ServiceConnection() {//서버? 연결되었는지 확인//
        public void onServiceConnected(ComponentName className, IBinder binder) {
            mServiceBinder = ((BackgroundMusicWithBindService.MyBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            mServiceBinder = null;
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player_sample);
        Button play=findViewById(R.id.play);
        play.setOnClickListener(this);
        Button stop=findViewById(R.id.stop);
        stop.setOnClickListener(this);
        Intent intent = new Intent(this, BackgroundMusicWithBindService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                mServiceBinder.play();
                break;

            case R.id.stop:
                mServiceBinder.stop();
                break;
        }


    }
}