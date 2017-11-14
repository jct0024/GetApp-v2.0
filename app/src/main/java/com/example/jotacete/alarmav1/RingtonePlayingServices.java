package com.example.jotacete.alarmav1;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by JotaCeTe on 12/11/2017.
 */

public class RingtonePlayingServices extends Service {
    boolean isRunning;
    MediaPlayer media_song;
    int startId;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        String state = intent.getStringExtra("a");
        Log.e("whats you state?",state.toString());
        //NOTIFiCATION

        //Cambio de estado
        assert state !=null;
        switch (state){
            case "on":
                startId=1;
                break;
            case"off":
                startId=0;
                break;
            default:
                startId=0;
                break;
        }


        if (!this.isRunning && startId==1){
            Log.e("there is nomusic","and you want to on");
            media_song = MediaPlayer.create(this,R.raw.dove);
            media_song.start();

            this.isRunning=true;
            this.startId = 1;
        }else if (this.isRunning && startId ==0){

            Log.e("there is music","and you want to stop");
            //stop the ringstone
            media_song.stop();
            this.isRunning=false;
            this.startId=0;

        }else if (!this.isRunning && startId == 0) {
            Log.e("there is nomusic","and you want to stop");

            this.isRunning= false;
            this.startId=0;
        } else if (this.isRunning && startId ==1){
            Log.e("there is music","and you want to on");

            this.isRunning=true;
            this.startId=1;
        }else {
            Log.e("otherthings", "you tri it");

        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("on destroy called","ta daa");

        super.onDestroy();
        this.isRunning = false;
    }
}
