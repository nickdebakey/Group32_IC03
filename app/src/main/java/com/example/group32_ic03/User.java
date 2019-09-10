package com.example.group32_ic03;

import java.io.Serializable;

public class User implements Serializable {
    String gender;
    String firstName;
    String lastName;

    User() {

    }

    public String getGender () {
        return gender;
    }

    public void setGender (String gender){
        this.gender = gender;
    }

    public User(String gender, String firstName, String lastName) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // add the edit texts here

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return  firstName + lastName + "\n" + gender;
    }
}
