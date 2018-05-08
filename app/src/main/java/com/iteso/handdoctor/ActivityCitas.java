package com.iteso.handdoctor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Citas;
import com.iteso.handdoctor.beans.CitasPaciente;
import com.iteso.handdoctor.beans.Doctor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityCitas extends AppCompatActivity {

    String id_doctor;
    String namePac;
    TextInputEditText id_text, motivo;
    TextView date,time;
    String hour;
    String appDate;
    Citas cita;
    Doctor doctor;
    CitasPaciente citasPaciente;
    int actyear,actmonth,actday;
    boolean first;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
        loadID();
        first = true;
        //casting
        date = findViewById(R.id.activity_citas_date_picked);
        time = findViewById(R.id.activity_citas_time_picked);
        //input text
        id_text = findViewById(R.id.activity_citas_id);
        motivo = findViewById(R.id.activity_citas_motivo);
        //date format
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String date = df.format(Calendar.getInstance().getTime());
        String [] dates = date.split("/");
        actyear = Integer.parseInt(dates[0]);
        actmonth = Integer.parseInt(dates[1]);
        actday = Integer.parseInt(dates[2]);
        //firebase create
        databaseReference = FirebaseDatabase.getInstance().getReference();
        doctor = new Doctor();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                doctor.setEspecialidad(dataSnapshot.child("Doctor").child(id_doctor).child("especialidad").getValue(String.class));
                doctor.setConsultorioLat(dataSnapshot.child("Doctor").child(id_doctor).child("consultorioLat").getValue(String.class));
                doctor.setConsultorioLon(dataSnapshot.child("Doctor").child(id_doctor).child("consultorioLon").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        showDatePicker();
    }

    public void agendarBtn(View view){
        Intent intent = new Intent(ActivityCitas.this,ActivityMain.class);
        cita = new Citas(id_doctor,id_text.getText().toString(),appDate,hour);
        fillCitaPac();
        getPacName();
        saveOnFirebase();
        startActivity(intent);
    }

    public void fillCitaPac(){

       citasPaciente = new CitasPaciente(cita.getId_doc(),cita.getId_pac(),cita.getFecha(),cita.getHora(),doctor.getEspecialidad(),doctor.getConsultorioLat(),doctor.getConsultorioLon());
    }
    public void saveOnFirebase(){
        String cita_id = databaseReference.child("Citas").push().getKey();
        cita.setName_pac(namePac);
        citasPaciente.setName_pac(namePac);
        databaseReference.child("Doctor").child(id_doctor).child("Citas").child(cita_id).setValue(cita);
        databaseReference.child("Paciente").child(id_text.getText().toString()).child("Citas").child(cita_id).setValue(citasPaciente);
        Toast.makeText(this,"The appointment has been added succesfully",Toast.LENGTH_SHORT).show();

    }
    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        id_doctor = sharedPreferences.getString("ID","555");
        sharedPreferences = null;
    }
    public void onClickTime(View v ){
        showTimePicker();
    }
    public void showTimePicker(){
        TimePickerDialog time1 = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                int check = minute-9;
                if (check<=0) {
                    hour = "" + hourOfDay + ":0" + minute;
                }
                else{
                    hour = "" + hourOfDay + ":" + minute;
                }
                time.setText(hour);
            }
        },12,0,true);
        time1.show();
    }
    public void onClickDate(View v ){
        showDatePicker();
    }
    public void showDatePicker(){
        DatePickerDialog date1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                appDate = ""+dayOfMonth+"/"+month+"/"+year;
                date.setText(appDate);
                if(first){
                    showTimePicker();
                    first = false;
                }
            }
        },actyear,actmonth,actday);
        date1.show();

    }

    public void getPacName(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                namePac = dataSnapshot.child("Paciente").child(id_text.getText().toString()).child("name").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
