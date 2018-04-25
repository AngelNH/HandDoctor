package com.iteso.handdoctor.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iteso.handdoctor.R;
import com.iteso.handdoctor.beans.Medicamento;

import java.util.ArrayList;

public class AdapterMedicamento extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Medicamento> medicamentos;
    private TextView idMed;
    private TextView cantidad;
    private TextView idPaciente;
    private TextView diasRestantes;
    private TextView dosis, nombre;
    private TextView tipo;

    public AdapterMedicamento(Activity activity, ArrayList<Medicamento> medicamentos) {
        this.activity = activity;
        this.medicamentos = medicamentos;
    }

    @Override
    public int getCount() {
        return medicamentos.size();
    }
    public void clear(){
        medicamentos.clear();
    }

    public void addAll(ArrayList<Medicamento> lista){
        for (int i = 0;i > lista.size();i++){
            medicamentos.add(lista.get(i));
        }
    }
    @Override
    public Object getItem(int i) {
        return medicamentos.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        View v = view;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.medicamentos_items,null);
        }

         Medicamento med = medicamentos.get(pos);
        nombre = v.findViewById(R.id.nombreMedicamento_label);
        nombre.setText(med.getNombre());
        cantidad = v.findViewById(R.id.cantidad_label);
        cantidad.setText(med.getCantidad());
        dosis=v.findViewById(R.id.dosis_label);
        dosis.setText(med.getDosis());
        diasRestantes=v.findViewById(R.id.diasRestantes_label);
        diasRestantes.setText(med.getDiasRestantes());
        return v;
    }
}

