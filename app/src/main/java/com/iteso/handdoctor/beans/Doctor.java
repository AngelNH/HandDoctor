package com.iteso.handdoctor.beans;

import java.util.ArrayList;

/**
 * Created by inqui on 23/04/2018.
 */

public class Doctor extends Paciente {

    private String especialidad;
    private String consultorioLat;
    private String consultorioLon;

    private ArrayList<Integer> pacientes;//check the integer part


    public Doctor(String name, String email, String phone, int estado, String especialidad, String consultorioLat, String consultorioLon) {
        super(name, email, phone, estado);
        this.especialidad = especialidad;
        this.consultorioLat = consultorioLat;
        this.consultorioLon = consultorioLon;
    }
    public Doctor(){}
    public Doctor(String name, String email, String phone, int estado) {
        super(name, email, phone, estado);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getConsultorioLat() {
        return consultorioLat;
    }

    public void setConsultorioLat(String consultorioLat) {
        this.consultorioLat = consultorioLat;
    }

    public String getConsultorioLon() {
        return consultorioLon;
    }

    public void setConsultorioLon(String consultorioLon) {
        this.consultorioLon = consultorioLon;
    }

    public ArrayList<Integer> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Integer> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + super.getName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                ", estado='" + super.getEstado() +
                ", especialidad='" + especialidad + '\'' +
                ", consultorioLat='" + consultorioLat + '\'' +
                ", consultorioLon='" + consultorioLon + '\'' +
                '}';
    }
}
