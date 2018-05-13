package com.iteso.handdoctor.beans;

/**
 * Created by inqui on 19/04/2018.
 */

public class Message {
    private String name;
    private String message;
    private String type_message;
    private String urlFoto;

    public Message(){}

    public Message(String name, String message, String type_message) {
        this.name = name;
        this.message = message;
        this.type_message = type_message;
    }



    public Message(String name, String message, String type_message, String urlFoto) {
        this.name = name;
        this.message = message;
        this.type_message = type_message;
        this.urlFoto = urlFoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType_message() {
        return type_message;
    }

    public void setType_message(String type_message) {
        this.type_message = type_message;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", type_message='" + type_message + '\'' +
                '}';
    }
}
