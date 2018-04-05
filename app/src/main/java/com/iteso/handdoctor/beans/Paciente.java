package com.iteso.handdoctor.beans;

/**
 * Created by inqui on 05/04/2018.
 */

public class Paciente {
    private int id;
    private String name;

    public Paciente(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
