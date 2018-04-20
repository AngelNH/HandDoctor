package com.iteso.handdoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityRegister extends AppCompatActivity {

    Button register;
    EditText name, lastName, expedient, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.activity_register_btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            //Logica para detectar si esta vacio el campo (falta)....
            @Override
            public void onClick(View v) {

                name = findViewById(R.id.activity_register_edt_name);
                lastName = findViewById(R.id.activity_register_edt_lastName);
                expedient = findViewById(R.id.activity_register_edt_expedient);
                phone = findViewById(R.id.activity_register_edt_phone);
                email = findViewById(R.id.activity_register_edt_mail);
                //Si los campos son diferentes que null, enviar, registrar y pasar al intento
                Intent intent = new Intent(ActivityRegister.this, ActivityPatient.class);
                startActivity(intent);
            }
        });
    }

    public void signInBtn(View view){
        Intent intent = new Intent(ActivityRegister.this,ActivityMain.class);
        startActivity(intent);
        finish();
    }
}
