package com.example.threadingapp;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button buttonStartThread;

    private volatile boolean stopThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartThread = findViewById(R.id.btnStart);
    }

    public void startThread(View view) {
        /*ExampleThread thread = new ExampleThread(20);
        thread.start();*/

        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable(20);
        new Thread(runnable).start();

     /* new Thread(new Runnable() {
          @Override
          public void run() {

          }
      }).start();*/

    }

    public void stopThread(View view) {
        stopThread = true;
    }

    class ExampleThread extends Thread {
        int seconds;

        public ExampleThread(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < seconds; i++) {

                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ExampleRunnable implements Runnable {

        int seconds;

        public ExampleRunnable(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < seconds; i++) {

                if(stopThread) {
                    return;
                }
                if (i == 5) {
                   /* Handler mainHandler = new Handler(Looper.getMainLooper());

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("50%");
                        }
                    });*/

                   /*buttonStartThread.post(new Runnable() {
                       @Override
                       public void run() {
                            buttonStartThread.setText("50%");
                       }
                   });*/

                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           buttonStartThread.setText("50%");
                       }
                   });
                }

                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
