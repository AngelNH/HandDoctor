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
    private String name_doc;
    private String motive;

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


    public String getName_doc() {
        return name_doc;
    }

    public void setName_doc(String name_doc) {
        this.name_doc = name_doc;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    @Override
    public String toString() {
        return "Citas{" +
                "id_doc='" + id_doc + '\'' +
                ", id_pac='" + id_pac + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", name='" + name_doc + '\'' +
                ", motive='" + motive + '\'' +
                '}';
    }
}
