package com.example.inferno_dog_care_app;

//Veterinarian Model
//IT20175498

public class Veterinarian {

    //Variables

    String ID, Name,Gender, Address, Phone, RegistrationNo,Email, Specialization;


    //Getters and Setter
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getRegistrationNo() {
        return RegistrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        RegistrationNo = registrationNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }


    //Constructor

    public Veterinarian(String ID, String name, String gender, String address, String phone, String registrationNo, String email, String specialization) {
        this.ID = ID;
        Name = name;
        Gender = gender;
        Address = address;
        Phone = phone;
        RegistrationNo = registrationNo;
        Email = email;
        Specialization = specialization;
    }

    public Veterinarian() {
    }

   }