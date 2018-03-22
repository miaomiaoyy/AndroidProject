package com.example.miaomiao.voiceapp;

import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FileActivity extends AppCompatActivity {


    @BindView(R.id.pressToRecord)
    TextView pressToRecord;
    private ExecutorService executorService;

    private MediaRecorder mediaRecorder;
    private File audioFile;
    private long startRecorderTime, endRecorderTime;
    private Handler mainHandler;


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
        releaseRecorder();
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
        try {
            mediaRecorder.stop();
            //timer
            endRecorderTime = System.currentTimeMillis();
            final int second = (int) (endRecorderTime - startRecorderTime)/1000;
            if(second > 3) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        pressToRecord.setText(pressToRecord.getText() +
                                " record succeed" + second + "seconds");

                    }
                });

            }
            //UI show log, only accept > 3 sec
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }


    private boolean doStart() {
        //start recording
        try {
            //create file
            mediaRecorder = new MediaRecorder();
            audioFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/iMoocDemo/" + System.currentTimeMillis() + ".m4a");
            audioFile.getParentFile().mkdir();
            audioFile.createNewFile();
            //set autio source
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            //All Android sys support!
            mediaRecorder.setAudioSamplingRate(44100);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setAudioEncodingBitRate(96000);
            mediaRecorder.setOutputFile(audioFile.getAbsoluteFile());
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void recorderFail() {
        //error handling
        audioFile = null;
        //toast notification, in main thread show to user

        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(FileActivity.this, "recorder failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void releaseRecorder() {
        //release mediaRecorder
        if(mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }

    }

}

