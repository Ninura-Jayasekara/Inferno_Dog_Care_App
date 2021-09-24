package com.example.inferno_dog_care_app;

public class Veterinarian {
    String FirstName, Specialization, Address;


    public Veterinarian(){}

    public Veterinarian(String firstName, String specialization, String address) {
        FirstName = firstName;
        Specialization = specialization;
        Address = address;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
