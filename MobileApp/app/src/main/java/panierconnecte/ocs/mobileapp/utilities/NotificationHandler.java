package panierconnecte.ocs.mobileapp.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import panierconnecte.ocs.mobileapp.MainActivity;
import panierconnecte.ocs.mobileapp.R;


public class NotificationHandler extends FirebaseMessagingService {

    public static int NOTIFICATION_ID = 1;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //Call method to generate notification
        generateNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
    }

    private void generateNotification(String messageBody, String title) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelId = "default";
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel channel = new NotificationChannel(channelId, "blabl", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(messageBody);
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            channel.setShowBadge(true);
            notificationManager.createNotificationChannel(channel);

            Notification notification = new Notification.Builder(this, channelId)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .build();
            notificationManager.notify(NOTIFICATION_ID++, notification);


        } else {


            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this, "notif")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Firebase Cloud or Push Notification")
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (NOTIFICATION_ID > 1073741824) {
                NOTIFICATION_ID = 0;
            }
            notificationManager.notify(NOTIFICATION_ID++, mNotifyBuilder.build());

        }
    }
}