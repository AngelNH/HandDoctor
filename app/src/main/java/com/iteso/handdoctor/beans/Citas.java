package com.iteso.handdoctor.beans;

/**
 * Created by inqui on 23/04/2018.
 */

public class Citas {
    private String id_doc;
    private String id_pac;
    private String fecha;
    private String hora;
    private String id_cita;
    private String name_pac;

    public Citas(){}

    public Citas(String id_doc, String id_pac, String fecha, String hora) {
        this.id_doc = id_doc;
        this.id_pac = id_pac;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getId_doc() {
        return id_doc;
    }

    public void setId_doc(String id_doc) {
        this.id_doc = id_doc;
    }

    public String getId_pac() {
        return id_pac;
    }

    public void setId_pac(String id_pac) {
        this.id_pac = id_pac;
    }


    public String getName_pac() {
        return name_pac;
    }

    public void setName_pac(String name_pac) {
        this.name_pac = name_pac;
    }

    @Override
    public String toString() {
        return "Citas{" +
                "id_doc='" + id_doc + '\'' +
                ", id_pac='" + id_pac + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", name='" + name_pac + '\'' +
                '}';
    }
}
