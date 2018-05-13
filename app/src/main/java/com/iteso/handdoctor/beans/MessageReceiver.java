package com.iteso.handdoctor.beans;

import com.iteso.handdoctor.beans.Message;

/**
 * Created by inqui on 12/05/2018.
 */

public class MessageReceiver extends Message {
    private Long hora;

    public MessageReceiver() {
    }

    public MessageReceiver(Long hora) {
        this.hora = hora;
    }

    public MessageReceiver(String name, String message, String type_message, String urlFoto, Long hora) {
        super(name, message, type_message, urlFoto);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
