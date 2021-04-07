package com.bridgelabz;


import org.junit.Assert;
import org.junit.Test;

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


}