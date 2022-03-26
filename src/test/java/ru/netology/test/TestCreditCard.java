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
        val CreditCard = tripPage.selectCreditCard();
        val validCardInfo = DataHelper.getCardInfoApproved();
        CreditCard.allCardInformation(validCardInfo);
        CreditCard.successfulNotif();
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
        val CreditCard = tripPage.selectCreditCard();
        val validCardInfo = DataHelper.getCardInfoShouldBeOk();
        CreditCard.allCardInformation(validCardInfo);
        CreditCard.successfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusDebitCard(paymentId);
        var statusExpected = "APPROVED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeNotSuccessfulNotificationStatusDeclined() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val CreditCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoDeclined();
        CreditCard.allCardInformation(invalidCardInfo);
        CreditCard.noSuccessfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusDebitCard(paymentId);
        var statusExpected = "DECLINED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongNumber() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val CreditCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongNumber();
        CreditCard.allCardInformation(invalidCardInfo);
        CreditCard.noSuccessfulNotif();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongMonth() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val CreditCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongMonth();
        CreditCard.allCardInformation(invalidCardInfo);
        CreditCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongYear() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val CreditCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongYear();
        CreditCard.allCardInformation(invalidCardInfo);
        CreditCard.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongName() throws InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val CreditCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongName();
        CreditCard.allCardInformation(invalidCardInfo);
        Duration.ofSeconds(8000);
        CreditCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongCVC() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val CreditCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongCVC();
        CreditCard.allCardInformation(invalidCardInfo);
        CreditCard.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulEmptyField() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val CreditCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithEmptyFields();
        CreditCard.allCardInformation(invalidCardInfo);
        CreditCard.wrongDataInField();
    }
}
