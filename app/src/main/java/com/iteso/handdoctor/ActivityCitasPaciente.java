package com.iteso.handdoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iteso.handdoctor.beans.CitasPaciente;
import com.iteso.handdoctor.utils.AdapterCitasPacientes;

import java.util.ArrayList;

public class ActivityCitasPaciente extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_paciente);
        listView = findViewById(R.id.citaspaciente_list_view);
        ArrayList<CitasPaciente> cp = new ArrayList<>();
        cp.add(new CitasPaciente("Vicky", "12 de mayo 2018", "Plaza del sol", "Medico general", "3 pm"));
        cp.add(new CitasPaciente("Jorge", "1 de mayo 2018", "iteso", "Psicologa", "6 pm"));
        cp.add(new CitasPaciente("Hector", "29 de abril 2018", "Galerias", "Dermatologo", "11 am"));
        cp.add(new CitasPaciente("Luis", "28 de junio 2018", "Centro magno", "Ginecologo", "4 pm"));
        cp.add(new CitasPaciente("Yara", "31 de julio 2018", "Plaza del sol", "Medico general", "7 pm"));
        cp.add(new CitasPaciente("Miguel", "12 de mayo 2018", "Plaza del sol" , "Medico general", "8 am"));
        cp.add(new CitasPaciente("Mariana", "13 de mayo 2018", "Plaza del sol", "Medico general", "9 pm"));

        AdapterCitasPacientes ad = new AdapterCitasPacientes(this, cp);
        listView.setAdapter(ad);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
            }
        });
    }
}
