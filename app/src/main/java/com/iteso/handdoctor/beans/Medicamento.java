package com.iteso.handdoctor.beans;

/**
 * Created by inqui on 23/04/2018.
 */

public class Medicamento {
    private String name;
    private int mg;
    private int cantidad;
    private int dias;

    public Medicamento(String name, int cantidad, int mg, int dias) {
        this.name = name;
        this.cantidad = cantidad;
        this.mg = mg;
        this.dias = dias;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "name='" + name + '\'' +
                ", cantidad=" + cantidad +
                ", mg='" + mg + '\'' +
                ", dias=" + dias +
                '}';
    }

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

    public int getMg() {
        return mg;
    }

    public void setMg(int mg) {
        this.mg = mg;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }


}
