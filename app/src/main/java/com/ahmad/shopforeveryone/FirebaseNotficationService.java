package com.ahmad.shopforeveryone;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.ahmad.shopforeveryone.authenticator.DataBase.RealTimeDataBaseManager;
import com.ahmad.shopforeveryone.notfication.NotficationCustomClass;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FirebaseNotficationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if (message.getNotification() != null) {
            NotficationCustomClass notfi = new NotficationCustomClass(message.getNotification().getTitle(), message.getNotification().getBody());

            RealTimeDataBaseManager realTimeDataBaseManager = new RealTimeDataBaseManager();

            realTimeDataBaseManager.AddToNotfiList(notfi);
            ShowNotfi(message.getNotification().getTitle(), message.getNotification().getBody());
        }

    }

    @SuppressLint("MissingPermission")
    public void ShowNotfi(String title, String body) {
        Intent intent = new Intent();
        String channel_id = "notification_channel";
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.drawable.bell)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000,
                        1000, 1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent).setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.bell);


        NotificationManager notificationManager
                = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);

        NotificationChannel notificationChannel
                = new NotificationChannel(
                channel_id, "web_app",
                NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(
                notificationChannel);
        notificationManager.notify(0, builder.build());

    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }
}
