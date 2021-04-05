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

    }
}