package com.example.inferno_dog_care_app;

//IT20175498
//Model for appointment

public class AppointmentModel {
    String ID, DoctorName, Date, Owner, Phone;
    private static AppointmentModel instance;


    //Constructor
    public AppointmentModel(String ID, String doctorName, String date, String owner, String phone) {
        this.ID = ID;
        DoctorName = doctorName;
        Date = date;
        Owner = owner;
        Phone = phone;
    }

    //Getters and setters

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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }


    //instance

    public static AppointmentModel getInstance() {
        if(instance == null){
            instance = new AppointmentModel();
        }
        return instance;
    }

    public AppointmentModel(){}
}
