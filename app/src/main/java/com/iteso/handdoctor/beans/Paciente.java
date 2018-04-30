package com.iteso.handdoctor.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by inqui on 05/04/2018.
 */

public class Paciente implements Parcelable {
    int id;
    String name;
    String lastName;
    String email;
    String phone;
    int state;


    public Paciente(String name, String lastName, String email, String phone,int state) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.state = state;
    }



    public Paciente(int id, String name, String lastName, String email, String phone) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }



    public Paciente(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Paciente(Parcel in) {
        id = in.readInt();
        name = in.readString();
        lastName = in.readString();
        email = in.readString();
        phone = in.readString();
    }

    public static final Creator<Paciente> CREATOR = new Creator<Paciente>() {
        @Override
        public Paciente createFromParcel(Parcel in) {
            return new Paciente(in);
        }

        @Override
        public Paciente[] newArray(int size) {
            return new Paciente[size];
        }
    };

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(phone);
    }
}
