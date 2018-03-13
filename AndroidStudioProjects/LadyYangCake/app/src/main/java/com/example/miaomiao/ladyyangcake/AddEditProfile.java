package com.example.miaomiao.ladyyangcake;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.android.gms.tasks.Task;


public class AddEditProfile extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_profile);
        editText = (EditText) findViewById(R.id.editText);
    }


    public void UpdateName(View view){
        if(TextUtils.isEmpty(editText.getText().toString())){
            Toast.makeText(this, "Enter a Display Name", Toast.LENGTH_SHORT).show();
            return;
        }

        //Update the name
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(editText.getText().toString()).build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "User profile updated.");
                            Intent intent = new Intent(AddEditProfile.this, Loggedin.class);
                            startActivity(intent);
                        }
                        else{

                            Toast.makeText(AddEditProfile.this, "Unable to Update Profile", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

//public class AddEditProfile extends AppCompatActivity {
//    EditText editText;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_edit_profile);
//        editText = (EditText)findViewById(R.id.editText);
//    }
//
//    public void updateName(View view) {
//        if(TextUtils.isEmpty(editText.getText().toString())) {
//            Toast.makeText(this, "Enter a Display Name", Toast.LENGTH_SHORT).show();
//            return;
//        }
//    }
//    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest().Builder()
//            .setDisplayName(editText.getText().toString()).build();
//
//    user.updateProfile(profileUpdates)
//            .addOnCompleteLister(new OnCompleteListener<Void>) {
//        @Override
//        public void onComplete(@NonNull Task<Void> task) {
//            if (!task.isSuccessful()) {
//                textViewStatus.setText("Authentication Failed");
//                Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
//            } else {
//                Intent intent = new Intent(AddEditProfile.this, Loggedin.class);
//                startActivity(intent);
//            }
//        }
//    });
//}
//}
