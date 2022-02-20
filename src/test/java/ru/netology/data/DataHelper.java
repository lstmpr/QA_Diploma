package ru.netology.data;

import lombok.Value;
import com.github.javafaker.Faker;
import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));
    private DataHelper() {

    }

    @Value
    public static class CardInfo {
        private String cardNum;
        private String monthNum;
        private String year;
        private String ownerCard;
        private String cvcCode;
    }


    public static CardInfo getCardInfoApproved() {
        return new CardInfo("4444 4444 4444 4441",String.valueOf(faker.number().numberBetween(10,12)),String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }
    public static CardInfo getCardInfoDeclined() {
        return new CardInfo("4444 4444 4444 4442",String.valueOf(faker.number().numberBetween(10,12)),String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoShouldBeOk() {
        return new CardInfo("4444 4444 4444 4441",String.valueOf(faker.number().numberBetween(1,12)),String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoWithWrongFormatNumberCardField() {
        return new CardInfo("  44 44O4 4444 44FF",String.valueOf(faker.number().numberBetween(10,12)),String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }


    public static CardInfo getCardInfoWithWrongNumber() {
        return new CardInfo("4444 4444 4444 4446","05",String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoWithWrongMonth() {
        return new CardInfo("4444 4444 4444 4441","15",String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoMonthFieldWithDoubleZero() {
        return new CardInfo("4444 4444 4444 4441","00",String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoMonthWithWrongFormat() {
        return new CardInfo("4444 4444 4444 4441","o*",String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoWithWrongYear() {
        return new CardInfo("4444 4444 4444 4441","08","11",faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoWithWrongFormatYear() {
        return new CardInfo("4444 4444 4444 4441","08","_F",faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoWithWrongFormatYearWithZero() {
        return new CardInfo("4444 4444 4444 4441","08","00",faker.name().fullName(),String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoWithWrongName() {
        return new CardInfo("4444 4444 4444 4441","02",String.valueOf(faker.number().numberBetween(22,25)),"Арсений Мартынов",String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoWithWrongFormatName() {
        return new CardInfo("4444 4444 4444 4441","02",String.valueOf(faker.number().numberBetween(22,25)),"  %Арсений Мартынов23 33",String.valueOf(faker.number().numberBetween(100,999)));
    }

    public static CardInfo getCardInfoWithWrongCVC() {
        return new CardInfo("4444 4444 4444 4441","01",String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),"22");
    }

    public static CardInfo getCardInfoWithWrongFormatCVC() {
        return new CardInfo("4444 4444 4444 4441","01",String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),"F4 ");
    }

    public static CardInfo getCardInfoWithEmptyFields() {
        return new CardInfo("","","","","");
    }

//    @Value
//    public static class Status {
//        private String status;
//    }




}
