package com.iteso.handdoctor;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Paciente;
import com.iteso.handdoctor.utils.AdapterPaciente;

import java.util.ArrayList;

public class ActivityExpedient extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    ArrayList<Paciente> pacientes = new ArrayList<>();
    ArrayList<Paciente> result = new ArrayList<>();
    boolean founded= false;
    String id;
    EditText input;
    Button search;
    AdapterPaciente adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expedient);
        founded = false;

        listView = findViewById(R.id.activity_expedient_list_view);
        input = findViewById(R.id.activity_expedient_input);
        search = findViewById(R.id.activity_expedient_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                founded = false;
                for (int i =0; i<pacientes.size();i++){
                    if (pacientes.get(i).getPhone().equals(input.getText().toString())){
                        result.add(pacientes.get(i));
                        Toast.makeText(ActivityExpedient.this,"Founded",Toast.LENGTH_SHORT).show();
                        founded = true;
                    }
                }
                if (founded)
                    adapter = new AdapterPaciente(ActivityExpedient.this,result,ActivityExpedient.this);
                else
                    adapter = new AdapterPaciente(ActivityExpedient.this,pacientes, ActivityExpedient.this);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            }
        });
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    adapter = new AdapterPaciente(ActivityExpedient.this,pacientes,ActivityExpedient.this);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        loadID();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.child("Doctor").child(id).child("Pacientes").getChildren()){
                    Paciente pac = data.getValue(Paciente.class);
                    pacientes.add(pac);
                    Log.e("FIREBASE_PACIENTE",pac.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //tomamos de la base de datos los datos del paciente y llenamos el array.
        adapter = new AdapterPaciente(this,pacientes, ActivityExpedient.this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                //CODIGO AQUI

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
