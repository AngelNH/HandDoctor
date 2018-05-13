package com.iteso.handdoctor;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Expediente;

import java.util.ArrayList;

public class ActivityMedicalProfile extends AppCompatActivity {
     TextView name;
     TextView gender;
     TextView age;
     TextView direction;
     TextView nationality;
     TextView religion;
     TextView civil;
     TextView actualMedication;
     TextView chronic;
     TextView weight;
     TextView height;
     TextView blood;
     TextView actualDisease;
     TextView tension;

     String id_pac;
     String id_doc;
    DatabaseReference databaseReference;
    ArrayList<Expediente> expedientes;
    Expediente expediente;
    String pacName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_profile);
        loadID();

        name = findViewById(R.id.activity_medical_profile_name);
        gender = findViewById(R.id.activity_medical_profile_gender);
        age = findViewById(R.id.activity_medical_profile_age);
        direction = findViewById(R.id.activity_medical_profile_direction);
        nationality= findViewById(R.id.activity_medical_profile_nation);
        religion = findViewById(R.id.activity_medical_profile_religion);
        civil = findViewById(R.id.activity_medical_profile_civil);
        actualMedication = findViewById(R.id.activity_medical_profile_med);
        chronic = findViewById(R.id.activity_medical_profile_chronic);
        weight = findViewById(R.id.activity_medical_profile_weight);
        height = findViewById(R.id.activity_medical_profile_height);
        blood = findViewById(R.id.activity_medical_profile_blood);
        actualDisease = findViewById(R.id.activity_medical_profile_act_dis);
        tension = findViewById(R.id.activity_medical_profile_tension);
        loadIDPaciente();
        expedientes = new ArrayList<>();


        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                expediente = dataSnapshot.child("Expediente").child(id_pac).getValue(Expediente.class);
                //expedientes.add(expediente);
                pacName = dataSnapshot.child("Doctor").child(id_doc).child("Pacientes").child(id_pac).child("name").getValue(String.class);
                Log.e("FIREBASE_ACT_PROFILE","Expediente: "+expediente);
                loadOnView();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void loadIDPaciente(){

        id_pac = "3299085648";
        Bundle bundle = getIntent().getExtras();

        if (bundle.getString("ID_PAC") != null){
            id_pac = bundle.getString("ID_PAC");
        }
        Log.e("PROFILE","idpaciente "+id_pac);
    }
    public void loadOnView(){
        Log.e("FIREBASE_ACT_PROFILE","gender: "+expediente.getGender());
        name.setText(pacName);
        gender.setText(expediente.getGender());
        Log.e("FIREBASE_ACT_PROFILE","age: "+expediente.getAge());
        age.setText(expediente.getAge());
        direction.setText(expediente.getDirection());
        nationality.setText(expediente.getNationality());
        religion.setText(expediente.getReligion());
        civil.setText(expediente.getCivilS());
        actualMedication.setText(expediente.getActualMedication());
        chronic.setText(expediente.getChronic());
        weight.setText(expediente.getWeight());
        height.setText(expediente.getHeight());
        blood.setText(expediente.getBlood());
        actualDisease.setText(expediente.getActualDisease());
        tension.setText(expediente.getTension());
    }
    public void loadID() {
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES", MODE_PRIVATE);
        id_doc = sharedPreferences.getString("ID", "555");
        sharedPreferences = null;
    }
}
