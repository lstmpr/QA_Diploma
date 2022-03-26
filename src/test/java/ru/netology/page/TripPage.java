package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TripPage {

    private SelenideElement bueCardButton = $(byText("Купить"));
    private SelenideElement bueCreditCardButton = $(byText("Купить в кредит"));
    private SelenideElement fieldPayment = $("#root > div >h3");

    public CreditCard selectDebitCard() {
        bueCardButton.click();
        fieldPayment.shouldHave(exactText("Оплата по карте"));
        return new CreditCard();
    }

    public CreditCard selectCreditCard() {
        bueCreditCardButton.click();
        fieldPayment.shouldHave(exactText("Кредит по данным карты"));
        return new CreditCard();
    }
}

