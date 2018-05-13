package com.iteso.handdoctor.beans;

/**
 * Created by leo_c on 19/04/2018.
 */

public class Medicamentos {
    private String name;
    private int cantidad;
    private String mg;
    private int dias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMg() {
        return mg;
    }

    public void setMg(String mg) {
        this.mg = mg;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public Medicamentos(String name, int cantidad, String mg, int dias) {
        this.name = name;
        this.cantidad = cantidad;
        this.mg = mg;
        this.dias = dias;
    }
}
