package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentCard {
    private SelenideElement cardNumField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = $$(".input__inner").find(Condition.text("Владелец")).$(".input__control");
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement paymentButton = $$(".button__content").find(Condition.text("Продолжить"));
    private SelenideElement paymentSuccess = $(".notification_status_ok");
    private SelenideElement paymentNoSuccess = $(".notification_status_error");
    private SelenideElement wrongDataField = $(".input__sub");


    public void allCardInformation(DataHelper.CardInfo cardInfo) {
        cardNumField.setValue(cardInfo.getNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getHolder());
        cvcField.setValue(cardInfo.getCvc());
        paymentButton.click();

    }

    public void successfulNotif() {
        paymentSuccess.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void noSuccessfulNotif() {
        paymentNoSuccess.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void wrongDataInField() {
        wrongDataField.shouldBe(visible);
    }

}
