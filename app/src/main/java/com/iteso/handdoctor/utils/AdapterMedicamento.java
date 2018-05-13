package com.iteso.handdoctor.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iteso.handdoctor.R;
import com.iteso.handdoctor.beans.Medicamento;
import com.iteso.handdoctor.beans.Paciente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by inqui on 23/04/2018.
 */

public class AdapterMedicamento  extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Medicamento> medicamentos;

    private TextView name;
    private TextView miligramos;
    private TextView cantidad;
    private TextView dias;

    private ImageView image;


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
        Medicamento med = medicamentos.get(pos);
        View v = view;

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.medicamentos_items, null);
            }

            name = v.findViewById(R.id.activity_medicamento_medicamento_label);
            name.setText(med.getNombre());
            miligramos = v.findViewById(R.id.activity_medicamento_miligramos_label);
            miligramos.setText("" + med.getCantidad());
            cantidad = v.findViewById(R.id.activity_medicamento_cantidad_label);
            cantidad.setText("" + med.getDosis());
            dias = v.findViewById(R.id.activity_medicamento_dias_label);
            dias.setText("" + med.getDiasRestantes());
            //dias.setText("" + days);
            image = v.findViewById(R.id.activity_medicamento_med_image);
            switch (med.getTipoMedicamento()) {
                case Medicamento.MEDICAMENTO_JARABE:
                    image.setImageResource(R.drawable.jarabe_icon);
                    break;
                case Medicamento.MEDICAMENTO_INYECCION:
                    image.setImageResource(R.drawable.inyeccion_icon);
                    break;
                case Medicamento.MEDICAMENTO_PASTILLA:
                    image.setImageResource(R.drawable.cap_icon);
                    break;
            }

        return v;
    }


}
