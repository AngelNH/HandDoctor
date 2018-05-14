package com.iteso.handdoctor;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Medicamento;
import com.iteso.handdoctor.utils.AdapterMedicamento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityMedicamento extends AppCompatActivity {
    ListView listView;
    EditText search;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String id_pac;
    ArrayList<Medicamento> medicamentos = new ArrayList<>();
    ArrayList<String> erase = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);
        search = findViewById(R.id.activity_medicamento_search);
        loadID();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.child("Paciente").child(id_pac).child("Medicamento").getChildren()){
                    Medicamento medicamento = data.getValue(Medicamento.class);
                    int i =checkExpiration(medicamento);
                    if (i>0){
                        medicamento.setDiasRestantes(i);
                        Log.e("ACTIVITY_MED","dias restantes: "+medicamento.getExpiration());
                        medicamentos.add(medicamento);
                    }
                    else{
                        erase.add(data.getKey());
                    }
                }
                Log.e("ACTIVITY_MED","erase:"+erase.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView = findViewById(R.id.medicamento_list_view);
        Log.e("ACTIVITY_MED",""+medicamentos.toString());
        AdapterMedicamento ad = new AdapterMedicamento(this,medicamentos);
        listView.setAdapter(ad);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String aux = ""+s;
                Pattern pattern = Pattern.compile(aux+".*");
                Matcher matcher ;

                ArrayList<Medicamento> newmeds = new ArrayList<>();
                for (Medicamento m :medicamentos){
                    matcher = pattern.matcher(m.getNombre().toLowerCase());
                    if (matcher.matches()){
                        newmeds.add(m);
                    }
                }
                if (newmeds.size()>0){
                    AdapterMedicamento ad = new AdapterMedicamento(ActivityMedicamento.this,newmeds);
                    listView.setAdapter(ad);
                    listView.deferNotifyDataSetChanged();
                    return;
                }
                AdapterMedicamento ad = new AdapterMedicamento(ActivityMedicamento.this,medicamentos);
                listView.setAdapter(ad);
                listView.deferNotifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public int checkExpiration(Medicamento m){
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date today = Calendar.getInstance().getTime();
        Date expiration = new Date();
            try {
                expiration= df.parse(m.getExpiration());
            }catch (Exception e){}
            int days=(int) ((expiration.getTime()-today.getTime())/86400000);
            days++;
            Log.e("ADAPTER_MED","days left: "+days);
            return days;
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (String id_med:erase){
            databaseReference.child("Paciente").child(id_pac).child("Medicamento").child(id_med).removeValue();
        }
    }

    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        id_pac = sharedPreferences.getString("ID","555");
        sharedPreferences = null;
    }
}