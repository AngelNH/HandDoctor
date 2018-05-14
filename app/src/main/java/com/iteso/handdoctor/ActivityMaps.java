package com.iteso.handdoctor;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Paciente;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ActivityMaps extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Marker marker;
    double latitud = 0;
    double longitud = 0;
    String gen_id, type;
    EditText editText;
    ImageButton imageButton;
    String lon,lat,nameDoc;

    boolean isPatient;
    EditText address;
    Button search;
    FloatingActionButton plusButton,consultorio;


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            loadID();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            lon = bundle.getString("LON");
            lat = bundle.getString("LAT");
            nameDoc = bundle.getString("NAME");
        }
        isPatient=false;
            databaseReference = FirebaseDatabase.getInstance().getReference();


            int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

            if (status == ConnectionResult.SUCCESS) {
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
            }else{
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,(Activity)getApplicationContext(),10);
                dialog.show();
            }
            FloatingActionsMenu floatingActionsMenu = findViewById(R.id.floatingActionsMenu);
            com.getbase.floatingactionbutton.FloatingActionButton floatingActionButton = findViewById(R.id.activity_maps_add_location);
            consultorio = findViewById(R.id.activity_maps_contact_a);
            imageButton = findViewById(R.id.activity_maps_btn_search);
            editText= findViewById(R.id.activity_maps__edt_address);
            if (type.equals(""+ Paciente.PACIENTE)) {
                floatingActionsMenu.setVisibility(View.VISIBLE);
                floatingActionButton.setVisibility(View.GONE);
                imageButton.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
                consultorio.setTitle(nameDoc);
                isPatient=true;
            }else if (type.equals(""+ Paciente.DOCTOR)) {
                floatingActionButton.setVisibility(View.VISIBLE);
                floatingActionsMenu.setVisibility(View.GONE);
                editText.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.VISIBLE);
                isPatient=false;
            }
        }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//        UiSettings uiSettings = mMap.getUiSettings();
//        uiSettings.setZoomControlsEnabled(true);
        //Agregar la direccion del medico.
        //(Solicitar a consultorio ó dar de alta desde aqui)
        if (isPatient){
            latitud= Double.parseDouble(lat);
            longitud =Double.parseDouble(lon);
            LatLng main = new LatLng(latitud,longitud);
            mMap.addMarker(new MarkerOptions().position(main).title("Consultorio: "+nameDoc));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(main,15));
        }else {
            LatLng gdl = new LatLng(20.6775,-103.3335);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gdl,14));
        }

//
//
//        LatLng doctor1 = new LatLng(20.666551,-103.33335);
//        mMap.addMarker(new MarkerOptions().position(doctor1).title("Dr.Rodrigo"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(doctor1));
//
//        LatLng doctor2 = new LatLng(20.667551,-103.34365);
//        mMap.addMarker(new MarkerOptions().position(doctor2).title("Dr.Hernandez"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(doctor2));
//
//        LatLng doctor3 = new LatLng(20.662551,-103.34935);
//        mMap.addMarker(new MarkerOptions().position(doctor3).title("Dr.De Anda"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(doctor3));
//
//        LatLng doctor4 = new LatLng(20.616351,-103.32035);
//        mMap.addMarker(new MarkerOptions().position(doctor4).title("Dr.Gonzalez"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(doctor4));

    }
    @Override
    public void onMapClick(LatLng latLng) {
        GoogleMap googleMap = null;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                latLng, 13));
        googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapa))
                .title("Custom Marker")
                .snippet("My Custom Marker")
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(latLng));
    }

    private void addMarker(double latitud, double longitud)//Doctor para dar de alta su consultorio (falta)
    {
        LatLng coordenadas = new LatLng(latitud, longitud);
        CameraUpdate myUbic = CameraUpdateFactory.newLatLngZoom(coordenadas,13);
        if(marker != null) marker.remove();
        marker = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Nuevo consultorio").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.animateCamera(myUbic);
    }

    //Este metodo solo funcionara si es doctor
//
//
    public void addLocation(View view) throws IOException {
        Geocoder geocoder = new Geocoder(this, new Locale("es", "MX"));
        try {
            address = findViewById(R.id.activity_maps__edt_address);
            String addressText = address.getText().toString();
            if (addressText == " " || addressText == "") {
                Toast.makeText(getApplicationContext(), "Introduce una dirección", Toast.LENGTH_LONG).show();
            } else {

                List<Address> addressList = geocoder.getFromLocationName(addressText, 3);
                if (addressList != null && addressList.size() > 0) {
                    latitud = (addressList.get(0).getLatitude());
                    longitud = (addressList.get(0).getLongitude());
                    LatLng consultorio = new LatLng(latitud, longitud);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(consultorio));
                    mMap.getMaxZoomLevel();
                    mMap.addMarker(new MarkerOptions().position(consultorio).title("Doctor nuevo"));//solicitar nombre del doctor
//                    Agregar a firebase el consultorio del doctor
                    databaseReference.child("Doctor").child(gen_id).child("consultorioLat").setValue(""+latitud);
                    databaseReference.child("Doctor").child(gen_id).child("consultorioLon").setValue(""+longitud);
                    Toast.makeText(this,"Se añadió su dirección",Toast.LENGTH_SHORT).show();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Introduce una dirección", Toast.LENGTH_LONG).show();
        }

    }

    public void goConsul (View view){

    }
    public void searchLocation(View view) {
        Geocoder geocoder = new Geocoder(this, new Locale("es", "MX"));
        try {
            address = findViewById(R.id.activity_maps__edt_address);
            String addressText = address.getText().toString();
            if (addressText == " " || addressText == "") {
                Toast.makeText(getApplicationContext(), "Introduce una dirección", Toast.LENGTH_LONG).show();
            } else {

                List<Address> addressList = geocoder.getFromLocationName(addressText, 3);
                if (addressList != null && addressList.size() > 0) {
                    latitud = (addressList.get(0).getLatitude());
                    longitud = (addressList.get(0).getLongitude());
                    LatLng consultorio = new LatLng(latitud, longitud);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(consultorio));
                    mMap.getMaxZoomLevel();
                    mMap.addMarker(new MarkerOptions().position(consultorio).title("Doctor nuevo")).isDraggable();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Introduce una dirección", Toast.LENGTH_LONG).show();
        }
    }


    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        gen_id = sharedPreferences.getString("ID","555");
        type = sharedPreferences.getString("TYPE","555");
        sharedPreferences = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
