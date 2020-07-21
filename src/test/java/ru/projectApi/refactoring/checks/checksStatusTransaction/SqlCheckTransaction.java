package ru.projectApi.refactoring.checks.checksStatusTransaction;

import io.qameta.allure.Step;
import org.junit.Assert;
import ru.projectApi.refactoring.connectors.ConnectForDB;
import ru.projectApi.refactoring.global.sqlRequestVariables.QPSqlRequests;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Thread.sleep;

public final class SqlCheckTransaction {

    private SqlCheckTransaction() {
    }

    @Step("Проверяем, что статус перевода не 0")
    public static void sqlCheckStatus(String uin) throws SQLException, InterruptedException {
        int count = 0;
        String result;
        PreparedStatement ps = ConnectForDB.getConnection().prepareStatement(QPSqlRequests.SELECT_ISCHECKED);
        ps.setString(1, uin);
        do {
            sleep(100);
            ResultSet rs = ps.executeQuery();
            rs.next();
            count++;
            result = String.valueOf(rs.getCharacterStream("ISCHECKED"));
        } while (count <= 5 && !result.equals("null"));
        if (count == 6 && !result.equals("null")) {
            Assert.fail("Время ожидания истекло");
        }
        ps.close();
    }

    @Step("Проверяем, что перевод был заблокирован по указанной причине")
    public static void checkFraud(String uin, List<String> reasons, String statusBlock) throws SQLException {
        ResultSet rs = getReason(uin, statusBlock);
        Assert.assertNotEquals("Операция не была заблокирована", rs.getRow(), 0);
        Assert.assertEquals("Колличество причин блокировки не совпадает с ожидаемым", reasons.size(), rs.getRow());
        do {
            String reason = rs.getString("REASON");
            Assert.assertTrue("Причина " + reason + " не совпадает с ожидаемой(-ыми)", reasons.contains(reason));
        } while (rs.next());
    }

    @Step("Проверяем, что перевод не был заблокирован")
    public static void checkNoFraud(String uin) throws SQLException {
        ResultSet rs = getReason(uin, "F");
        Assert.assertEquals("Sudden FRAUD. The operation was blocked", 0, rs.getRow());
    }

    private static ResultSet getReason(String uin, String statusBlock) throws SQLException {
        PreparedStatement ps = ConnectForDB.getConnection().prepareStatement(QPSqlRequests.SELECT_REASON);
        ps.setString(1, uin);
        ps.setString(2, statusBlock);
        ResultSet result = ps.executeQuery();
        result.next();
        return result;
    }
}