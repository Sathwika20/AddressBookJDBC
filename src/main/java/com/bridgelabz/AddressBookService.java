package com.bridgelabz;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookService {
    private static final String URL = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
    private static final String user = "root";
    private static final String password = "sathWIKA@20";
    public static List<Contact> contacts = new ArrayList<>();

    public static List<Contact> retrieveAllEntriesFromDataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    URL, user, password);
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from address_book_service;");
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String address = resultSet.getString("Address");
                String city  = resultSet.getString("City");
                String state = resultSet.getString("State");
                String zip  = resultSet.getString("Zip");
                String phone_Number = resultSet.getString("Phone_Number");
                String email = resultSet.getString("Email");
                String type = resultSet.getString("Type");
                contacts.add(new Contact(firstName, lastName, address, city, state, zip, phone_Number, email, type));
            }
            contacts.forEach(data -> System.out.println(data.firstName
                    +" "+data.lastName+" "+data.city+" "+data.state+" "+data.zip+" "+ data.phoneNumber+" "+ data.email));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return contacts;
    }

}
