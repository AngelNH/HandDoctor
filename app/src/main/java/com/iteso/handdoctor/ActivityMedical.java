package com.iteso.handdoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Expediente;
import com.iteso.handdoctor.beans.Paciente;
import com.iteso.handdoctor.beans.User;

import java.util.ArrayList;

public class ActivityMedical extends AppCompatActivity {
    EditText id;
    EditText chronic, medicament, age, from;
    RadioButton female, male;
    EditText nationality,religion,weight,height,blood,actual,tension;
    Button send;
    RadioButton yesDisease, noDisease,yesMedicament,noMedicament;
    RadioButton single, married, divorced;
    TextView patname;

    String id_doc;

    ArrayList<Paciente> pacientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);


        id = findViewById(R.id.activity_medical_edt_id);
        send = findViewById(R.id.activity_medical_btn_send);
        chronic = findViewById(R.id.activity_medical_edt_chronic);
        medicament = findViewById(R.id.activity_medical_edt_medicament);
        age = findViewById(R.id.activity_medical_edt_age);
        from = findViewById(R.id.activity_medical_edt_from);
        nationality = findViewById(R.id.activity_medical_edt_nationality);
        religion = findViewById(R.id.activity_medical_edt_religion);
        weight = findViewById(R.id.activity_medical_edt_weight);
        height = findViewById(R.id.activity_medical_edt_height);
        blood = findViewById(R.id.activity_medical_edt_blood);
        tension = findViewById(R.id.activity_medical_edt_arterial_tension);
        actual = findViewById(R.id.activity_medical_edt_actual_disease);
        //RadioButtons.
        female = findViewById(R.id.activity_medical_rdb_female);
        male = findViewById(R.id.activity_medical_rdb_male);
        yesDisease = findViewById(R.id.activity_medical_rdb_yesDisease);
        noDisease = findViewById(R.id.activity_medical_rdb_noDisease);
        yesMedicament = findViewById(R.id.activity_medical_rdb_yesMedicament);
        noMedicament = findViewById(R.id.activity_medical_rdb_noMedicament);
        single = findViewById(R.id.activity_medical_rdb_single);
        married = findViewById(R.id.activity_medical_rdb_married);
        divorced = findViewById(R.id.activity_medical_rdb_divorced);

        female.setChecked(true);
        single.setChecked(true);
        noDisease.setChecked(true);
        noMedicament.setChecked(true);
        chronic.setEnabled(false);
        medicament.setEnabled(false);
        loadID();

        yesDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronic.setEnabled(true);
            }
        });
        noDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronic.setEnabled(false);
            }
        });
        yesMedicament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicament.setEnabled(true);
            }
        });
        noMedicament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicament.setEnabled(false);
            }
        });

        patname = findViewById(R.id.activity_medical_txt_name);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for (DataSnapshot data : dataSnapshot.child("Paciente").getChildren()){//change it only from de doctor.
                for (DataSnapshot data : dataSnapshot.child("Doctor").child(id_doc).child("Pacientes").getChildren()){
                    pacientes.add(data.getValue(Paciente.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s+"";
                for (int i = 0;i<pacientes.size();i++){
                     if (pacientes.get(i).getPhone().equals(text)){
                         patname.setText(pacientes.get(i).getName());
                         return;
                     }
                    patname.setText("(Patient Name)");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//      Mandar a base de datos con id del paciente...
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expediente expediente = new Expediente();
                expediente.setId(id.getText().toString());
                //gender
                if (female.isChecked()) {
                    expediente.setGenderN(Expediente.GENDER_FEMALE);
                    expediente.setGender("Female");
                } else if (male.isChecked()) {
                    expediente.setGenderN(Expediente.GENDER_MALE);
                    expediente.setGender("Masculine");
                }
                expediente.setAge(age.getText().toString());
                expediente.setDirection(from.getText().toString());
                expediente.setNationality(nationality.getText().toString());
                expediente.setReligion(religion.getText().toString());
                //civil state
                if (single.isChecked()) {
                    expediente.setCivil(Expediente.CIVIL_SINGLE);
                    expediente.setCivilS("Single");
                } else if (married.isChecked()) {
                    expediente.setCivil(Expediente.CIVIL_MARRIED);
                    expediente.setCivilS("Married");
                }else if (divorced.isChecked()) {
                expediente.setCivil(Expediente.CIVIL_DIVORCED);
                    expediente.setCivilS("Divorced");
                }
                //actual medication
                if (yesMedicament.isChecked())
                    expediente.setActualMedication(medicament.getText().toString());
                else
                    expediente.setActualMedication(" ");
                // chronic disease
                if (yesDisease.isChecked())
                    expediente.setChronic(chronic.getText().toString());
                else
                    expediente.setChronic(" ");
                expediente.setWeight(weight.getText().toString());
                expediente.setHeight(height.getText().toString());
                expediente.setBlood(blood.getText().toString());
                expediente.setActualDisease(actual.getText().toString());
                expediente.setTension(tension.getText().toString());
                //add to firebase
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Expediente").child(expediente.getId()).setValue(expediente);
                Intent intent = new Intent(ActivityMedical.this,ActivityDoctor.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        id_doc = sharedPreferences.getString("ID","555");
        sharedPreferences = null;

    }
}
