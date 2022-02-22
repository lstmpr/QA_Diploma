package ru.netology.test;

import io.restassured.http.ContentType;
import lombok.val;
import okhttp3.MediaType;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DbInteraction;
import ru.netology.data.RestAPI;
import ru.netology.page.TripPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class TestAPI {

    @Test
    public void shouldVerifyStatusDB() {
//        val validCard = DataHelper.getCardInfoApproved();
//        String response = RestAPI.infoFromDebit(validCard);
//        assertEquals("APPROVED", response);
//    }

    }

    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:8080/")
                //.contentType(ContentType.JSON)
                .body(DataHelper.getCardInfoApproved())
                // Выполняемые действия
                .when()
                .post("/api/v1/pay")
                // Проверки
                .then()
                .statusCode(200);

    }

}
