package br.com.cesar.emailprocessorservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import br.com.cesar.emailprocessorservice.util.Utils;

public class EmailService extends Service {

    private final List<MyThread> threads = new ArrayList<>();

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        MyThread thread = new MyThread(intent, startId);
        threads.add(thread);

        thread.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for(MyThread thread: threads) {
            thread.interrupt();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyThread extends Thread {
        private final Intent intent;
        private final int startId;

        MyThread(Intent intent, int startId) {
            this.intent = intent;
            this.startId = startId;
        }

        @Override
        public void run() {
            super.run();

            try {
                ArrayList<String> emails = intent.getStringArrayListExtra("EMAILS");
                Utils.removeDuplicates(emails);
                intent.putStringArrayListExtra("EMAILS", emails);
            } catch (Exception ignored) { }
            stopSelf(startId);
        }
    }

}
