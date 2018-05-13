package com.iteso.handdoctor.utils;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.handdoctor.R;

/**
 * Created by inqui on 12/05/2018.
 */

public class HolderMensaje extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView mensaje;
    private TextView hora;
    private ImageView foto;

    private CardView card;

    public HolderMensaje(View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.card_nombre);
        mensaje = itemView.findViewById(R.id.card_mensaje);
        hora = itemView.findViewById(R.id.card_hora);
        foto = itemView.findViewById(R.id.card_mensaje_foto);

        card = itemView.findViewById(R.id.card_view_message);
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }




    public CardView getCard() {
        return card;
    }

    public void setCard(CardView card) {
        this.card = card;
    }
}
