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

public class TestDebitCard {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldBeSuccessfulNotificationStatusApproved() throws SQLException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val validCardInfo = DataHelper.getCardInfoShouldBeOk();
        paymentCardPage.allCardInformation(validCardInfo);
        paymentCardPage.successfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusDebitCard(paymentId);
        var statusExpected = "APPROVED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeNotSuccessfulNotificationStatusDeclined() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoDeclined();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.noSuccessfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusDebitCard(paymentId);
        var statusExpected = "DECLINED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongNumber() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongNumber();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.noSuccessfulNotif();

    }


    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatNumber() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongFormatNumberCardField();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongMonth() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongMonth();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatMonth() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoMonthWithWrongFormat();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulWrongMonthWithDoubleZero() throws InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoMonthFieldWithDoubleZero();
        paymentCardPage.allCardInformation(invalidCardInfo);
        Duration.ofSeconds(8000);
        paymentCardPage.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongYear() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongYear();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatYear() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongFormatYear();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatYearWithYear() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongFormatYearWithZero();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongName() throws InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongName();
        paymentCardPage.allCardInformation(invalidCardInfo);
        Duration.ofSeconds(8000);
        paymentCardPage.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatName() throws InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongFormatName();
        paymentCardPage.allCardInformation(invalidCardInfo);
        Duration.ofSeconds(8000);
        paymentCardPage.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongCVC() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongCVC();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatCVC() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongFormatCVC();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.wrongDataInField();
    }


    @Test
    public void shouldBeNotSuccessfulEmptyField() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val paymentCardPage = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithEmptyFields();
        paymentCardPage.allCardInformation(invalidCardInfo);
        paymentCardPage.wrongDataInField();
    }
}
