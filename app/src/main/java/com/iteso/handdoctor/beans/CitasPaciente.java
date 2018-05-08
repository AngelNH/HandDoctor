package com.iteso.handdoctor.beans;

/**
 * Created by inqui on 23/04/2018.
 */
public class CitasPaciente extends Citas{
    private String especialidad;
    private String consultorioLat;
    private String consultorioLon;

    /*
    public CitasPaciente(String nombreDoctor, String fecha, String consultorioLat, String consultorioLon, String especialidad, String hora) {
        //super(nombreDoctor,fecha,hora);
        this.consultorioLat = consultorioLat;
        this.consultorioLon = consultorioLon;
        this.especialidad = especialidad;
    }
    */

    public CitasPaciente(String id_doc, String id_pac, String fecha, String hora, String especialidad, String consultorioLat, String consultorioLon) {
        super(id_doc, id_pac, fecha, hora);
        this.especialidad = especialidad;
        this.consultorioLat = consultorioLat;
        this.consultorioLon = consultorioLon;
    }

    @Override
    public String toString() {
        return "CitasPaciente{" +
                "id_doc='" + super.getId_doc() + '\'' +
                ", fecha=" + super.getFecha() +
                ", consultorioLat='" + consultorioLat + '\'' +
                ", consultorioLon='" + consultorioLon + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", hora='" + super.getHora() + '\'' +
                '}';
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}