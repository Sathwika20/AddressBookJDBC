package com.bridgelabz;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookService {
    private static final String URL = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
    private static final String user = "root";
    private static final String password = "sathWIKA@20";
    public static List<Contact> contacts = new ArrayList<>();
    public static Connection connection;


    public static void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, user, password);
            Statement statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    public static List<Contact> retrieveAllEntriesFromDataBase(String sql) {
        getConnection();
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
                contacts.add(new Contact(firstName, lastName, address, city, state, zip, phone_Number, email, type, Date.valueOf("2021-03-25")));
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

    public static String updateAddressBook(String first_name, String phone_number, String sql) {
        getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update address_book set phone_number=? where first_name =?");
            preparedStatement.setString(1, phone_number);
            preparedStatement.setString(2,first_name);
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        retrieveAllEntriesFromDataBase(sql);
        for (Contact data: contacts) {
            if (data.firstName.equals(first_name))
                return data.phoneNumber;
        }
        return null;
    }
    public void addDetails(List<Contact> contactDataList) {
        contactDataList.forEach(contactData -> {
            System.out.println("Employee being added : " + contactData.firstName);
            this.addContactInAddressBookDatabase(contactData.firstName, contactData.lastName, contactData.address, contactData.city,
                    contactData.state, contactData.zip, contactData.phoneNumber, contactData.email, contactData.address_book_name, contactData.type, (Date) contactData.date);
            System.out.println("Employee added : " + contactData.firstName);
        });
    }

    private String addContactInAddressBookDatabase(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email, String address_book_name, String type, Date date) {
        getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into address_book(first_name,Last_Name,Address,City,State," +
                    "Zip,Email,AddressBook_Name,Type,start) values(?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            preparedStatement.setString(6, zip);
            preparedStatement.setString(7, email);
            preparedStatement.setString(8, address_book_name);
            preparedStatement.setString(9, type);
            preparedStatement.setDate(10, (java.sql.Date) date);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        retrieveAllEntriesFromDataBase("select * from address_book;");
        for (Contact data : contacts) {
            if (data.firstName.equals(firstName))
                return data.firstName;
        }
        return null;
    }

    public void addDetailsWithThreads(List<Contact> contactDataList) {
        Map<Integer, Boolean> conatactAdditionStatus = new HashMap<>();
        contactDataList.forEach(contactData -> {
            Runnable runnable = () -> {
                conatactAdditionStatus.put(contactData.hashCode(), false);
                System.out.println("Employee being added : " + Thread.currentThread().getName());
                this.addContactInAddressBookDatabase(contactData.firstName, contactData.lastName, contactData.address, contactData.city,
                        contactData.state, contactData.zip, contactData.phoneNumber, contactData.email,contactData.address_book_name, contactData.type, (Date) contactData.date);
                conatactAdditionStatus.put(contactData.hashCode(), true);
                System.out.println("Employee added : " + Thread.currentThread().getName());
            };
            Thread thread = new Thread(runnable, contactData.firstName);
            thread.start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        while (conatactAdditionStatus.containsValue(false)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

