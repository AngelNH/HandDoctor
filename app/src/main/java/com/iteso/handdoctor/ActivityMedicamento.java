package com.iteso.handdoctor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.iteso.handdoctor.beans.Medicamento;
import com.iteso.handdoctor.utils.AdapterMedicamento;

import java.util.ArrayList;

public class ActivityMedicamento extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference=database.child("Medicamento");

    int idP; //Jalar id paciente actual
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
        listView = findViewById(R.id.medicamento_list_view);
        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        Iterable<Medicamento> medData = databaseReference.getChildren();

        for (Medicamento medi : medData) {
            if(medi.getIdPaciente()==idP){
                medicamentos.add(medi);
            }

        }
        AdapterMedicamento ad = new AdapterMedicamento(this,medicamentos);
        listView.setAdapter(ad);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
            }
        });
    }
}

