package com.bridgelabz;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookServiceTest {
    @Test
    public void retrieveEntriesFromDataBaseForAddressBookTest() {
        AddressBookService addressBookService = new AddressBookService();
        List<Contact> data = addressBookService.retrieveAllEntriesFromDataBase();
        Assert.assertEquals(4, data.size());

    } @Test
    public void updateAddressBookTest() {
        AddressBookService addressBookService = new AddressBookService();
        String first_name = "Srujana";
        String phone_number = "7989216041";
        String phone_numberUpdated = addressBookService.updateAddressBook(first_name, phone_number);
        Assert.assertEquals(phone_number, phone_numberUpdated);
    }

}