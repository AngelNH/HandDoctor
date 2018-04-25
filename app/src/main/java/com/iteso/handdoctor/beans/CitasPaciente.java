package com.iteso.handdoctor.beans;

public class CitasPaciente {
    private int idPaciente;
    private String nombreDoctor;
    private String fecha;
    private long lat;
    private long lon;
    private String especialidad;
    private String hora;

    public CitasPaciente(int idPaciente, String nombreDoctor, String fecha, long lat, long lon, String especialidad, String hora) {
        this.idPaciente = idPaciente;
        this.nombreDoctor = nombreDoctor;
        this.fecha = fecha;
        this.lat = lat;
        this.lon = lon;
        this.especialidad = especialidad;
        this.hora = hora;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
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

    @Override
    public String toString() {
        return "CitasPaciente{" +
                "idPaciente=" + idPaciente +
                ", nombreDoctor='" + nombreDoctor + '\'' +
                ", fecha='" + fecha + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", especialidad='" + especialidad + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}