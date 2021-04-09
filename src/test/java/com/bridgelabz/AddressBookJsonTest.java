package com.bridgelabz;

import com.mysql.fabric.Response;
import jdk.jfr.ContentType;
import org.junit.Assert;
import org.junit.Test;

public class AddressBookJsonTest {
    @Test
    public void testToRetrieveAllAddressBookData() {
        Response response = RestAssured.get("http://localhost:3000/addressBook");
        System.out.println(response.asString());
        Assert.assertEquals(200, response.getStatusCode());
    }
    @Test
    public void testToContactDataInJSONServer(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"firstName\": \"Sathwika\",\"lastName\": \"Gopiraj\",\"address\": \"HarithaEnclave\",\"city\": \"Kamareddy\",\"state\": \"Telangana\",\"zip\": \"503111\",\"phoneNumber\": \"7036101828\",\"email\": \"sathwika@gmail.com\",\"personType\": \"Friend\"}")
                .when().post("http://localhost:3000/addressbook");
        response.then()
                .body("firstName", Matchers.is("Sathwika"));
        Assert.assertEquals(201, response.getStatusCode());
    }
}
