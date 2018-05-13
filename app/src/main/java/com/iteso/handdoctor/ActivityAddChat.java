package com.iteso.handdoctor;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Doctor;
import com.iteso.handdoctor.beans.Paciente;

import java.util.ArrayList;

public class ActivityAddChat extends AppCompatActivity {
    EditText input;
    TextView nombre,especialidad,telefono;
    Button addD;
    ImageButton search;
    LinearLayout especial;

    DatabaseReference databaseReference;
    ArrayList<Doctor> doctors = new ArrayList<>();
    ArrayList<Paciente> pacientes = new ArrayList<>();

    boolean foundedDoctor,foundedPac;
    Paciente subjectP;
    Doctor subjectD;
    String gen_id,type;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);

        input = findViewById(R.id.activity_add_chat_input);
        nombre = findViewById(R.id.activity_add_chat_name);
        especialidad = findViewById(R.id.activity_add_chat_especial);
        telefono = findViewById(R.id.activity_add_chat_phone);
        addD = findViewById(R.id.activity_add_chat_add);
        search = findViewById(R.id.activity_add_chat_search);
        especial = findViewById(R.id.activity_add_chat_lin);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        loadID();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.child("Doctor").getChildren()){
                    Doctor d = data.getValue(Doctor.class);
                    doctors.add(d);
                }
                for(DataSnapshot data : dataSnapshot.child("Paciente").getChildren()){
                    Paciente p = data.getValue(Paciente.class);
                    pacientes.add(p);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foundedDoctor =false;
                foundedPac = false;
                String in = input.getText().toString();
                for(Doctor d:doctors){
                    if (d.getPhone().equals(in)){
                        foundedDoctor = true;
                        subjectD = d;
                        Toast.makeText(ActivityAddChat.this,"Encontrado en Doctores",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                for(Paciente p:pacientes){
                    if (p.getPhone().equals(in)){
                        foundedPac = true;
                        subjectP = p;
                        Toast.makeText(ActivityAddChat.this,"Encontrado en Pacientes",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if (foundedDoctor){
                    nombre.setText(subjectD.getName());
                    telefono.setText(subjectD.getPhone());
                    especialidad.setText(subjectD.getEspecialidad());
                    addD.setVisibility(View.VISIBLE);
                    especial.setVisibility(View.VISIBLE);
                }else if (foundedPac){
                    nombre.setText(subjectP.getName());
                    telefono.setText(subjectP.getPhone());
                    addD.setVisibility(View.VISIBLE);
                    especial.setVisibility(View.GONE);
                }else {
                    Toast.makeText(ActivityAddChat.this,"No se encontraron resultados",Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    telefono.setText("");
                    especialidad.setText("");
                    addD.setVisibility(View.INVISIBLE);
                    especial.setVisibility(View.GONE);
                }
            }
        });

        addD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOnFirebase();
            }
        });
    }
    public void addOnFirebase(){
        Log.e("ADD_CHAT","Type: "+type);
        if (type.equals(""+Paciente.DOCTOR)){
            databaseReference.child("Doctor").child(gen_id).child("Contactos").child(telefono.getText().toString()).child("name").setValue(nombre.getText().toString());
            databaseReference.child("Doctor").child(gen_id).child("Contactos").child(telefono.getText().toString()).child("chat").setValue("555");
            databaseReference.child("Doctor").child(gen_id).child("Contactos").child(telefono.getText().toString()).child("phone").setValue(telefono.getText().toString());
            //don´t know if we add the contact also for the receiver

        }else if (type.equals(""+Paciente.PACIENTE)) {
            databaseReference.child("Paciente").child(gen_id).child("Contactos").child(telefono.getText().toString()).child("name").setValue(nombre.getText().toString());
            databaseReference.child("Paciente").child(gen_id).child("Contactos").child(telefono.getText().toString()).child("chat").setValue("555");
            databaseReference.child("Paciente").child(gen_id).child("Contactos").child(telefono.getText().toString()).child("phone").setValue(telefono.getText().toString());
        }
    }

    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        gen_id = sharedPreferences.getString("ID","555");
        type = sharedPreferences.getString("TYPE","555");
        sharedPreferences = null;
    }
}
