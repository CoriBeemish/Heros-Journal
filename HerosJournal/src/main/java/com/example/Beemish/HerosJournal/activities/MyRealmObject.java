package com.example.Beemish.HerosJournal.activities;

//Written by Alex Helfrich

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MyRealmObject extends RealmObject {

    private String FirstName, LastName;
    @PrimaryKey
    private String Email;
    private String Password;

    public MyRealmObject() {
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
