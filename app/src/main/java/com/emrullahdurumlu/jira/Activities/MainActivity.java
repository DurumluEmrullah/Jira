package com.emrullahdurumlu.jira.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.emrullahdurumlu.jira.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
    EditText etMail,etPassword;
    String email,password;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMail = findViewById(R.id.etMail);
        etPassword=findViewById(R.id.etPasword);
        auth=FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(MainActivity.this,FeedActivity.class);
            startActivity(intent);
            finish();
        }

    }


    public void login(View view) {

        email=etMail.getText().toString().trim();
        password=etPassword.getText().toString().trim();
        if(!(email.isEmpty())&& !(password.isEmpty()))
            signIn(email,password);
        else
            Toast.makeText(MainActivity.this,"Kullanıcı adı ve şifreyi girin",Toast.LENGTH_SHORT).show();
    }

    public void signIn(String email,String password){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this,"Kullanıcı adı yada şifre yanlış",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}