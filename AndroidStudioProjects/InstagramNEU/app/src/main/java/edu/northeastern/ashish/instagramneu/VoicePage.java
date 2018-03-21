package edu.northeastern.ashish.instagramneu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.ButterKnife;

public class VoicePage extends AppCompatActivity {
    TextView recordBtn;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_voice_page);
        ButterKnife.bind(this);
        //录音JNI不具备线程安全性
        executorService = Executors.newSingleThreadExecutor();

        recordBtn.setOnTouchListener(new View.OnTouchListener() {
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
        executorService.shutdownNow();
    }

    private void startRecord() {
        //recordBtn.setText(R.string.speaking);
    }

    private void stopRecord() {

    }
}
