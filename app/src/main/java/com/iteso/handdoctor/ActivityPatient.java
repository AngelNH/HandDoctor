package com.iteso.handdoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.CitasPaciente;
import com.iteso.handdoctor.utils.AdapterCitasPacientes;

public class ActivityPatient extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageSwitcher imageSwitcher;
    private int[] gallery = { R.drawable.advice, R.drawable.advice1, R.drawable.advice2,
            R.drawable.advice3, R.drawable.advice4};
    String id;
    AdapterCitasPacientes ad;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<CitasPaciente> cp = new ArrayList<>();

    private int position;

    private static final Integer DURATION = 10000;

    private Timer timer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
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
                Intent i = new Intent(ActivityPatient.this,ActivityMaps.class);
                i.putExtra("LAT",lat);
                i.putExtra("LON",lon);
                i.putExtra("NAME",name);
                startActivity(i);
            }
        });
        //Log.e("FIREBASE",citas.toString());


        //VICKY
        imageSwitcher =  findViewById(R.id.advice);
        imageSwitcher.setFactory(new ViewFactory() {

            public View makeView() {
                return new ImageView(ActivityPatient.this);
            }
        });
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);

        if (timer != null) {
            timer.cancel();
        }
        position = 0;
        startSlider();






        //LEO/&//////////////////////////////////////////////
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    ////////////////////////////////////////////////////////////

//VICKYY///////////////////////////////////////////////////////
    public void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        imageSwitcher.setImageResource(gallery[position]);
                        position++;
                        if (position == gallery.length) {
                            position = 0;
                        }
                    }
                });
            }

        }, 0, DURATION);
    }
    // Stops the slider when the Activity is going into the background
    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            startSlider();
        }

    }
    ////////////////////////////////////////////////////////////////////

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
        getMenuInflater().inflate(R.menu.activity_patient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.activity_nav_patient_citasMedicas) {
            Intent intent = new Intent(ActivityPatient.this, ActivityCitasPaciente.class);
            startActivity(intent);
        } else if (id == R.id.activity_nav_patient_medicamentos) {
            Intent intent = new Intent(ActivityPatient.this, ActivityMedicamento.class);//falta modificar
            startActivity(intent);
        } else if (id == R.id.activity_nav_patient_medicalPreview) {

        } else if (id == R.id.activity_nav_patient_chat) {
            Intent intent = new Intent(ActivityPatient.this, ActivityRoomChat.class);//falta modificar
            startActivity(intent);
        } else if (id == R.id.activity_nav_patient_config) {
            Intent intent = new Intent(ActivityPatient.this, ActivityConfigProfile.class);//falta modificar
            startActivity(intent);

        } else if (id == R.id.activity_nav_patient_maps) {
            Intent intent = new Intent(ActivityPatient.this, ActivityMaps.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    ///////////////////////////////////////////////////
    ///////////////Logout de la APP///////////////////
    public void logOut(MenuItem item) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(this,"You just Logged out", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        id = sharedPreferences.getString("ID","555");
        sharedPreferences = null;
    }


}
