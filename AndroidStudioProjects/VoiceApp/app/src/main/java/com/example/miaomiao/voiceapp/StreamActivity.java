package com.example.miaomiao.voiceapp;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StreamActivity extends AppCompatActivity {
    //make sure concurrent sychronized
    private volatile boolean isRecording;

    @BindView(R.id.streamBtn)
    Button streamBtn;
    @BindView(R.id.mTvLog)
    TextView mTvLog;

    private ExecutorService executorService;

    private MediaRecorder mediaRecorder;
    private AudioRecord audioRecord;
    private Handler mainHandler;
    private File audioFile;
    private FileOutputStream outputStrem;
    private byte[] buffer;
    private long startRecorderTime, endRecorderTime;
    private final int BUFFER_SIZE = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        ButterKnife.bind(this);
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdownNow();
    }

    @OnClick(R.id.streamBtn)
    public void start(){
        if(!isRecording) {
            streamBtn.setText(R.string.end);
            isRecording = true;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    if(!startRecording()) {
                        recordFail();
                    }
                }
            });
        } else {
            streamBtn.setText(R.string.start);
            isRecording = false;

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    if(!startRecording()) {
                        recordFail();
                    }
                }
            });
        }
    }

    private boolean startRecording() {
        //create file

        try {
            //create file
            mediaRecorder = new MediaRecorder();
            audioFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/iMoocDemo/" + System.currentTimeMillis() + ".pcm");
            audioFile.getParentFile().mkdir();
            audioFile.createNewFile();

            //create output stream
            outputStrem = new FileOutputStream(audioFile);


            //audiorecord setting
            int autoSource = MediaRecorder.AudioSource.MIC;
            int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
            int sampleRate = 44100;
            //单声道
            int channel = AudioFormat.CHANNEL_IN_MONO;
            //calculate the mini size of buffer
            int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, channel, audioFormat);
            audioRecord = new AudioRecord(autoSource, sampleRate, channel, audioFormat,
                    Math.max(minBufferSize, BUFFER_SIZE));
            //start record
            audioRecord.startRecording();
            //gettime Start
            startRecorderTime = System.currentTimeMillis();
            //recursivly read from the stream
            while(isRecording) {
                int read = audioRecord.read(buffer, 0, BUFFER_SIZE);
                if (read > 0) {
                    outputStrem.write(buffer, 0, read);
                } else {
                    return false;
                }
            }
            return stopRecord();
            //top and release
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            //release
            releaseRecord();
        }

    }

    private void releaseRecord() {
        if(audioRecord != null) {
            audioRecord.release();
            audioRecord=null;
        }
    }

    private boolean stopRecord() {
        try {
            audioRecord.stop();
            releaseRecord();
            outputStrem.close();
            endRecorderTime = System.currentTimeMillis();
            final int second = (int)(endRecorderTime - startRecorderTime);
            if(second > 3) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTvLog.setText(mTvLog.getText() + "record success " + second + "seconds");
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    private void recordFail() {
        audioFile = null;
        //toast notification, in main thread show to user

        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(StreamActivity.this, "recorder failed",
                        Toast.LENGTH_SHORT).show();

                //reset ecord status and UI status
                isRecording = false;
                streamBtn.setText(R.string.start);

            }
        });
    }
}
