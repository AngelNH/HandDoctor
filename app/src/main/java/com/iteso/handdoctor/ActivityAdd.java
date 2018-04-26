package com.iteso.handdoctor;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Paciente;

import java.util.ArrayList;

public class ActivityAdd extends AppCompatActivity {
    TextInputEditText inputPhone;
    TextView name, phone, email;
    TextView noCoincidence;
    Button add,search;

    DatabaseReference databaseReference;
    ArrayList<Paciente> pacientes;
    Paciente paciente;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        inputPhone = findViewById(R.id.activity_add_input);

        name = findViewById(R.id.activity_add_name);
        phone = findViewById(R.id.activity_add_phone);
        email = findViewById(R.id.activity_add_email);

        noCoincidence = findViewById(R.id.activity_add_no_coincidence);

        add = findViewById(R.id.activity_add_add);
        search = findViewById(R.id.activity_add_search);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        pacientes = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.child("Paciente").getChildren()){
                    Paciente pac = snapshot.getValue(Paciente.class);
                    Log.e("FIREBASE_ADD",pac.toString());
                    pacientes.add(pac);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void searchBtn(View v){
        for (int i = 0; i<pacientes.size();i++){
            if (pacientes.get(i).getPhone().equals(inputPhone.getText().toString())){
                paciente = pacientes.get(i);
                add.setVisibility(View.VISIBLE);
                noCoincidence.setVisibility(View.INVISIBLE);
                name.setText(pacientes.get(i).getName());
                phone.setText(pacientes.get(i).getPhone());
                email.setText(pacientes.get(i).getEmail());
                return;
            }
        }
        paciente = new Paciente();//evitar null pointer exception.
        name.setText("NOT FOUND");
        phone.setText("NOT FOUND");
        email.setText("NOT FOUND");
        noCoincidence.setVisibility(View.VISIBLE);
        add.setVisibility(View.INVISIBLE);
    }
    public void addBtn(View v){
        loadID();
        databaseReference.child("Doctor").child(id).child("Pacientes").child(phone.getText().toString()).setValue(paciente);
        Toast.makeText(this,"The patient has been added succesfully",Toast.LENGTH_SHORT).show();

    }

    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        id = sharedPreferences.getString("ID","555");
        sharedPreferences = null;
    }
}
