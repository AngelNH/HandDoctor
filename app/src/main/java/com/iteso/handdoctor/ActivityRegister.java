package com.iteso.handdoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iteso.handdoctor.beans.Doctor;
import com.iteso.handdoctor.beans.Paciente;

public class ActivityRegister extends AppCompatActivity {
    TextInputEditText name,lastname,email,phone,password1,password2;
    RadioButton radPaciente, radDoctor,radSecretary;
    DatabaseReference databaseReference;
    int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.activity_register_name);
        lastname = findViewById(R.id.activity_register_last_name);
        phone = findViewById(R.id.activity_register_phone);
        email = findViewById(R.id.activity_register_email);

        password1 = findViewById(R.id.activity_register_pass1);
        password2 = findViewById(R.id.activity_register_pass2);



        radPaciente = findViewById(R.id.rbtnUser);
        radDoctor = findViewById(R.id.rbtnDoctor);
        radSecretary = findViewById(R.id.rbtnSecretary);
    }

    public void signInBtn(View view){
        String pass1, pass2;
        pass1 = password1.getText().toString();
        pass2 = password2.getText().toString();

        if (pass1.equals(pass2)){

            databaseReference = FirebaseDatabase.getInstance().getReference();
            boolean any = false;

            String id= "";
            Paciente paciente = new Paciente(name.getText().toString()+ " " +lastname.getText().toString(),email.getText().toString(),phone.getText().toString());
            if(radPaciente.isChecked()){
                any = true;
                //id = databaseReference.child("Paciente").push().getKey();//here is the key for these pacient.
                //paciente.setId(id);
                paciente.setEstado(Paciente.PACIENTE);
                //now we add the pacient to firebase
                databaseReference.child("Paciente").child(paciente.getPhone()).setValue(paciente);
                databaseReference.child("User").child(paciente.getPhone()).child("user").setValue(paciente.getEmail());
                databaseReference.child("User").child(paciente.getPhone()).child("password").setValue(pass1);
                databaseReference.child("User").child(paciente.getPhone()).child("id").setValue(paciente.getPhone());
                databaseReference.child("User").child(paciente.getPhone()).child("state").setValue(paciente.getEstado());
                id = paciente.getPhone();
                state = paciente.getEstado();
            }else if(radSecretary.isChecked()){
                any = true;
                //id = databaseReference.child("Doctor").push().getKey();//here is the key for these pacient.
                Doctor doctor = new Doctor(name.getText().toString()+ " " +lastname.getText().toString(),email.getText().toString(),phone.getText().toString(),Paciente.SECRETARIA," "," "," ");

                //now add the secretary to firebase
                databaseReference.child("Doctor").child(doctor.getPhone()).setValue(doctor);
                databaseReference.child("User").child(doctor.getPhone()).child("user").setValue(doctor.getEmail());
                databaseReference.child("User").child(doctor.getPhone()).child("password").setValue(pass1);
                databaseReference.child("User").child(doctor.getPhone()).child("id").setValue(doctor.getPhone());
                databaseReference.child("User").child(doctor.getPhone()).child("state").setValue(doctor.getEstado());
                id = doctor.getPhone();
                state = doctor.getEstado();

            }else if(radDoctor.isChecked()){
                any = true;
                //id = databaseReference.child("Doctor").push().getKey();//here is the key for these pacient.
                Doctor doctor = new Doctor(name.getText().toString()+ " " +lastname.getText().toString(),email.getText().toString(),phone.getText().toString(),Paciente.DOCTOR," "," "," ");

                //now add the secretary to firebase
                databaseReference.child("Doctor").child(doctor.getPhone()).setValue(doctor);
                databaseReference.child("User").child(doctor.getPhone()).child("user").setValue(doctor.getEmail());
                databaseReference.child("User").child(doctor.getPhone()).child("password").setValue(pass1);
                databaseReference.child("User").child(doctor.getPhone()).child("id").setValue(doctor.getPhone());
                databaseReference.child("User").child(doctor.getPhone()).child("state").setValue(doctor.getEstado());
                id = doctor.getPhone();
                state = doctor.getEstado();
            }

            if (any) {
                //TODO save the login, in phone.
                SharedPreferences sharedPreferences = getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("NAME",email.getText().toString());
                editor.putString("PWD",password1.getText().toString());
                editor.putBoolean("LOGGED",true);
                editor.putString("ID",id);
                editor.apply();
                //check the state of the person.
                if (state == Paciente.DOCTOR || state == Paciente.SECRETARIA) {
                    Intent intent = new Intent(ActivityRegister.this, ActivityMain.class);
                    startActivity(intent);
                    finish();
                }else if(state == Paciente.PACIENTE){
                    //TODO mandar a la pantalla principal del paciente.
                }
            }else{
                Toast.makeText(ActivityRegister.this,"Falta Seleccionar una opción",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(ActivityRegister.this,"Error: las contraseñas no coinciden",Toast.LENGTH_LONG).show();
        }



    }
}
