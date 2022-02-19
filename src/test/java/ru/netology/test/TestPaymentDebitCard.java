package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DbInteraction;
import ru.netology.page.TripPage;
//import ru.netology.rest.RestApiHelper;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

public class TestPaymentDebitCard {

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
//    @AfterAll
//    static void cleanDB() {
//        DbInteraction.cleanDb();
//    }
}
