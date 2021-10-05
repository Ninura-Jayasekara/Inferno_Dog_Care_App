package com.example.inferno_dog_care_app;

public class Advertisement {
    String ID,Owner_Name,Phone_Number, Address,Breed,No_Of_Puppies,Birthday,Price,imageURL;;

    public Advertisement(){

    }

    public Advertisement(String ID, String owner_Name, String phone_Number, String address, String breed, String no_Of_Puppies, String birthday, String price, String imageURL) {
        this.ID = ID;
        Owner_Name = owner_Name;
        Phone_Number = phone_Number;
        Address = address;
        Breed = breed;
        No_Of_Puppies = no_Of_Puppies;
        Birthday = birthday;
        Price = price;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOwner_Name() {
        return Owner_Name;
    }

    public void setOwner_Name(String owner_Name) {
        Owner_Name = owner_Name;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getNo_Of_Puppies() {
        return No_Of_Puppies;
    }

    public void setNo_Of_Puppies(String no_Of_Puppies) {
        No_Of_Puppies = no_Of_Puppies;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
