package com.iteso.handdoctor.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.handdoctor.R;
import com.iteso.handdoctor.beans.Contacto;

import java.util.ArrayList;

/**
 * Created by inqui on 13/05/2018.
 */

public class AdapterContactos extends BaseAdapter {
   private ArrayList<Contacto> contactos= new ArrayList<>();
   private Activity activity;

   TextView name;
   ImageView image;

    public AdapterContactos( Activity activity,ArrayList<Contacto> contactos) {
        this.contactos = contactos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.contact_item, null);
        }
        name = v.findViewById(R.id.contact_item_contact_name);
        name.setText(contactos.get(position).getName());
        image = v.findViewById(R.id.contact_item_contact_image);
        return v;
    }
}
