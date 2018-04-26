package com.iteso.handdoctor.beans;

/**
 * Created by inqui on 24/04/2018.
 */

public class Expediente {
    public static final int GENDER_FEMALE = 0;
    public static final int GENDER_MALE = 1;
    public static final int CIVIL_SINGLE = 0;
    public static final int CIVIL_MARRIED = 1;
    public static final int CIVIL_DIVORCED = 2;

    private String id;
    private int gender;
    private String age;
    private String direction;
    private String nationality;
    private String religion;
    private int civil;
    private String actualMedication;
    private String chronic;
    private String weight;
    private String height;
    private String blood;
    private String actualDisease;
    private String tension;

    public Expediente(){}
    public Expediente(String id, int gender, String age, String direction, String nationality, String religion, int civil, String actualMedication, String chronic, String weight, String height, String blood, String actualDisease, String tension) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.direction = direction;
        this.nationality = nationality;
        this.religion = religion;
        this.civil = civil;
        this.actualMedication = actualMedication;
        this.chronic = chronic;
        this.weight = weight;
        this.height = height;
        this.blood = blood;
        this.actualDisease = actualDisease;
        this.tension = tension;
    }

    @Override
    public String toString() {
        return "Expediente{" +
                "id='" + id + '\'' +
                ", gender=" + gender +
                ", age='" + age + '\'' +
                ", direction='" + direction + '\'' +
                ", nationality='" + nationality + '\'' +
                ", religion='" + religion + '\'' +
                ", civil=" + civil +
                ", actualMedication='" + actualMedication + '\'' +
                ", chronic='" + chronic + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", blood='" + blood + '\'' +
                ", actualDisease='" + actualDisease + '\'' +
                ", tension='" + tension + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public int getCivil() {
        return civil;
    }

    public void setCivil(int civil) {
        this.civil = civil;
    }

    public String getActualMedication() {
        return actualMedication;
    }

    public void setActualMedication(String actualMedication) {
        this.actualMedication = actualMedication;
    }

    public String getChronic() {
        return chronic;
    }

    public void setChronic(String chronic) {
        this.chronic = chronic;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getActualDisease() {
        return actualDisease;
    }

    public void setActualDisease(String actualDisease) {
        this.actualDisease = actualDisease;
    }

    public String getTension() {
        return tension;
    }

    public void setTension(String tension) {
        this.tension = tension;
    }

}
