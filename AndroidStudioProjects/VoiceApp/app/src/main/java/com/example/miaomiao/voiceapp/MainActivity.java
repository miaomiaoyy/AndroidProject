package com.example.miaomiao.voiceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    EditText editTextEMail;
    EditText editTextPassword;
    TextView textViewStatus;
    Button btnLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.fileBtn)
    public void fileMode() {

    }

    @OnClick(R.id.StreamBtn)
    public void streamMode() {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    public void Login(View view) {

        String email = editTextEMail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            textViewStatus.setText("Email or password is empty");
            Toast.makeText(this, "EMail or Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }


    }
}

