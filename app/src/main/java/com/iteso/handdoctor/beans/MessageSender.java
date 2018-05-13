package com.iteso.handdoctor.beans;

import com.iteso.handdoctor.beans.Message;

import java.util.Map;

/**
 * Created by inqui on 12/05/2018.
 */

public class MessageSender extends Message {
    private Map hora;

    public MessageSender(){
    }

    public MessageSender(Map hora) {
        this.hora = hora;
    }

    public MessageSender(String name, String message, String type_message, String urlFoto, Map hora) {
        super(name, message, type_message, urlFoto);
        this.hora = hora;
    }

    public MessageSender(String name, String message, String type_message, Map hora) {
        super(name, message, type_message);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
