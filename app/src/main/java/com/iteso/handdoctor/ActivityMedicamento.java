package com.iteso.handdoctor;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String id;
    ArrayList<Medicamento> medicamentos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.child("Medicamento").getChildren()){
                    //TODO get the data from database depending from the database.
                    //Medicamento medicamento = data.getValue(Medicamento.class);
                    //medicamentos.add(medicamento);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView = findViewById(R.id.medicamento_list_view);

        //medicamentos tomados de la base de datos, para mostrar en la list view del usuario.
        /*
        medicamentos.add(new Medicamento("Paracetamol","50mg","1 cada 8 horas.",15,Medicamento.MEDICAMENTO_PASTILLA));
        medicamentos.add(new Medicamento("Ibuprofeno","2mg","2 cada 12 horas.",3,Medicamento.MEDICAMENTO_INYECCION));
        medicamentos.add(new Medicamento("Centrium","20mg","1 1/2 cada 8 horas.",7,Medicamento.MEDICAMENTO_JARABE));
        medicamentos.add(new Medicamento("Antibiótico","1mg","1 cada 24 horas.",20,Medicamento.MEDICAMENTO_PASTILLA));*/





        AdapterMedicamento ad = new AdapterMedicamento(this,medicamentos);
        listView.setAdapter(ad);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
            }
        });
    }

    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        id = sharedPreferences.getString("ID","555");
        sharedPreferences = null;
    }
}