package com.iteso.handdoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.iteso.handdoctor.beans.Paciente;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityRegister extends AppCompatActivity {

    Button register;
    EditText name, lastName, phone, email;
    RadioButton pacient, doctor, secretary;
    int state = 2;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.activity_register_btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = findViewById(R.id.activity_register_edt_name);
                lastName = findViewById(R.id.activity_register_edt_lastName);
                phone = findViewById(R.id.activity_register_edt_phone);
                email = findViewById(R.id.activity_register_edt_mail);
                pacient = findViewById(R.id.activity_register_rbtnUser);
                doctor = findViewById(R.id.activity_register_rbtnDoctor);
                secretary = findViewById(R.id.activity_register_rbtnSecretary);

                switch (v.getId())
                {
                    case R.id.activity_register_rbtnUser:
                        state = 2;
                        break;
                    case R.id.activity_register_rbtnDoctor:
                        state = 0;
                        break;
                    case R.id.activity_register_rbtnSecretary:
                        state = 1;
                        break;
                }

                Paciente pacient = new Paciente(name.getText().toString(),
                                                lastName.getText().toString(),
                                                phone.getText().toString(),
                                                email.getText().toString(),state);
                if((name.getText().toString() != "") || lastName.getText().toString() != "" || phone.getText().toString() != "" || email.getText().toString() != "") {
//Registrar y pasar al intento
                    String pacientId = databaseReference.child("Paciente").push().getKey();
                    databaseReference.child("Paciente").child(pacientId).setValue(pacient);
                    Intent intent = new Intent(ActivityRegister.this, ActivityPatient.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    if(name.getText() == null)
                    {}
                    if(lastName.getText() == null)
                    {}
                    if(phone.getText() == null)
                    {}
                    if(email.getText() == null)
                    {}
                }
            }
        });
    }

    public void signInBtn(View view){
        Intent intent = new Intent(ActivityRegister.this,ActivityMain.class);
        startActivity(intent);
        finish();
    }
}
