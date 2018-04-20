package com.iteso.handdoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ActivityMedical extends AppCompatActivity {

    Boolean medicalTest;
    EditText chronic, medicament, age, from;
    Button send;
    RadioButton yesDisease, noDisease, yesChronic, noChronic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);
        send = findViewById(R.id.activity_medical_btn_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
