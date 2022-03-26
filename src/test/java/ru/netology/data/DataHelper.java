package ru.netology.data;

import lombok.Value;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {

    }

    static String generateDate(int plusMonth, String formatPattern) {
        return LocalDate.now().plusMonths(plusMonth).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    @Value
    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String holder;
        private String cvc;
    }


    public static CardInfo getCardInfoApproved() {
        return new CardInfo("4444 4444 4444 4441", generateDate(3, "MM"), generateDate(24, "yy"), faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoDeclined() {
        return new CardInfo("4444 4444 4444 4442", generateDate(3, "MM"), generateDate(24, "yy"), faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoShouldBeOk() {
        return new CardInfo("4444 4444 4444 4441", generateDate(3, "MM"), generateDate(24, "yy"), faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoWithWrongFormatNumberCardField() {
        return new CardInfo("  44 44O4 4444 44FF", generateDate(3, "MM"), generateDate(24, "yy"), faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }


    public static CardInfo getCardInfoWithWrongNumber() {
        return new CardInfo("4444 4444 4444 4446", generateDate(3, "MM"), generateDate(24, "yy"), faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoWithWrongMonth() {
        return new CardInfo("4444 4444 4444 4441", "15", generateDate(24, "yy"), faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoMonthFieldWithDoubleZero() {
        return new CardInfo("4444 4444 4444 4441", "00", generateDate(24, "yy"), faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoMonthWithWrongFormat() {
        return new CardInfo("4444 4444 4444 4441", "o*", generateDate(24, "yy"), faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoWithWrongYear() {
        return new CardInfo("4444 4444 4444 4441", generateDate(3, "MM"), "11", faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoWithWrongFormatYear() {
        return new CardInfo("4444 4444 4444 4441", generateDate(3, "MM"), "_F", faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoWithWrongFormatYearWithZero() {
        return new CardInfo("4444 4444 4444 4441", generateDate(3, "MM"), "00", faker.name().fullName(), String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoWithWrongName() {
        return new CardInfo("4444 4444 4444 4441", generateDate(3, "MM"), generateDate(24, "yy"), "Арсений Мартынов", String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoWithWrongFormatName() {
        return new CardInfo("4444 4444 4444 4441", generateDate(3, "MM"), generateDate(24, "yy"), "  %Арсений Мартынов23 33", String.valueOf(faker.number().numberBetween(100, 999)));
    }

    public static CardInfo getCardInfoWithWrongCVC() {
        return new CardInfo("4444 4444 4444 4441", generateDate(3, "MM"), generateDate(24, "yy"), faker.name().fullName(), "22");
    }

    public static CardInfo getCardInfoWithWrongFormatCVC() {
        return new CardInfo("4444 4444 4444 4441", generateDate(3, "MM"), generateDate(24, "yy"), faker.name().fullName(), "F4 ");
    }

    public static CardInfo getCardInfoWithEmptyFields() {
        return new CardInfo("", "", "", "", "");

    }

}
