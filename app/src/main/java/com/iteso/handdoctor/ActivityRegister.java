package com.iteso.handdoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void signInBtn(View view){
        Intent intent = new Intent(ActivityRegister.this,ActivityMain.class);
        startActivity(intent);
        finish();
    }
}