package com.example.miaomiao.voiceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileActivity extends AppCompatActivity {


    @BindView(R.id.pressToRecord)
    TextView pressToRecord;
    private ExecutorService executorService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        ButterKnife.bind(this);
        executorService = Executors.newSingleThreadExecutor();
        pressToRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //handle diff touch situations
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        startRecord();
                        break;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        stopRecord();
                        break;
                    case MotionEvent.ACTION_DOWN:


                }
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stop to avoid neicun xielou
        executorService.shutdownNow();
    }

    private void startRecord() {
        //recordBtn.setText(R.string.speaking);
        pressToRecord.setText(R.string.speaking);
        //logic
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //RELEASE previous recorder
                releaseRecorder();

                //execuate recording, if fail reminder user
                if(!doStart()) {
                    recorderFail();
                }

            }
        });
    }



    private void stopRecord() {
        pressToRecord.setText(R.string.presstosay);
        //logic
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //stop recording, if fail reminder user

                if (!doStop()) {
                    recorderFail();
                }

                releaseRecorder();
            }

        });
    }

    private boolean doStop() {
        //stop
        return true;
    }


    private boolean doStart() {
        //start recording
        return true;
    }

    private void recorderFail() {
        //error handling

    }

    private void releaseRecorder() {
        //release mediaRecorder


    }

}

