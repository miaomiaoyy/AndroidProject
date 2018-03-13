package com.example.android.concurrency;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CodeRunner";
    private static final String MESSAGE_KEY = "message key";

    // View object references
    private ScrollView mScroll;
    private TextView mLog;
    private ProgressBar mProgressBar;

    private Handler mHandler;//intrepret message and take action from it, lifelong of the activity
    private ExecutorService mExecutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the logging components
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mExecutor = Executors.newFixedThreadPool(5);

        mLog.setText(R.string.lorem_ipsum);
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                //super.handleMessage(message);
                Bundle bundle = message.getData();
                String msg = bundle.getString(MESSAGE_KEY);
                log(msg);
                displayProgressBar(false);
            }
        };

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExecutor.shutdown();
    }

    //  Run some code, called from the onClick event in the layout file
    public void runCode(View v) {
        for(int i = 0; i < 10; i++) {
            Runnable worker = new BackgroundTask(i);
            mExecutor.execute(worker);
        }
        log("Running code");
        displayProgressBar(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "RUN:runnable complete");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "RUN: running thread");
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString(MESSAGE_KEY, "thread is complete");
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
        };

        displayProgressBar(false);
//        Handler handler = new Handler();
//        handler.postDelayed(runnable, 200);
        Thread thread = new Thread();
        thread.start();
        //thread.notify();

    }

    //  Clear the output, called from the onClick event in the layout file
    public void clearOutput(View v) {
        mLog.setText("");
        scrollTextToEnd();
    }

    //  Log output to logcat and the screen
    private void log(String message) {
        Log.i(TAG, message);
        mLog.append(message + "\n");
        scrollTextToEnd();
    }

    private void scrollTextToEnd() {
        mScroll.post(new Runnable() {
            @Override
            public void run() {
                mScroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @SuppressWarnings("unused")
    private void displayProgressBar(boolean display) {
        if (display) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}