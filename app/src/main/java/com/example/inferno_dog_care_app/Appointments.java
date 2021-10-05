package com.example.inferno_dog_care_app;

public class Appointments {
    String ID, DoctorName, Owner,Date;

    public Appointments(String ID, String doctorName, String owner, String date) {
        this.ID = ID;
        DoctorName = doctorName;
        Owner = owner;
        Date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
    public Appointments () {
    }
}
