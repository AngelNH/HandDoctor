package com.iteso.handdoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logInBtn(View view){
        Intent intent = new Intent(ActivityLogin.this,ActivityPatient.class);
        startActivity(intent);
        finish();
    }
    public void signInLbl(View view){
        Intent intent = new Intent(ActivityLogin.this,ActivityRegister.class);
        startActivity(intent);
    }
}
