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

    @Value
    public static class CardInfo {
        private String cardNum;
        private String monthNum;
        private String year;
        private String ownerCard;
        private String cvcCode;
    }

//    public static CardInfo getCardInfoApproved() {
//        return new CardInfo("4444 4444 4444 4441",faker.number().numberBetween(0,13),faker.number().numberBetween(0,100),faker.name().fullName(),faker.number().numberBetween(99,1000));
//    }
//
//    public static CardInfo getCardInfoDeclined() {
//        return new CardInfo("4444 4444 4444 4442",faker.number().numberBetween(0,13),faker.number().numberBetween(0,100),faker.name().fullName(),faker.number().numberBetween(99,1000));
//    }

    public static CardInfo getCardInfoApproved() {
        return new CardInfo("4444 4444 4444 4441",String.valueOf(faker.number().numberBetween(10,12)),String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(99,1000)));
    }
    public static CardInfo getCardInfoDeclined() {
        return new CardInfo("4444 4444 4444 4442",String.valueOf(faker.number().numberBetween(10,12)),String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(99,1000)));
    }

    public static CardInfo getCardInfoShouldBeOk() {
        return new CardInfo("4444 4444 4444 4441",String.valueOf(faker.number().numberBetween(10,12)),String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(99,1000)));
    }


    public static CardInfo getCardInfoWithWrongNumber() {
        return new CardInfo("4444 4444 4444 4446",String.valueOf(faker.number().numberBetween(0,13)),String.valueOf(faker.number().numberBetween(22,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(99,1000)));
    }

    public static CardInfo getCardInfoWithWrongMonth() {
        return new CardInfo("4444 4444 4444 4441","15",String.valueOf(faker.number().numberBetween(20,25)),faker.name().fullName(),String.valueOf(faker.number().numberBetween(99,1000)));
    }

    public static CardInfo getCardInfoWithWrongYear() {
        return new CardInfo("4444 4444 4444 4441",String.valueOf(faker.number().numberBetween(0,13)),"11",faker.name().fullName(),String.valueOf(faker.number().numberBetween(99,1000)));
    }

    public static CardInfo getCardInfoWithWrongName() {
        return new CardInfo("4444 4444 4444 4441",String.valueOf(faker.number().numberBetween(0,13)),String.valueOf(faker.number().numberBetween(20,25)),"Арсений Мартынов",String.valueOf(faker.number().numberBetween(99,1000)));
    }

    public static CardInfo getCardInfoWithWrongCVC() {
        return new CardInfo("4444 4444 4444 4441",String.valueOf(faker.number().numberBetween(0,13)),String.valueOf(faker.number().numberBetween(20,25)),faker.name().fullName(),"22");
    }

    public static CardInfo getCardInfoWithEmptyFields() {
        return new CardInfo("","","","","");
    }

//    @Value
//    public static class Status {
//        private String status;
//    }




}
