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
    public void shouldBeStatusApprovedInDB() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val validCardInfo = DataHelper.getCardInfoApproved();
        PaymentCard.allCardInformation(validCardInfo);
        PaymentCard.successfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusDebitCard(paymentId);
        var statusExpected = "APPROVED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeStatusDeclinedInDB() throws SQLException, InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoDeclined();
        PaymentCard.allCardInformation(invalidCardInfo);
        Thread.sleep(8000);
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusDebitCard(paymentId);

        assertEquals("DECLINED", statusActual);

    }

    @Test
    public void shouldBeSuccessfulNotificationStatusApproved() throws SQLException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val validCardInfo = DataHelper.getCardInfoShouldBeOk();
        PaymentCard.allCardInformation(validCardInfo);
        PaymentCard.successfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusDebitCard(paymentId);
        var statusExpected = "APPROVED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeNotSuccessfulNotificationStatusDeclined() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoDeclined();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.noSuccessfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusDebitCard(paymentId);
        var statusExpected = "DECLINED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongNumber() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongNumber();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.noSuccessfulNotif();

    }


    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatNumber() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongFormatNumberCardField();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongMonth() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongMonth();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatMonth() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoMonthWithWrongFormat();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulWrongMonthWithDoubleZero() throws InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoMonthFieldWithDoubleZero();
        PaymentCard.allCardInformation(invalidCardInfo);
        Thread.sleep(8000);
        PaymentCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongYear() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongYear();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatYear() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongFormatYear();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatYearWithYear() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongFormatYearWithZero();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongName() throws InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongName();
        PaymentCard.allCardInformation(invalidCardInfo);
        Thread.sleep(8000);
        PaymentCard.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatName() throws InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongFormatName();
        PaymentCard.allCardInformation(invalidCardInfo);
        Thread.sleep(8000);
        PaymentCard.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongCVC() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongCVC();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongFormatCVC() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongFormatCVC();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();
    }


    @Test
    public void shouldBeNotSuccessfulEmptyField() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectDebitCard();
        val invalidCardInfo = DataHelper.getCardInfoWithEmptyFields();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();
    }
}
