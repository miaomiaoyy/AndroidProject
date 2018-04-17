package edu.northeastern.ashish.instagramneu;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    EditText editTextEMail;
    EditText editTextPassword;
    TextView textViewStatus;
    Button btnLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        editTextEMail = (EditText) findViewById(R.id.editTextEMail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        // Get the Instance of Firebase Authentication for your app
        mAuth = FirebaseAuth.getInstance();
        // Initialize the Auth State Listener
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null)
                    startActivity(new Intent(MainActivity.this, LoggedIn.class));

            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    public void Login(View view) {

        String email = editTextEMail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            textViewStatus.setText("Email or password is empty");
            Toast.makeText(this, "EMail or Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // EMail password is not null so try to login with the email and password
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(! task.isSuccessful()){

                    Toast.makeText(MainActivity.this, "Unable to login", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
