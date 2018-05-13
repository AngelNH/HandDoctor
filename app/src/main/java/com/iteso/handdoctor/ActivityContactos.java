package com.iteso.handdoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
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
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Contacto;
import com.iteso.handdoctor.beans.Doctor;
import com.iteso.handdoctor.beans.Paciente;
import com.iteso.handdoctor.beans.Room;
import com.iteso.handdoctor.utils.AdapterContactos;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityContactos extends AppCompatActivity {

    EditText searchName;
    ListView contactList;
    FloatingActionButton fab;

    DatabaseReference databaseReference;

    String gen_id,type;
    ArrayList<Contacto>contactos = new ArrayList<>();
    ArrayList<Paciente> pacientes = new ArrayList<>();
    final ArrayList<Doctor> doctors = new ArrayList<>();
    AdapterContactos adapterContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        searchName = findViewById(R.id.activity_contactos_input);
        contactList = findViewById(R.id.activity_contactos_list_view);
        fab = findViewById(R.id.activity_contactos_fab);
        //contactos = new ArrayList<>();


        loadID();
        adapterContactos = new AdapterContactos(ActivityContactos.this,contactos);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactList.clearChoices();
                //get the doctors
                for (DataSnapshot datad:dataSnapshot.child("Doctor").getChildren()){
                    doctors.add(datad.getValue(Doctor.class));
                }
                //get the patients
                for (DataSnapshot datap:dataSnapshot.child("Paciente").getChildren()){
                    pacientes.add(datap.getValue(Paciente.class));
                }
                //get the contacts
                if (type.equals(""+ Paciente.DOCTOR)){
                    for (DataSnapshot data : dataSnapshot.child("Doctor").child(gen_id).child("Contactos").getChildren()){
                        Contacto contacto = data.getValue(Contacto.class);
                        Log.e("ACT_CONTACTO","Contactos "+contacto.toString());
                        contactos.add(contacto);
                        contactList.deferNotifyDataSetChanged();
                        //adapterContactos.notifyDataSetChanged();

                    }
                }else if (type.equals(""+ Paciente.PACIENTE)){
                    for (DataSnapshot data : dataSnapshot.child("Paciente").child(gen_id).child("Contactos").getChildren()){
                        Contacto contacto = data.getValue(Contacto.class);
                        contactos.add(contacto);
                        contactList.deferNotifyDataSetChanged();
                        //adapterContactos.notifyDataSetChanged();

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.e("ACT_CONTACTO","Contactos "+contactos.toString());


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityContactos.this,ActivityAddChat.class);
                startActivity(intent);
            }
        });

        searchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = ""+s;
                Pattern pattern = Pattern.compile(input+".*");
                Matcher matcher ;

                ArrayList<Contacto> newContact = new ArrayList<>();
                for (Contacto c: contactos){
                    matcher = pattern.matcher(c.getName().toLowerCase());
                    if (matcher.matches()){
                        newContact.add(c);
                    }
                }


                if (newContact.size()>0){
                    contactList.clearChoices();
                    adapterContactos = new AdapterContactos(ActivityContactos.this,newContact);
                    contactList.setAdapter(adapterContactos);
                    contactList.deferNotifyDataSetChanged();
                    //adapterContactos.notifyDataSetChanged();
                    return;
                    //
                }
                contactList.clearChoices();
                adapterContactos= new AdapterContactos(ActivityContactos.this,contactos);
                contactList.setAdapter(adapterContactos);
                //adapterContactos.notifyDataSetChanged();
                contactList.deferNotifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        contactList.setAdapter(adapterContactos);

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (contactos.get(position).getChat().equals("555")){
                    //create new room
                    String nameDoc="NOP", namePac = "NOP";
                    String id_chat =databaseReference.child("Salas").push().getKey();
                    contactos.get(position).setChat(id_chat);
                    if (type.equals(""+Paciente.DOCTOR)){
                        namePac = getPacienteByPhone(contactos.get(position).getPhone()).getName();
                        nameDoc = getDoctorByPhone(gen_id).getName();
                        //add the reference of chat
                        databaseReference.child("Doctor").child(gen_id).child("Contactos").child(contactos.get(position).getPhone()).child("chat").setValue(id_chat);
                        databaseReference.child("Paciente").child(contactos.get(position).getPhone()).child("Contactos").child(gen_id).child("chat").setValue(id_chat);
                        databaseReference.child("Paciente").child(contactos.get(position).getPhone()).child("Contactos").child(gen_id).child("name").setValue(nameDoc);
                    }else if (type.equals(""+Paciente.PACIENTE)){
                        nameDoc= getDoctorByPhone(contactos.get(position).getPhone()).getName();
                        namePac = getPacienteByPhone(gen_id).getName();
                        //add the reference of chat
                        databaseReference.child("Paciente").child(gen_id).child("Contactos").child(contactos.get(position).getPhone()).child("chat").setValue(id_chat);
                        databaseReference.child("Doctor").child(contactos.get(position).getPhone()).child("Contactos").child(gen_id).child("chat").setValue(id_chat);
                        databaseReference.child("Doctor").child(contactos.get(position).getPhone()).child("Contactos").child(gen_id).child("name").setValue(namePac);
                    }
                    databaseReference.child("Salas").child(id_chat).setValue(new Room(nameDoc,namePac,Long.valueOf("4"),""));


                }
            }
        });

    }

    public Paciente getPacienteByPhone(String phone){
        for (Paciente p : pacientes){
            if (p.getPhone().equals(phone))
                return p;
        }
        return null;
    }
    public Paciente getDoctorByPhone(String phone){
        for (Doctor d : doctors){
            if (d.getPhone().equals(phone))
                return d;
        }
        return null;
    }
    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        gen_id = sharedPreferences.getString("ID","555");
        type = sharedPreferences.getString("TYPE","555");
        sharedPreferences = null;
    }
}
