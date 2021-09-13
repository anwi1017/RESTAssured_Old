package org.ra;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssured1Test {

    public static void main (String[] s) {

        //create base path
        RestAssured.baseURI="https://reqres.in";

        //create a request (blank)
        RequestSpecification request = RestAssured.given();

        //setup and executing request
        request.get("/api/users").then().log().all();

    }
}
