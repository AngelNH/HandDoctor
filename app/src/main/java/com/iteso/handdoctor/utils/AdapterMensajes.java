package com.iteso.handdoctor.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.iteso.handdoctor.R;
import com.iteso.handdoctor.beans.MessageReceiver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by inqui on 12/05/2018.
 */

public class AdapterMensajes extends RecyclerView.Adapter<HolderMensaje>{
    List<MessageReceiver> listMessages = new ArrayList<>();
    private Context context;

    public AdapterMensajes(Context context) {
        this.context = context;
    }

    public void addMensaje(MessageReceiver m){
        listMessages.add(m);
        notifyItemInserted(listMessages.size());
    }

    @Override
    public HolderMensaje onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_message,parent,false);
        return new HolderMensaje(v);
    }

    @Override
    public void onBindViewHolder(HolderMensaje holder, int position) {
        holder.getNombre().setText(listMessages.get(position).getName());
        holder.getMensaje().setText(listMessages.get(position).getMessage());
        if (listMessages.get(position).getType_message().equals("2")){
            holder.getFoto().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(context).load(listMessages.get(position).getUrlFoto()).into(holder.getFoto());
        }else if (listMessages.get(position).getType_message().equals("1")){
            holder.getFoto().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }

        Long code = listMessages.get(position).getHora();
        Date d = new Date(code);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");//a is for pm or am
        holder.getHora().setText(sdf.format(d));

    }

    @Override
    public int getItemCount() {
        return listMessages.size();
    }
}
