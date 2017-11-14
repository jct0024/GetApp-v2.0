package com.example.jotacete.alarmav1;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //to make our alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;
    //Create new Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.context = this;
        //Inizialize alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //initialize aou timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);
        //Inizialize our text update Box
        update_text = (TextView) findViewById(R.id.update_text);

        //create and instance of a calendar
        final Calendar calendar = Calendar.getInstance();
        //Initialize buttonr

        final Intent myIntent = new Intent(this.context,Alarm_Receiver.class);

        /**
         * Parte de codigo que ocurre al activar el boton encender
         *
         *
         *
         *
         */

        Button alarm_on = (Button) findViewById(R.id.alarm_on);
        //CreateonClick listener to start alarm
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE,alarm_timepicker.getMinute());
                //Get the int values the update text textbox
                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();

                //Convert the intvalues to String
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);
                myIntent.putExtra("a","on");
                //untill the calendar mark
                pending_intent = PendingIntent.getBroadcast(MainActivity.this,0,
                        myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                //change the alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent);

                //convert 24 time to 12 time
                if (hour > 12){
                    hour_string = String.valueOf(hour-12);
                }

                if (minute < 10) {
                    minute_string ="0" + String.valueOf(minute);
                }

                //Mthod that changes the update text TextBox
                set_alarm_text("Alarma set to: "+hour_string+":"+minute_string);
                //extra inten for alarm_on button

            }


        });



        /**
         * Parte de codigo que inicializa el boton de apagado y hacesu funcion
         *
         *
         */
        //Initialize the stop button
        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mthod that changes the update text TextBox
                set_alarm_text("Alarma_off");
                //Cancel the alarm
                alarm_manager.cancel(pending_intent);
                //put a extra intent for Aalarm_off button
                myIntent.putExtra("a","off");
                //Cancel the ringtone
                sendBroadcast(myIntent);

            }
        });

    }

    private void set_alarm_text(String s) {
        update_text.setText(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
