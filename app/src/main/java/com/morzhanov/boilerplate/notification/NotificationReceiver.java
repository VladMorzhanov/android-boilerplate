package com.morzhanov.boilerplate.notification;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.morzhanov.boilerplate.notification.NotificationManager.SAMPLE_ACTION;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import com.morzhanov.boilerplate.App;
import com.morzhanov.boilerplate.ui.main.MainActivity;
import java.util.Calendar;
import javax.inject.Inject;

public class NotificationReceiver extends BroadcastReceiver {

    //// TODO: 10/30/17 somehow inject app and repo here

    @Inject
    App mApp;

    @Override
    public void onReceive(final Context context, Intent intent) {
        switch (intent.getAction()) {
            case SAMPLE_ACTION:
                break;
        }
    }

    public void sendNotification(String title, String desc, PendingIntent pi) {
        Notification notification = new NotificationCompat.Builder(mApp)
                //// TODO: 10/10/17 uncomment
//                .setTicker(mInstance.getString(R.string.notification_ticker))
//                .setSmallIcon(R.drawable.icon)
//                .setContentTitle(mInstance.getString(R.string.notification_title))
//                .setContentText(mInstance.getString(R.string.notification_content))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        android.app.NotificationManager notificationManager = (android.app.NotificationManager) mApp
                .getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    private void sendSampleNotification() {
        // send
        String title = "get title";
        String desc = "get desc";
        PendingIntent pi = PendingIntent.getActivity(mApp, 0, new Intent(mApp, MainActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT);
        sendNotification(title, desc, pi);
    }
}
