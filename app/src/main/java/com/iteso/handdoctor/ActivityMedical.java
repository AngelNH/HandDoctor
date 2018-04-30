package com.iteso.handdoctor;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.zip.Inflater;

public class ActivityMedical extends AppCompatActivity {

    Boolean medicalTest;
    EditText chronic, medicament, age, from;
    Button send;
    RadioButton yesDisease, noDisease, yesMedicament, noMedicament, male, female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);
        send = findViewById(R.id.activity_medical_btn_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male = findViewById(R.id.activity_medical_rdb_male);
                female = findViewById(R.id.activity_medical_rdb_female);
                yesDisease = findViewById(R.id.activity_medical_rdb_yesDisease);
                noDisease = findViewById(R.id.activity_medical_rdb_noDisease);
                yesMedicament = findViewById(R.id.activity_medical_rdb_yesMedicament);
                noMedicament = findViewById(R.id.activity_medical_rdb_noMedicament);
                //Logica de campos vacios.
//                ...
//                ...
//                ...


                //Si o no de enfermedad cronica.
                chronic = findViewById(R.id.activity_medical_edt_chronic);

                //Si o no toma medicamento
                medicament = findViewById(R.id.activity_medical_edt_medicament);
                age = findViewById(R.id.activity_medical_edt_age);
                from = findViewById(R.id.activity_medical_edt_from);

//                Mandar a base de datos con id del paciente...
            }
        });
    }

    public void sendTest(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_medical, (ViewGroup) findViewById(R.id.activity_nav_medicalPreview));
        if (age.getText().toString() != "" || from.getText().toString() != "" || medicament.getText().toString() != "" || chronic.getText().toString() != "") {
            Context c = getApplication();
            String text = "Cuestionario completado...";
            int durToast = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(c, text, durToast);
            toast.show();
        } else {


            Toast toast = Toast.makeText(getApplication(), "Completa los campos...", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        }
    }
}
