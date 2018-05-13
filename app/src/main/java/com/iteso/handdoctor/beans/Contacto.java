package com.iteso.handdoctor.beans;

/**
 * Created by inqui on 13/05/2018.
 */

public class Contacto {
    String name;
    String phone;
    String chat;

    public Contacto() {
    }

    public Contacto(String name, String chat) {
        this.name = name;
        this.chat = chat;
    }

    public Contacto(String name, String phone, String chat) {
        this.name = name;
        this.phone = phone;
        this.chat = chat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "name='" + name + '\'' +
                ", chat='" + chat + '\'' +
                '}';
    }
}
