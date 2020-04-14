package com.example.companionapp.Resources;

public class Building {
    private String name;
    private String description;
    private String address;
    private String postalCode;
    private String city;

    public Building(String name, String description, String address, String postalCode, String city) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String ToString(){
        return name + System.lineSeparator() + description + System.lineSeparator() + address + " " + postalCode + System.lineSeparator() + "City: " + city;
    }
}
