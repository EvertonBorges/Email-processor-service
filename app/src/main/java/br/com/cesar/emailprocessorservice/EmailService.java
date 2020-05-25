package br.com.cesar.emailprocessorservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.cesar.emailprocessorservice.util.Utils;

public class EmailService extends Service {

    public EmailService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ArrayList<String> emails = intent.getStringArrayListExtra("EMAILS");
        Utils.removeDuplicates(emails);
        intent.putStringArrayListExtra("EMAILS", emails);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

}
