package com.iteso.handdoctor.beans;

import java.text.SimpleDateFormat;

/**
 * Created by inqui on 12/05/2018.
 */

public class Room {
    private String nameDoc;
    private String namePac;
    private Long hour;
    private String lastMessage;



    public Room() {
    }

    public Room(String nameDoc, String namePac, Long hour, String lastMessage) {
        this.nameDoc = nameDoc;
        this.namePac = namePac;
        this.hour = hour;
        this.lastMessage = lastMessage;
    }

    public String getNameDoc() {
        return nameDoc;
    }

    public void setNameDoc(String nameDoc) {
        this.nameDoc = nameDoc;
    }

    public String getNamePac() {
        return namePac;
    }

    public void setNamePac(String namePac) {
        this.namePac = namePac;
    }

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public String getLastmessage() {
        return lastMessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastMessage = lastmessage;
    }

    @Override
    public String toString() {
        return "Room{" +
                "nameDoc='" + nameDoc + '\'' +
                ", namePac='" + namePac + '\'' +
                ", hour='" + hour + '\'' +
                ", lastmessage='" + lastMessage + '\'' +
                '}';
    }
}
