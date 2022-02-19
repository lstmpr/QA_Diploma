package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import java.sql.DriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class DbInteraction {
    //@BeforeEach
    @SneakyThrows
    public static Connection getConnectionDB() {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass");
        return connection;
    }

    @SneakyThrows
    public static String getPaymentId() {
        String paymentId = null;
        String  SQLId = "SELECT payment_id FROM order_entity order by created desc limit 1;";

        try (
                Connection conn = getConnectionDB();
                PreparedStatement idPrepStat = conn.prepareStatement(SQLId);
        ) {
            try (ResultSet rs = idPrepStat.executeQuery()) {
                if (rs.next()) {
                    paymentId = rs.getString("payment_id");
                }
            }
        }
        return paymentId;

    }

    @SneakyThrows
    public static String getStatusDebitCard(String paymentId) {
        String status = null;
        String statusQuery = "SELECT status FROM payment_entity WHERE transaction_id =?;";

        try (
                Connection conn = getConnectionDB();
                PreparedStatement statusPrepStat = conn.prepareStatement(statusQuery);
        ) {
            statusPrepStat.setString(1, paymentId);
            try (ResultSet rs = statusPrepStat.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    @SneakyThrows
    public static String getStatusCreditCard(String paymentId) {
        String statusCredit = null;
        String statusQueryCredit = "SELECT status FROM credit_request_entity WHERE bank_id =?;";

        try (
                Connection conn = getConnectionDB();
                PreparedStatement statusPrepStat = conn.prepareStatement(statusQueryCredit);
        ) {
            statusPrepStat.setString(1, paymentId);
            try (ResultSet rs = statusPrepStat.executeQuery()) {
                if (rs.next()) {
                    statusCredit = rs.getString("status");
                }
            }
        }
        return statusCredit;
    }

    @SneakyThrows
    public static void cleanDb() {
        String deleteCreditDB = "DELETE FROM credit_request_entity WHERE TRUE;";
        String deleteDebitDB = "DELETE FROM payment_entity WHERE TRUE;";
        String deleteDBForAll = "DELETE FROM order_entity WHERE TRUE;";
        try (Connection conn = getConnectionDB();
             Statement deleteCardsStmt = conn.createStatement();
             Statement deleteAuthCodesStmt = conn.createStatement();
             Statement deleteUsersStmt = conn.createStatement();
        ) {
            deleteCardsStmt.executeUpdate(deleteCreditDB);
            deleteAuthCodesStmt.executeUpdate(deleteDebitDB);
            deleteUsersStmt.executeUpdate(deleteDBForAll);
        }
    }


}
