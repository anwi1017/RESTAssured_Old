package org.ra;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RestAssured2Test {
    @Before
    public void before() {
        //create base path
        RestAssured.baseURI="https://reqres.in";
    }

    @Test
    public void getRequest(){

        //create a request (blank)
        RequestSpecification request = RestAssured.given();

        //setup and executing request
        request.get("/api/users").then().log().all();

    }

    @Test
    public void getRequest2(){

        //create a request (blank)
        RequestSpecification request = RestAssured.given();

        //setup and executing request
        request.get("/api/users?page=2").then().log().headers();

        //request.get("/api/users?page=2").then().log().body();

    }

    @Test
    public void getRequest3(){

        //create a request (blank)
        RequestSpecification request = RestAssured.given();

        //setup and executing request
        //request.get("/api/users?page=2").then().log().headers();

        String respBody = request.get("/api/users?page=2").getBody().asString();

        System.out.println("response Body ="+respBody);

    }

    @Test
    public void postRequest(){

        //create a request (blank)
        RequestSpecification request = RestAssured.given();

        //setup and executing request
        String requestBody = " {\"name\": \"morpheus\", \"job\": \"leader\"}";

        request.header("content-type","application/json")
                .body(requestBody)
                .log().all()
                .post("/api/users").then().log().all();

    }

    @Test
    public void getRequest3Assert() {

        // create a request  (blank)
        RequestSpecification request= RestAssured.given();


        //setup and executing request
        Response response =request.get("/api/users?page=2");

        JsonPath jp =response.jsonPath();

        Assert.assertEquals("michael.lawson@reqres.com", jp.getString("data[0].email"));

    }

    @Test
    public void soapRequest() {

        String payload ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
                + "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
                + "  <soap:Body>\r\n"
                + "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
                + "      <ubiNum>1002</ubiNum>\r\n"
                + "    </NumberToWords>\r\n"
                + "  </soap:Body>\r\n"
                + "</soap:Envelope>";


        // create a request  (blank)
        RequestSpecification request= RestAssured.given();


        //Content-Type: text/xml; charset=utf-8
        request
                .header("Content-Type","text/xml; charset=utf-8")
                .and().body(payload)
                .when().post("/webservicesserver/NumberConversion.wso")
                .then().log().body();



    }
}
