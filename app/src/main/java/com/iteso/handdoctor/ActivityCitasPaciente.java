package com.iteso.handdoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.CitasPaciente;
import com.iteso.handdoctor.utils.AdapterCitasPacientes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityCitasPaciente extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<CitasPaciente> cp = new ArrayList<>();
    Map<String,CitasPaciente> citas = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_paciente);
        listView = findViewById(R.id.citaspaciente_list_view);

        //cp.add(new CitasPaciente("Vicky", "12/05/2018", "12.322", "13.23231", "Ginecología", "3:00pm"));
        //cp.add(new CitasPaciente("Miguel", "14/05/2018", "12.322", "13.23231", "Ginecología", "3:00pm"));

        AdapterCitasPacientes ad = new AdapterCitasPacientes(this, cp);
        listView.setAdapter(ad);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
            }
        });

        //Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//TODO event listener



        Log.e("FIREBASE",citas.toString());


    }

    public void addCita(View v){
        for(int i = 0; i<cp.size();i++) {
            CitasPaciente cita = cp.get(i);
            String ID_cita = databaseReference.child("citasPaciente").push().getKey();
            databaseReference.child("citasPaciente").child(ID_cita).setValue(cita);
            citas.put(ID_cita,cita);
        }
    }
}
