package com.iteso.handdoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Citas;
import com.iteso.handdoctor.beans.Doctor;
import com.iteso.handdoctor.beans.Paciente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivityDoctor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView nameNav, emailNav;

    CalendarView calendarView;
    TextView date, hour, name, motive;
    ArrayList<Citas> citas;
    DatabaseReference databaseReference;
    String id_doc;
    Doctor doctor;
    Date today, sel_date;
    int sel_year, sel_month, sel_day;
    ImageButton prev, next;
    int actual_cita;
    String namePacienteActual;
    int auxY, auxM, auxD;
    LinearLayout appointment;
    boolean pacNameReady;
    ArrayList<Paciente> pacientes;
    String nameDocActual,emailDocActual;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDoctor.this,ActivityCitas.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //------------------------------------------
        //------------------------------------------
        //----------------AGENDA--------------------
        //------------------------------------------
        //------------------------------------------

        pacientes = new ArrayList<>();
        actual_cita = 0;
        calendarView = findViewById(R.id.activity_agenda_calendar);
        date = findViewById(R.id.activity_agenda_date);
        hour = findViewById(R.id.activity_ageda_hour);
        name = findViewById(R.id.activity_agenda_pac_name);
        prev = findViewById(R.id.activity_agenda_prev);
        next = findViewById(R.id.activity_agenda_next);
        motive = findViewById(R.id.activity_agenda_motive);
        appointment = findViewById(R.id.appointment);


        loadID();

        doctor = new Doctor();
        citas = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nameDocActual= dataSnapshot.child("Doctor").child(id_doc).child("name").getValue(String.class);
                emailDocActual= dataSnapshot.child("Doctor").child(id_doc).child("email").getValue(String.class);

                for (DataSnapshot data : dataSnapshot.child("Doctor").child(id_doc).child("Citas").getChildren()) {
                    Citas cita = data.getValue(Citas.class);
                    citas.add(cita);
                    Log.e("FIREBASE_AGENDA", cita.toString());
                }
                getNameFirebase(citas.get(actual_cita));
                sortByHour();
                for(DataSnapshot d :dataSnapshot.child("Doctor").child(id_doc).child("Pacientes").getChildren()){
                    Paciente paciente = d.getValue(Paciente.class);
                    pacientes.add(paciente);
                    Log.e("FIREBASE_AGENDA_PAC", paciente.toString());
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //get today´s date
        today = Calendar.getInstance().getTime();
        calendarView.setDate(today.getTime());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                auxY = year;
                auxM = month + 1;
                auxD = dayOfMonth;
                getNameFirebase(citas.get(actual_cita));
                loadFirstApp();

            }
        });
        //get calendars dates.
        sel_date = new Date(calendarView.getDate());
        String[] split_date = splitDate(sel_date);
        sel_year = Integer.parseInt(split_date[0]);
        sel_month = Integer.parseInt(split_date[1]);
        sel_day = Integer.parseInt(split_date[2]);
        //button
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actual_cita != 0) {
                    actual_cita--;
                }
                loadFirstApp();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actual_cita < citas.size() - 1) {
                    actual_cita++;
                }
                loadFirstApp();
            }
        });

        //----------------------------------------------------
        //----------------------------------------------------
        //---------------------AGENDA END---------------------
        //----------------------------------------------------
        //----------------------------------------------------
    }
    //----------------------------------------------------------------
    //----------------------------------------------------------------
    //----------------------------------------------------------------
    //---------------------AGENDA METHODS-----------------------------
    //----------------------------------------------------------------
    //----------------------------------------------------------------
    //----------------------------------------------------------------
    public Paciente searchPacByID(String id){
        for(Paciente paciente: pacientes){
            if (paciente.getPhone().equals(id))
                return paciente;
        }
        return null;
    }

    public void loadID() {
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES", MODE_PRIVATE);
        id_doc = sharedPreferences.getString("ID", "555");
        sharedPreferences = null;
    }

    public String[] splitDate(Date date) {
        //date format
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String fecha = df.format(date);
        String[] dates = fecha.split("/");
        return dates;
    }

    public void sortByHour() {
        //ArrayList<String> sorted = new ArrayList<>();
        for (int i = 0; i < citas.size(); i++) {
            for (int j = 0; j < citas.size(); j++) {
                if (soonDate(citas.get(i), citas.get(j))) {
                    //switch
                    Citas c = citas.get(j);
                    citas.set(j, citas.get(i));
                    citas.set(i, c);
                    Log.e("ACTIVITY_AGENDA", citas.get(i).toString());
                    Log.e("ACTIVITY_AGENDA", citas.get(j).toString());
                }
            }
        }
        Log.e("ACTIVITY_AGENDA", citas.toString());

    }

    public boolean soonDate(Citas cita1, Citas cita2) {
        Log.e("ACTIVITY_AGENDA_SOON", cita1.toString());
        Log.e("ACTIVITY_AGENDA_SOON", cita2.toString());
        String hora1 = cita1.getHora();
        String[] aux = hora1.split(":");
        int h1 = Integer.parseInt(aux[0]);
        int m1 = Integer.parseInt(aux[1]);
        String hora2 = cita2.getHora();
        aux = hora2.split(":");
        int h2 = Integer.parseInt(aux[0]);
        int m2 = Integer.parseInt(aux[1]);

        if (h1 < h2) {
            Log.e("ACTIVITY_AGENDA_SOON", "true");
            return true;
        } else if (h1 == h2 && m1 < m2) {
            Log.e("ACTIVITY_AGENDA_SOON", "true");
            return true;
        }
        Log.e("ACTIVITY_AGENDA_SOON", "false");
        return false;
    }

    public void getNameFirebase(final Citas app) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                namePacienteActual = dataSnapshot.child("Paciente").child(app.getId_pac()).child("name").getValue(String.class);
                pacNameReady = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    String auxF="FFF",auxH = "HHH";
    boolean dateFounded;
    public void loadFirstApp(){
        String fecha="";
        fecha+=auxD+"/";
        fecha+=auxM+"/";
        fecha+=auxY;

        Log.e("ACTIVITY_AGENDA_ACT",fecha);
        Log.e("ACTIVITY_AGENDA_ACT",citas.get(actual_cita).getFecha().toString());
        for (int i = 0; i<citas.size();i++){
            if (fecha.equals(citas.get(i).getFecha().toString())){
                Log.w("ACTIVITY_AGENDA_ACT","actual cita"+actual_cita);
                Log.w("ACTIVITY_AGENDA_ACT","citas size"+citas.size());
                Citas c =citas.get(actual_cita);
                getNameFirebase(c);
                //
                auxF = c.getFecha();
                auxH = c.getHora();
                dateFounded = true;
                Log.w("ACTIVITY_AGENDA_ACT","Fecha "+auxF);
                Log.w("ACTIVITY_AGENDA_ACT","hora "+auxH);
                Log.w("ACTIVITY_AGENDA_ACT","datefounded"+dateFounded);
                date.setText(c.getFecha());
                hour.setText(c.getHora());
                motive.setText(c.getMotive());
                Paciente p = searchPacByID(c.getId_pac());
                name.setText(p.getName());
                //name.setText(namePacienteActual);
                appointment.setVisibility(View.VISIBLE);
                return;

            }
        }
        appointment.setVisibility(View.INVISIBLE);
    }
    //----------------------------------------------------------------
    //----------------------------------------------------------------
    //----------------------------------------------------------------
    //----------------------------------------------------------------
    //----------------------------------------------------------------
    //----------------------------------------------------------------

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_doctor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.activity_nav_action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//
        //
        //
        //
        //Aqui se cargan las actividades dependiento que se selecciona
        ///
//        if (id == R.id.activity_nav_doctor_agenda) {
//            Intent intent = new Intent(ActivityDoctor.this, Activity_Agenda.class);
//            startActivity(intent);
            //Activities de miguel
        if (id == R.id.activity_nav_doctor_expediente) {
            Intent intent = new Intent(ActivityDoctor.this, ActivityExpedient.class);
            startActivity(intent);
            //activities de Miguel
        } else if (id == R.id.activity_nav_doctor_cita) {
            Intent intent = new Intent(ActivityDoctor.this, ActivityCitas.class);
            startActivity(intent);
            //Activities de miguel
        } else if (id == R.id.activity_nav_doctor_receta) {
            Intent intent = new Intent(ActivityDoctor.this, ActivityRecetar.class);
            startActivity(intent);
//            Activities de miguel
        }else if (id == R.id.activity_nav_doctor_addPaciente) {
            Intent intent = new Intent(ActivityDoctor.this, ActivityAdd.class);
            startActivity(intent);
//            Activities de miguel
        }else if (id == R.id.activity_nav_doctor_chat) {
            Intent intent = new Intent(ActivityDoctor.this, ActivityRoomChat.class);
            startActivity(intent);



//            Activities de Leo
        } else if (id == R.id.activity_nav_doctor_config) {
            Intent intent = new Intent(ActivityDoctor.this, ActivityConfigProfile.class);//falta modificar
            startActivity(intent);

        } else if (id == R.id.activity_nav_doctor_maps) {
            Intent intent = new Intent(ActivityDoctor.this, ActivityMaps.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void openMap(View view) {
//        Intent intent = new Intent(ActivityDoctor.this, ActivityMaps.class);
//        startActivity(intent);
//    }


    ///////////////////////////////////////////////////
    ///////////////Logout de la APP///////////////////
    public void logOut(MenuItem item) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        finish();
    }
}
