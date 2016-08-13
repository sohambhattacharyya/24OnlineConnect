package com.daceapps.netconnect;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class secondService extends IntentService{
    public secondService() {
        super("Net Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service is created", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service is started", Toast.LENGTH_LONG).show();

        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    protected void onHandleIntent(Intent arg0) {
        Log.d("Service test", "from the onHandleIntent method");
    }
}
