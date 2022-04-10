package ru.netology.data;

import lombok.SneakyThrows;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



import org.junit.jupiter.api.BeforeEach;

import java.sql.*;

public class DbInteraction {
    @BeforeEach
    @SneakyThrows
    public static Connection getConnectionDB() throws SQLException {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }

    @SneakyThrows
    public static String getPaymentId() {
        String paymentId = null;
        String SQLId = "SELECT payment_id FROM order_entity order by created desc limit 1;";

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


}