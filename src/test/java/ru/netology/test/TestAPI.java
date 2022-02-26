package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.RestAPI;

import static org.junit.jupiter.api.Assertions.*;

public class TestAPI {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldStatusDBApprovedDebit() {
        val validCard = DataHelper.getCardInfoApproved();
        String response = RestAPI.infoFromCard(validCard);
        assertEquals("APPROVED", response);
    }

    @Test
    public void shouldStatusDBDeclinedDebit() {
        val invalidCard = DataHelper.getCardInfoDeclined();
        String response = RestAPI.infoFromCard(invalidCard);
        assertEquals("DECLINED", response);
    }


}
