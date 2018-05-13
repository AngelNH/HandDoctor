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
    String id;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<CitasPaciente> cp = new ArrayList<>();
    //Map<String,CitasPaciente> citas = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //erase these
        id = "3299085648";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_paciente);
        listView = findViewById(R.id.citaspaciente_list_view);

        //cp.add(new CitasPaciente("Vicky", "12/05/2018", "12.322", "13.23231", "Ginecología", "3:00pm"));
        //cp.add(new CitasPaciente("Miguel", "14/05/2018", "12.322", "13.23231", "Ginecología", "3:00pm"));


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
                for (DataSnapshot data : dataSnapshot.child("Paciente").child(id).child("Citas").getChildren()){
                    CitasPaciente cita = data.getValue(CitasPaciente.class);
                    cp.add(cita);
                    Log.e("ACTIVITY_CITAS_PAC",cita.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        AdapterCitasPacientes ad = new AdapterCitasPacientes(this, cp);
        listView.setAdapter(ad);
        //Log.e("FIREBASE",citas.toString());
    }


}
