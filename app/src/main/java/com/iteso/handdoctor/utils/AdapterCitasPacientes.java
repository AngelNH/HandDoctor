package com.iteso.handdoctor.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iteso.handdoctor.R;
import com.iteso.handdoctor.beans.CitasPaciente;

import java.util.ArrayList;

/**
 * Created by inqui on 23/04/2018.
 */

public class AdapterCitasPacientes extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<CitasPaciente> citasPacientes;

    private TextView doctor;
    private TextView fecha;
    private TextView lugar;
    private TextView especialidad;
    private TextView hora;

    public AdapterCitasPacientes(Activity activity, ArrayList<CitasPaciente> citasPacientes) {
        this.activity = activity;
        this.citasPacientes = citasPacientes;
    }

    @Override
    public int getCount() {
        return citasPacientes.size();
    }
    public void clear(){
        citasPacientes.clear();
    }

    public void addAll(ArrayList<CitasPaciente> lista){
        for (int i = 0;i > lista.size();i++){
            citasPacientes.add(lista.get(i));
        }
    }
    @Override
    public Object getItem(int i) {
        return citasPacientes.get(i);
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
            v = inflater.inflate(R.layout.citaspaciente_items,null);
        }

        CitasPaciente cp = citasPacientes.get(pos);
        doctor = v.findViewById(R.id.activity_citas_paciente_doctor_label);
        doctor.setText(cp.getName_doc());
        fecha = v.findViewById(R.id.activity_citas_paciente_fecha_label);
        fecha.setText(cp.getFecha());
        hora = v.findViewById(R.id.activity_citas_paciente_hora_label);
        hora.setText(cp.getHora());
        especialidad=v.findViewById(R.id.activity_citas_paciente_especialidad_label);
        especialidad.setText(cp.getEspecialidad());
        return v;
    }
}
