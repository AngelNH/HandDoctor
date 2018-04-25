package com.iteso.handdoctor.beans;


public class Medicamento {
    private String idMed;
    private String cantidad;
    private int idPaciente;
    private int diasRestantes;
    private String dosis, nombre;
    private int tipo;

    public Medicamento(String idMed, String cantidad, int idPaciente, int diasRestantes, String dosis, String nombre, int tipo) {
        this.idMed = idMed;
        this.cantidad = cantidad;
        this.idPaciente = idPaciente;
        this.diasRestantes = diasRestantes;
        this.dosis = dosis;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getIdMed() {
        return idMed;
    }

    public void setIdMed(String idMed) {
        this.idMed = idMed;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "idMed='" + idMed + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", idPaciente=" + idPaciente +
                ", diasRestantes=" + diasRestantes +
                ", dosis='" + dosis + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
