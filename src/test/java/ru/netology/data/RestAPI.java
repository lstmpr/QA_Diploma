package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class RestAPI {
    static String appUrl = System.getProperty("app.url");

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(appUrl)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String infoFromCard(DataHelper.CardInfo cardInfo) {
        String response = given()
                .spec(requestSpec)
                .body(cardInfo)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .path("status");
        return response;

    }


}
