package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

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
        cardNumField.setValue(cardInfo.getCardNum());
        monthField.setValue(cardInfo.getMonthNum());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getOwnerCard());
        cvcField.setValue(cardInfo.getCvcCode());
        paymentButton.click();

    }

    public void successfulNotif() {
        paymentSuccess.waitUntil(visible,15000);
    }

    public void noSuccessfulNotif() {
        paymentNoSuccess.waitUntil(visible, 15000);
    }

    public void wrongDataInField() {
        wrongDataField.shouldBe(visible);
    }

}
