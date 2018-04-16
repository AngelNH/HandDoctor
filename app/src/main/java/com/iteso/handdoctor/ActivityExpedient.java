package com.iteso.handdoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iteso.handdoctor.beans.Paciente;
import com.iteso.handdoctor.utils.AdapterPaciente;

import java.util.ArrayList;

public class ActivityExpedient extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expedient);

        listView = findViewById(R.id.activity_expedient_list_view);
        ArrayList<Paciente> pacientes = new ArrayList<>();
        //tomamos de la base de datos los datos del paciente y llenamos el array.
        //este código es para mostrar.
        //se va a borrar hasta los asteriscos
        pacientes.add(new Paciente(1,"Joceline Arreguin"));
        pacientes.add(new Paciente(2,"Rocio Hernández"));
        pacientes.add(new Paciente(3,"Ulises Arturo"));
        pacientes.add(new Paciente(4,"Edith Gonzalez"));
        pacientes.add(new Paciente(5,"Martha Arreguin"));
        pacientes.add(new Paciente(6,"Leo Nuño"));
        pacientes.add(new Paciente(7,"Victoria Sanchez"));
        pacientes.add(new Paciente(8,"Miguel Gonzalez"));
        //************************
        AdapterPaciente adapter = new AdapterPaciente(this,pacientes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                //CODIGO AQUI

            }
        });
    }
}
