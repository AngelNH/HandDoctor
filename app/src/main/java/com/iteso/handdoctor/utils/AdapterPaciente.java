package com.iteso.handdoctor.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iteso.handdoctor.R;
import com.iteso.handdoctor.beans.Paciente;

import java.util.ArrayList;

/**
 * Created by inqui on 05/04/2018.
 */

public class AdapterPaciente extends BaseAdapter{

    protected Activity activity;
    protected ArrayList<Paciente> pacientes;

    private TextView name;
    private TextView expedient;

    public AdapterPaciente(Activity activity, ArrayList<Paciente> pacientes){
        this.activity = activity;
        this.pacientes=pacientes;
    }
    @Override
    public int getCount() {
        return pacientes.size();
    }
    public void clear(){
        pacientes.clear();
    }

    public void addAll(ArrayList<Paciente> it){
        for (int i = 0;i > it.size();i++){
            pacientes.add(it.get(i));
        }
    }
    @Override
    public Object getItem(int i) {
        return pacientes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return pacientes.get(i).getId();
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        View v = view;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.expedient_item,null);
        }

        Paciente pac = pacientes.get(pos);
        expedient = v.findViewById(R.id.activity_expedient_number);
        Log.e("HANDDOCTOR","ID: "+ pac.getId()+" NAME: "+pac.getName());
        expedient.setText(pac.getId()+"");
        name = v.findViewById(R.id.activity_expedient_name);
        name.setText(pac.getName());


        return v;
    }
}
