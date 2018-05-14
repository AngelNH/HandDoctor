package com.iteso.handdoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.iteso.handdoctor.beans.Paciente;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {
    String name, password;
    boolean isLogged;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //timer task: que tarea se va a hacer en cierto tiempo
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                loadUser();
                Log.e("ACTIVITY_SPLASH","Type: "+type);
                Intent intent;
                if (isLogged){
                    if (type.equals(""+Paciente.DOCTOR))
                        intent  = new Intent(ActivitySplash.this,ActivityDoctor.class);
                    else if(type.equals(""+Paciente.PACIENTE))
                        intent= new Intent(ActivitySplash.this,ActivityPatient.class);
                    else
                        intent = new Intent(ActivitySplash.this,ActivityLogin.class);
                }else{
                    intent = new Intent(ActivitySplash.this,ActivityLogin.class);
                }
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,2000);//se define el tiempo.

    }

    public void loadUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        name = sharedPreferences.getString("NAME",null);
        password = sharedPreferences.getString("PWD",null);
        isLogged = sharedPreferences.getBoolean("LOGGED",false);
        type = sharedPreferences.getString("TYPE","null");
        sharedPreferences = null;
    }
}
