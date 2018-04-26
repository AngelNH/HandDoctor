package com.iteso.handdoctor.beans;

/**
 * Created by inqui on 23/04/2018.
 */

public class Medicamento {
    public static final int MEDICAMENTO_PASTILLA=0;
    public static final int MEDICAMENTO_JARABE=1;
    public static final int MEDICAMENTO_INYECCION=2;



    private String nombre;
    private String cantidad;//mg o ml.
    private String dosis;
    private int diasRestantes;
    private int tipoMedicamento;

    private String idPaciente;

    public Medicamento(String nombre, String cantidad, String dosis, int diasRestantes, int tipoMedicamento) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.dosis = dosis;
        this.diasRestantes = diasRestantes;
        this.tipoMedicamento = tipoMedicamento;
    }

    public Medicamento(String nombre, String cantidad, String dosis, int diasRestantes, int tipoMedicamento, String idPaciente) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.dosis = dosis;
        this.diasRestantes = diasRestantes;
        this.tipoMedicamento = tipoMedicamento;
        this.idPaciente = idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public int getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public int getTipoMedicamento() {
        return tipoMedicamento;
    }


    public void setTipoMedicamento(int tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }
    @Override
    public String toString() {
        return "Medicamento{" +
                "nombre='" + nombre + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", dosis='" + dosis + '\'' +
                ", diasRestantes=" + diasRestantes +
                ", tipoMedicamento=" + tipoMedicamento +
                ", idPaciente=" + idPaciente + '\'' +
                '}';
    }

}
