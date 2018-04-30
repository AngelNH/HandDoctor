package com.iteso.handdoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {

    EditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logInBtn(View view){
        //Si user y password es igual a ...
        Intent intent = new Intent(ActivityLogin.this,ActivityPatient.class);
        startActivity(intent);
        finish();
    }
    public void signInLbl(View view){
        Intent intent = new Intent(ActivityLogin.this,ActivityRegister.class);
        startActivity(intent);
        finish();
    }
}
