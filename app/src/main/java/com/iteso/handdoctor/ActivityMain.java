package com.iteso.handdoctor;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menu_activity_main_settings){
            //TODO show the settings activity.
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            //now to prove it.

            SharedPreferences sharedPreferences = getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }
        return true;
    }

    void agendaBtn(View v){
        Intent intent = new Intent(ActivityMain.this,ActivityAgenda.class);
        //Intent intent = new Intent(ActivityMain.this,ActivityMedical.class);

        startActivity(intent);
    }
    void expedientBtn(View v){
        Intent intent = new Intent(ActivityMain.this,ActivityExpedient.class);
        startActivity(intent);
    }
    void citasBtn(View v){
        //Intent intent = new Intent(ActivityMain.this,ActivityMedical.class);
        Intent intent = new Intent(ActivityMain.this,ActivityCitas.class);
        //Intent intent = new Intent(ActivityMain.this,ActivityMedicamento.class);

        startActivity(intent);
    }
    void recetarBtn(View v){
        Intent intent = new Intent(ActivityMain.this,ActivityRecetar.class);
        startActivity(intent);
    }
    void addPacienteBtn(View v){
        Intent intent = new Intent(ActivityMain.this,ActivityAdd.class);
        startActivity(intent);
    }
}
