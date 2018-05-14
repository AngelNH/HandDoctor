package com.iteso.handdoctor;

import android.content.Intent;
import android.content.SharedPreferences;
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
    AdapterCitasPacientes ad;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<CitasPaciente> cp = new ArrayList<>();
    //Map<String,CitasPaciente> citas = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_paciente);
        listView = findViewById(R.id.citaspaciente_list_view);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
            }
        });
        loadID();
        ad= new AdapterCitasPacientes(this, cp);
        id= "3318275490";
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
                ad.notifyDataSetChanged();
                listView.deferNotifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView.setAdapter(ad);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String lon = cp.get(position).getConsultorioLon();
                String lat = cp.get(position).getConsultorioLat();
                String name = cp.get(position).getName_doc();
                Intent i = new Intent(ActivityCitasPaciente.this,ActivityMaps.class);
                i.putExtra("LAT",lat);
                i.putExtra("LON",lon);
                i.putExtra("NAME",name);
                startActivity(i);
            }
        });
        //Log.e("FIREBASE",citas.toString());
    }
    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        id = sharedPreferences.getString("ID","555");
        sharedPreferences = null;
    }


}
