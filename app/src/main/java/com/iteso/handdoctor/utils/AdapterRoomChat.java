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
import com.iteso.handdoctor.beans.Medicamento;
import com.iteso.handdoctor.beans.Paciente;
import com.iteso.handdoctor.beans.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by inqui on 12/05/2018.
 */

public class AdapterRoomChat extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Room> rooms = new ArrayList<>();
    String type;

    private TextView name;
    private TextView hour;
    private TextView lastmessage;
    //private CircleImageView circleImageView;
    private ImageView imageView;

    public AdapterRoomChat(Activity activity, ArrayList<Room> rooms, String type) {
        this.activity = activity;
        this.rooms = rooms;
        this.type = type;
    }

    @Override
    public int getCount() {
        return rooms.size();
    }

    @Override
    public Object getItem(int position) {
        return rooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        Room room = rooms.get(position);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.card_view_room_chat, null);
        }
        name = v.findViewById(R.id.card_room_nombre);
        if (type.equals(""+Paciente.DOCTOR))
            name.setText(room.getNamePac());
        else  if (type.equals(""+Paciente.PACIENTE))
            name.setText(room.getNameDoc());
        hour = v.findViewById(R.id.card_room_hora);
        lastmessage = v.findViewById(R.id.card_room_mensaje);
        lastmessage.setText(room.getLastmessage());
        imageView = v.findViewById(R.id.card_img);

        Long code = room.getHour();
        Date d = new Date(code);
        SimpleDateFormat simpleDate = new SimpleDateFormat("hh:mm:ss a");
        hour.setText(simpleDate.format(d));
        return v;
    }
}
