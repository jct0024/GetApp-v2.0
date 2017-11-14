package com.example.jotacete.alarmav1;


        import android.app.Activity;
        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.BroadcastReceiver;
        import android.content.ComponentName;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.media.MediaPlayer;
        import android.media.Ringtone;
        import android.media.RingtoneManager;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.PowerManager;
        import android.util.Log;
        import android.widget.Toast;


class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("MyActivity", "In the receiver with " );
        //create a intent to the ringstone service
        String get_your_string = intent.getExtras().getString("a");
        Intent service_intent = new Intent(context,RingtonePlayingServices.class);
        service_intent.putExtra("a",get_your_string);
        context.startService(service_intent);

    }

}