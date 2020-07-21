package ru.projectApi.refactoring.connectors;

import io.cucumber.java.ru.Дано;
import io.qameta.allure.Allure;
import org.junit.Assert;
import ru.projectApi.refactoring.global.globalUtils.GlobalTools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.getProperty;

public final class ConnectForDB {

    private static Connection connection;

    @Дано("^Подключаемся к базе db-name с логином \"([^\"]*)\" и паролем \"([^\"]*)\"$")
    public static void connectForDb(String username, String pass) throws SQLException, IOException {
        String platform = getProperty("platform");
        String db = GlobalTools.getProperties("db-" + platform);
        Allure.addAttachment("Адрес базы", "text/plain", db);
        connection = DriverManager.getConnection(db, username, pass);
    }

    public static void connectForDb(String platforms, String username, String pass) throws IOException {
        String platform = getProperty("platform");
        String db = GlobalTools.getProperties("db-" + platform);
        Allure.addAttachment("Адрес базы", "text/plain", db);
        try {
            connection = DriverManager.getConnection(db, username, pass);
        } catch (SQLException se) {
            Assert.fail("БД недоступна");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeDbConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            Assert.fail("БД недоступна");
        }
    }

    private static void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}