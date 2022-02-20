package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DbInteraction;
import ru.netology.page.TripPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class TestCreditCard {
    @Test
    public void shouldBeStatusApprovedInDB() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectCreditCard();
        val validCardInfo = DataHelper.getCardInfoApproved();
        PaymentCard.allCardInformation(validCardInfo);
        PaymentCard.successfulNotif();
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusCreditCard(paymentId);
        var statusExpected = "APPROVED";
        assertEquals(statusExpected, statusActual);

    }

    @Test
    public void shouldBeStatusDeclinedInDB() throws SQLException, InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoDeclined();
        PaymentCard.allCardInformation(invalidCardInfo);
        Thread.sleep(8000);
        var paymentId = DbInteraction.getPaymentId();
        var statusActual = DbInteraction.getStatusCreditCard(paymentId);

        assertEquals("DECLINED", statusActual);

    }

    @Test
    public void shouldBeSuccessfulNotificationStatusApproved() throws SQLException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectCreditCard();
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
        val PaymentCard = tripPage.selectCreditCard();
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
        val PaymentCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongNumber();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.noSuccessfulNotif();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongMonth() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongMonth();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongYear() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongYear();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();

    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongName() throws InterruptedException {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongName();
        PaymentCard.allCardInformation(invalidCardInfo);
        Thread.sleep(8000);
        PaymentCard.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulCardWithWrongCVC() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithWrongCVC();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();
    }

    @Test
    public void shouldBeNotSuccessfulEmptyField() {
        open("http://localhost:8080/");
        val tripPage = new TripPage();
        val PaymentCard = tripPage.selectCreditCard();
        val invalidCardInfo = DataHelper.getCardInfoWithEmptyFields();
        PaymentCard.allCardInformation(invalidCardInfo);
        PaymentCard.wrongDataInField();
    }
}
