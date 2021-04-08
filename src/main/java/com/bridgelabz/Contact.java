package com.bridgelabz;

import java.sql.Date;

public class Contact {

    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String state;
    public String zip;
    public String phoneNumber;
    public String email;
    public String type;
    public String address_book_name;
    public Date date;

    public Contact(String firstName, String lastName, String address, String city, String state, String zip, String phone_number, String email, String type, Date date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phone_number;
        this.email = email;
        this.type = type;
        this.address_book_name = address_book_name;
        this.date = this.date;

    }
}
