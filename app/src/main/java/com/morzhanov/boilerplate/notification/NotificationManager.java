package com.morzhanov.boilerplate.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.morzhanov.boilerplate.App;
import java.time.LocalTime;
import java.util.Calendar;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NotificationManager {

    static final String SAMPLE_ACTION = "SAMPLE_NOTIF";

    static final int REQUEST_CODE_SAMPLE = 0;

    @Inject
    App mApp;

    @Inject
    public NotificationManager() {
    }

    public void disableSampleNotification() {
        cancelAlarm(REQUEST_CODE_SAMPLE);
    }

    //// TODO: 11/3/17 create custom time parser class
    public void enableSampleNotification(LocalTime callTime) {
//        setAlarm(REQUEST_CODE_SAMPLE, SAMPLE_ACTION, callTime);
    }

    private void cancelAlarm(int code) {
        Intent intent = new Intent(mApp, NotificationReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(mApp, code, intent, 0);
        AlarmManager alarmManager = (AlarmManager) mApp.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    private void setAlarm(int code, String action, LocalTime callTime) {
        Intent intent = new Intent(mApp, NotificationReceiver.class);
        intent.setAction(action);

        //// TODO: 10/10/17 Do we actually need this ?
        boolean alarmUp = (PendingIntent.getBroadcast(mApp, 0, intent,
                PendingIntent.FLAG_NO_CREATE) != null);
        if (alarmUp) {
            cancelAlarm(code);
        }

        AlarmManager am = (AlarmManager) mApp.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(mApp, code, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar currentCal = Calendar.getInstance();
        Calendar firingCal = Calendar.getInstance();

        //// TODO: 11/3/17 create custom time parser class
//        firingCal.set(Calendar.HOUR, callTime.getHourOfDay());
//        firingCal.set(Calendar.MINUTE, callTime.getMinuteOfHour());
//        firingCal.set(Calendar.SECOND, callTime.getSecondOfMinute());

        long intendedTime = firingCal.getTimeInMillis();
        long currentTime = currentCal.getTimeInMillis();

        if (intendedTime >= currentTime) {
            am.setRepeating(AlarmManager.RTC, intendedTime, AlarmManager.INTERVAL_DAY, pi);
        } else {
            firingCal.add(Calendar.DAY_OF_MONTH, 1);
            intendedTime = firingCal.getTimeInMillis();
            am.setRepeating(AlarmManager.RTC, intendedTime, AlarmManager.INTERVAL_DAY, pi);
        }
    }
}
