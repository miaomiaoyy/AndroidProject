package com.example.miaomiao.ladyyangcake;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import lombok.ast.app.Main;

public class MainActivity extends AppCompatActivity {
    EditText editTextEmail;
    EditText editPassword;
    TextView textViewStatus;
    Button btnLogin;
    private FirebaseAuth mAuth;//authentication
    private FirebaseAuth.AuthStateListener mAuthListener;//authentication listener



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {//if the login is successful status changed
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {//it means there is a potential user
                if(mAuth.getCurrentUser() != null){
                    Intent intent = new Intent(MainActivity.this, Loggedin.class);
                    if(mAuth.getCurrentUser()!= null) {
                        intent = new Intent(MainActivity.this, AddEditProfile.class);
                    } else {
                        intent = new Intent(MainActivity.this, Loggedin.class);
                    }
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);//make mAuth to listen to lister
    }

    public void Login(View view) {
        String email = editTextEmail.getText().toString();
        String password = editPassword.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){//either field is empty
            textViewStatus.setText("Email/Password is empty");
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    textViewStatus.setText("Authentication Failed");
                    Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



//    public void SignIn(View view) {
//        String email = editTextEmail.getText().toString();
//        String password = editPassword.getText().toString();
//        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){//either field is empty
//            textViewStatus.setText("Email/Password is empty");
//            Toast.makeText(this,"Email/Password is empty",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        //when the two fields are complete, we will get a task back to further check
//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(!task.isSuccessful()) {
//                    textViewStatus.setText("Authentication Failed");
//                    Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}