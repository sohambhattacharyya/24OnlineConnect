package com.daceapps.netconnect;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class NotificationService extends Service {
    private NotificationManager mNM;
    private int NOTIFICATION = R.string.notify_service_started;

    public class LocalBinder extends Binder {
        NotificationService getService() {
            return NotificationService.this;
        }
    }

    public void onCreate() {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        showNotification();
        Toast.makeText(this, "tying shoelace..", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "ace net is up and running.", Toast.LENGTH_SHORT).show();
        Log.i("NotificationService", "Received start id" + startId + ": " + intent);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        startService(new Intent(this, MainActivity.class));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new LocalBinder();

    private void showNotification() {

        CharSequence text = getText(R.string.notify_service_started);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
      //        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setTicker(null)
      //          .setWhen(System.currentTimeMillis())
                .setContentTitle(getText(R.string.notify_service_label))
                .setContentText(text)
                .setContentIntent(contentIntent)
                .setColor(Color.argb(100, 255, 110, 64))
                .setLights(Color.argb(100, 255, 110, 64), 100, 3000)
                .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.salacia))
                .setOnlyAlertOnce(true)
                .build();

        mNM.notify(NOTIFICATION, notification);

    }

}

