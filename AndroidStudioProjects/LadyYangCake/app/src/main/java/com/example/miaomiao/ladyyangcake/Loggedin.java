package com.example.miaomiao.ladyyangcake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.widget.EditText;


public class Loggedin extends AppCompatActivity {
    TextView textViewLWelcome ;
    FirebaseAuth mAuth;
    DatabaseReference ref;
    FirebaseDatabase database;

    int counter;
    EditText editTextData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);
        textViewLWelcome = (TextView)findViewById(R.id.loginText);
        mAuth = FirebaseAuth.getInstance();
        textViewLWelcome.setText("Welcome" + mAuth.getCurrentUser().getDisplayName());
        database = FirebaseDatabase.getInstance();
        //ref = database.getReference("server/saving-data/fireblog");
        counter = 1;
    }

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Loggedin.this, MainActivity.class));
    }

    public void AddToDB(View view) {
        ref = database.getReference("Users/User" + counter);
        System.out.print(editTextData);
        ref.setValue(editTextData.getText().toString());
        counter++;
    }
}

//public class Loggedin extends AppCompatActivity {
//    TextView loginText;
//    FirebaseAuth mAuth;
//    DatabaseReference ref;
//    FirebaseDatabase database;
//    int counter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_loggedin);
//        loginText = (TextView)findViewById(R.id.loginText);
//        loginText.setText("Welcome" + mAuth.getCurrentUser().getEmail());
//
//        database = FirebaseDatabase.getInstance();//singleton
//        ref = database.getReference("Users/User1");
//        counter = 1;
//
//    }
//    public void Logout(View view) {
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(Loggedin.this, MainActivity.class));
//
//    }
//
//    public void AddToDB(View view) {
//        ref = database.getReference("Users/User" + counter);
//        ref.setValue(editTextData.getText.toString());
//        counter++;
//    }
//}
