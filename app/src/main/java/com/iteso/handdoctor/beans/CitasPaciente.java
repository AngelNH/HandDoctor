package com.iteso.handdoctor.beans;

import java.util.Date;

public class CitasPaciente {
    private String nombreDoctor;
    private String fecha;
    private String lugar;
    private String especialidad;
    private String hora;

    public CitasPaciente(String nombreDoctor, String fecha, String lugar, String especialidad, String hora) {
        this.nombreDoctor = nombreDoctor;
        this.fecha = fecha;
        this.lugar = lugar;
        this.especialidad = especialidad;
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "CitasPaciente{" +
                "nombreDoctor='" + nombreDoctor + '\'' +
                ", fecha=" + fecha +
                ", lugar='" + lugar + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}