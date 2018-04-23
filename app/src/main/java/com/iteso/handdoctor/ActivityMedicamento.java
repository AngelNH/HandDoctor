package com.iteso.handdoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iteso.handdoctor.beans.Medicamento;
import com.iteso.handdoctor.utils.AdapterMedicamento;

import java.util.ArrayList;

public class ActivityMedicamento extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        listView = findViewById(R.id.medicamento_list_view);
        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        medicamentos.add(new Medicamento("Paracetamol", 2, 200, 15));
        medicamentos.add(new Medicamento("Ibuprofeno", 8, 150, 6));
        medicamentos.add(new Medicamento("Centrium", 2, 250, 7));
        medicamentos.add(new Medicamento("Antibiotico", 2, 2, 20));
        medicamentos.add(new Medicamento("Genoprazol", 2, 600, 5));
        medicamentos.add(new Medicamento("Omeprazol", 2, 800, 3));




        AdapterMedicamento ad = new AdapterMedicamento(this,medicamentos);
        listView.setAdapter(ad);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
            }
        });
    }
}