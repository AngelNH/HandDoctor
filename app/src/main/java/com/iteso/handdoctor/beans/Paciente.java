package com.iteso.handdoctor.beans;

import java.util.ArrayList;

/**
 * Created by inqui on 05/04/2018.
 */

public class Paciente {
    public static final int DOCTOR=0;
    public static final int SECRETARIA=1;
    public static final int PACIENTE=2;


    private String name;
    private String email;
    private String phone;
    private int estado;

    private boolean isLogged;
    private ArrayList<String> citas;

    public Paciente(){

    }
    public Paciente(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public Paciente(String name, String email, String phone, int estado) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.estado = estado;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public ArrayList<String> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<String> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", estado='" + estado +
                '}';
    }
}
