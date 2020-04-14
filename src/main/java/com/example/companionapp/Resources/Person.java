package com.example.companionapp.Resources;

public class Person {
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String office;

    private String displayName;
    private String title;
    private String photoURL;
    private String personalTitle;

    public Person(String id, String first_name, String last_name, String email, String office) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.office = office;
    }

    public Person(String id, String displayName, String email, String title, String personalTitle, String photoURL){
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.title = title;
        this.personalTitle = personalTitle;
        this.photoURL = photoURL;
    }

    public String personToString() {
        return first_name + " " + last_name + System.lineSeparator() + email + System.lineSeparator() + office;
    }

    public String profileToString(){
        return id + "@fhict.nl" + System.lineSeparator() + displayName + System.lineSeparator() + email + System.lineSeparator() + title + " | " + personalTitle;
    }

    public String GetPhotoURL(){
        return photoURL;
    }
}
