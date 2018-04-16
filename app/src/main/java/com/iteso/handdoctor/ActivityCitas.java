package com.iteso.handdoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityCitas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
    }
    public void agendarBtn(View view){
        Intent intent = new Intent(ActivityCitas.this,ActivityMain.class);
        startActivity(intent);
    }
}
