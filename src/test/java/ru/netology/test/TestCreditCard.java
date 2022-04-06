package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DbInteraction;
import ru.netology.page.TripPage;

import java.sql.SQLException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class TestCreditCard {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldBeStatusApprovedInDB() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val creditCardPage = tripPage.selectCreditCard();
        val validCardInfo = DataHelper.getCardInfoApproved();
        creditCardPage.allCardInformation(validCardInfo);
        creditCardPage.successfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusCreditCard(paymentId);
        var statusExpected = "APPROVED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeStatusDeclinedInDB() throws SQLException, InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val CreditCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoDeclined();
        CreditCard.allCardInformation(invalidCardInfo);
        Duration.ofSeconds(8000);
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusCreditCard(paymentId);

        assertEquals("DECLINED", statusActual);

    }

    @Test
    public void shouldBeSuccessfulNotificationStatusApproved() throws SQLException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val creditCardPage = tripPage.selectCreditCard();
        val validCardInfo = DataHelper.getCardInfoShouldBeOk();
        creditCardPage.allCardInformation(validCardInfo);
        creditCardPage.successfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusDebitCard(paymentId);
        var statusExpected = "APPROVED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeNotSuccessfulNotificationStatusDeclined() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val creditCardPage = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoDeclined();
        creditCardPage.allCardInformation(invalidCardInfo);
        creditCardPage.noSuccessfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusDebitCard(paymentId);
        var statusExpected = "DECLINED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongNumber() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val creditCardPage = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongNumber();
        creditCardPage.allCardInformation(invalidCardInfo);
        creditCardPage.noSuccessfulNotif();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongMonth() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val creditCardPage = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongMonth();
        creditCardPage.allCardInformation(invalidCardInfo);
        creditCardPage.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongYear() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val creditCardPage = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongYear();
        creditCardPage.allCardInformation(invalidCardInfo);
        creditCardPage.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongName() throws InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val creditCardPage = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongName();
        creditCardPage.allCardInformation(invalidCardInfo);
        Duration.ofSeconds(8000);
        creditCardPage.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongCVC() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val creditCardPage = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongCVC();
        creditCardPage.allCardInformation(invalidCardInfo);
        creditCardPage.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulEmptyField() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val creditCardPage = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithEmptyFields();
        creditCardPage.allCardInformation(invalidCardInfo);
        creditCardPage.wrongDataInField();
    }
}
