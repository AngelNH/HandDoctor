package com.iteso.handdoctor;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityMaps extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Marker marker;
    double latitud = 0;
    double longitud = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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


        //Agregar la direccion del medico.
        //(Solicitar a consultorio ó dar de alta desde aqui)
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        LatLng doctor1 = new LatLng(20.666551,-103.33335);
        mMap.addMarker(new MarkerOptions().position(doctor1).title("Dr.Rodrigo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(doctor1));

        LatLng doctor2 = new LatLng(20.667551,-103.34365);
        mMap.addMarker(new MarkerOptions().position(doctor2).title("Dr.Hernandez"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(doctor2));

        LatLng doctor3 = new LatLng(20.662551,-103.34935);
        mMap.addMarker(new MarkerOptions().position(doctor3).title("Dr.De Anda"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(doctor3));

        LatLng doctor4 = new LatLng(20.616351,-103.32035);
        mMap.addMarker(new MarkerOptions().position(doctor4).title("Dr.Gonzalez"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(doctor4));
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
        CameraUpdate myUbic = CameraUpdateFactory.newLatLngZoom(coordenadas,16);
        if(marker != null) marker.remove();
        marker = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Nuevo consultorio").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.animateCamera(myUbic);
    }


//Localizacion del usuario

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
}
