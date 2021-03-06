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
                .body("firstName", Matchers.is("Sathwika "));
        Assert.assertEquals(201, response.getStatusCode());
    }
    @Test
    public void testToUpdateContactDataInJSONServer(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"firstName\": \"Srujana\",\"lastName\": \"Kulkarni\",\"address\": \"AshokNagar\",\"city\": \"Hyderabad\",\"state\": \"Telangana\",\"zip\": \"502888\",\"phoneNumber\": \"9245647823\",\"email\": \"srujana@gmail.com\",\"personType\": \"Friend\"}")
                .when().put("http://localhost:3000/addressbook/4");
        response.then()
                .body("address", Matchers.is("AshokNagar"));
        Assert.assertEquals(200, response.getStatusCode());
    }
}
