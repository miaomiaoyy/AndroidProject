package com.example.android.concurrency;

import android.util.Log;

/**
 * Created by miaomiao on 3/13/18.
 */

public class BackgroundTask implements Runnable {
    public static final String TAG = "CodeRunner";
    private int threadNum = 10;
    @Override
    public void run() {

        Log.i(TAG, Thread.currentThread().getName() + "start, thread numer" + threadNum);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
