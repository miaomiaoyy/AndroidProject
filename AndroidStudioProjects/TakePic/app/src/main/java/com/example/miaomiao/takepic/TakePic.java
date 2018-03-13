package com.example.miaomiao.takepic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TakePic extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 10;
    private static final int REQUEST_IMAGE_CAPTURE = 10;
    ImageView imageView;
    Button Takepic;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_pic);
        imageView = (ImageView)findViewById(R.id.imageView);
        Takepic = (Button)findViewById(R.id.Takepic);
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, CAMERA_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantedResults) {
        if(requestCode == CAMERA_REQUEST_CODE) {
            if(grantedResults.length > 0 && grantedResults[0] == PackageManager.PERMISSION_GRANTED  //0 for the camera
                    && grantedResults[1] == PackageManager.PERMISSION_GRANTED) //1 for external storage
            {
                Takepic.setEnabled(true);
            }
        }
    }


    public void Takepic(View view) {
        Intent takepicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takepicIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takepicIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extra = data.getExtras();
            Bitmap image = (Bitmap) extra.get("data");
            imageView.setImageBitmap(image);
        }
    }
}
