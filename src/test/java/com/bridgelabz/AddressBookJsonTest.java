package com.bridgelabz;

import com.mysql.fabric.Response;
import org.junit.Assert;
import org.junit.Test;

public class AddressBookJsonTest {
    @Test
    public void testToRetrieveAllAddressBookData() {
        Response response = RestAssured.get("http://localhost:3000/addressBook");
        System.out.println(response.asString());
        Assert.assertEquals(200, response.getStatusCode());
    }
}
