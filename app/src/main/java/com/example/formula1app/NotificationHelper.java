package com.example.formula1app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {
    public static Notification getNotification(Context context, String content) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(context, NotificationChannelManager.CHANNEL_ID)
                .setContentTitle("Formula 1 Értesítés")
                .setContentText(content)
                .setSmallIcon(R.drawable.f1_pic)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
    }
}

