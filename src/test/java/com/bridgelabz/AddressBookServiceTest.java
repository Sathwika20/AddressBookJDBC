package com.bridgelabz;


import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class AddressBookServiceTest {
    @Test
    public void retrieveEntriesFromDataBaseForAddressBookTest(String sql) {
        AddressBookService addressBookService = new AddressBookService();
        List<Contact> data = addressBookService.retrieveAllEntriesFromDataBase(sql);
        Assert.assertEquals(4, data.size());

    }
    @Test
    public void updateAddressBookTest(String sql) {
        AddressBookService addressBookService = new AddressBookService();
        String first_name = "Srujana";
        String phone_number = "7989216041";
        String phone_numberUpdated = addressBookService.updateAddressBook(first_name, phone_number, sql);
        Assert.assertEquals(phone_number, phone_numberUpdated);
    }
    @Test
    public void retrieveEntryByDateTest() {
        AddressBookService addressBookService = new AddressBookService();
        String sql = "SELECT * FROM address_book WHERE start BETWEEN CAST('2016,-01-01' AS DATE) AND DATE(NOW());";
        List<Contact> data = addressBookService.retrieveAllEntriesFromDataBase(sql);
        Assert.assertEquals(2, data.size());
    }
    @Test
    public void retrieveEntryByStateOrCityTest() {
        AddressBookService addressBookService = new AddressBookService();
        AddressBookService addressBookService2 = new AddressBookService();
        String sql1 = "select * from address_book where state = 'Telangana';";
        String sql2 = "select * from address_book where city = 'Hyderabad';";
        List<Contact> dataByState =addressBookService.retrieveAllEntriesFromDataBase(sql1);
        List<Contact> dataByCity = addressBookService2.retrieveAllEntriesFromDataBase(sql2);
        Assert.assertEquals(1, dataByState.size());
        Assert.assertEquals(3, dataByCity.size());
    }
    @Test
    public void addContactInAddressBookDatabaseTest() {
        AddressBookService addressBookService = new AddressBookService();
        String first_name = "Sita";
        String updated_name = addressBookService.addContactInAddressBookDatabase(first_name,"Sita", "Uppal", "Hyderabad", "Telangana",
                502888, "sita@.gmail.com", "FellowShip","Friend", Date.valueOf("2021-01-01"));
        Assert.assertEquals(first_name,updated_name);
    }


}