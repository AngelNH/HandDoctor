package com.iteso.handdoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AnalogClock;

public class ActivityAgenda extends AppCompatActivity {

    AnalogClock clock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__agenda);
        clock = findViewById(R.id.appointment_clock);

    }
}
