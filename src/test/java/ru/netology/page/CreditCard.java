package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditCard {
    private SelenideElement cardNumField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = $$(".input__inner").find(text("Владелец")).$(".input__control");
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement paymentButton = $$(".button__content").find(text("Продолжить"));

    private SelenideElement paymentSuccess = $(withText("Успешно"));
    private SelenideElement paymentNoSuccess = $(withText("Ошибка"));
    private SelenideElement wrongFormatDataField = $$(".input__sub").find(Condition.text("Неверный формат"));
    private SelenideElement wrongDataLimitField = $$(".input__sub").find(Condition.text("Неверно указан срок действия карты"));
    private SelenideElement cardExpired = $$(".input__sub").find(Condition.text("Истёк срок действия карты"));
    private SelenideElement emptyField = $$(".input__sub").find(Condition.text("Поле обязательно для заполнения"));


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

    public void wrongFormatDataInField() {
        wrongFormatDataField.shouldBe(visible);
    }

    public void wrongDataLimitField() {
        wrongDataLimitField.shouldBe(visible);
    }

    public void wrongFieldCardExpired() {
        cardExpired.shouldBe(visible);
    }

    public void wrongEmptyField() {
        emptyField.shouldBe(visible);
    }

}
