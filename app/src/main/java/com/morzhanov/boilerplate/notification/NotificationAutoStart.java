package com.morzhanov.boilerplate.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import javax.inject.Inject;

public class NotificationAutoStart extends BroadcastReceiver {

    @Inject
    NotificationManager mNotificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            // set notification
        }
    }
}
