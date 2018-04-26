package com.iteso.handdoctor.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.handdoctor.R;
import com.iteso.handdoctor.beans.Paciente;

import java.util.ArrayList;

/**
 * Created by inqui on 05/04/2018.
 */

public class AdapterPaciente extends BaseAdapter{

    static protected Activity activity;
    protected ArrayList<Paciente> pacientes;

    private TextView name;

    private TextView email;
    private TextView phone;

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
        return i;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        View v = view;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.expedient_item,null);
        }

        final Paciente pac = pacientes.get(pos);
        phone = v.findViewById(R.id.activity_expedient_phone);
        phone.setText(pac.getPhone());
        name = v.findViewById(R.id.activity_expedient_name);
        name.setText(pac.getName());
        email = v.findViewById(R.id.activity_expedient_email);
        email.setText(pac.getEmail());

        Log.e("HANDDOCTOR"," NAME: "+pac.getName() + "PHONE: "+ pac.getPhone());


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdapterPaciente.activity,"Mensaje de que le pico al paciente "+pac.getName(),Toast.LENGTH_LONG).show();//TODO mostrar los datos del paciente.
            }
        });
        return v;
    }
}
