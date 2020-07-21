package ru.projectApi.refactoring.steps.sqlRequests;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Также;
import io.cucumber.java.ru.Тогда;
import ru.projectApi.refactoring.checks.checksStatusTransaction.SqlCheckTransaction;
import ru.projectApi.refactoring.connectors.ConnectForDB;
import ru.projectApi.refactoring.global.sqlRequestVariables.QPSqlRequests;
import ru.projectApi.refactoring.hooks.UtilityForGetParameters;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public final class SqlRequestsForQP {

    @И("^Удаляем правила для системы \"([^\"]*)\"")
    public void cleanRules(String sourceId) throws SQLException {
        Statement st = ConnectForDB.getConnection().createStatement();
        if (sourceId.toLowerCase().equals("qp")) {
            st.addBatch("delete from tablename");
        } else {
            st.addBatch("delete from tablename_" + sourceId.toLowerCase() + "tablename");
        }
        st.addBatch("delete from tablename where rule in (select rule from tablename where scenario in (select code from "
                    + "tablename where system_Id = '" + sourceId.toUpperCase() + "'))");
        st.addBatch("delete from tablename where scenario in (select code from tablename "
                    + "where system_Id = '" + sourceId.toUpperCase() + "')");
        st.addBatch("delete from tablename where system_Id = '" + sourceId.toUpperCase() + "'");
        st.executeBatch();
        st.close();
    }

    @Тогда("^Настраиваем таблицу tablename:$")
    public void settingUpRuleTableControlScenario(DataTable table) throws SQLException {
        List<Map<String, String>> controlScenario = table.asMaps(String.class, String.class);
        PreparedStatement ps = ConnectForDB.getConnection().prepareStatement(QPSqlRequests.INSERT_CONTROL_SCENARIO);
        for (Map<String, String> map_controlScenario : controlScenario) {
            ps.setString(1, map_controlScenario.get("code"));
            ps.setString(2, map_controlScenario.get("description"));
            ps.setString(3, map_controlScenario.get("defaultStatus"));
            ps.setString(4, map_controlScenario.get("synchMode"));
            ps.setString(5, map_controlScenario.get("systemId"));
            ps.setString(6, map_controlScenario.get("step"));
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    @Также("^Настраиваем таблицу tablename2:$")
    public void settingUpRuleTableScenario(DataTable table) throws SQLException {
        List<Map<String, String>> scenario = table.asMaps(String.class, String.class);
        PreparedStatement ps = ConnectForDB.getConnection().prepareStatement(QPSqlRequests.INSERT_SCENARIO);
        for (Map<String, String> map_scenario : scenario) {
            ps.setString(1, map_scenario.get("scenario"));
            ps.setInt(2, Integer.parseInt(map_scenario.get("ruleOrder")));
            ps.setString(3, map_scenario.get("rule"));
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    @И("^Настраиваем таблицу tablename3:$")
    public void settingUpRuleTableRuleParameters(DataTable table) throws SQLException {
        List<Map<String, String>> rule = table.asMaps(String.class, String.class);
        PreparedStatement ps = ConnectForDB.getConnection().prepareStatement(QPSqlRequests.INSERT_RULE_PARAMETERS);
        for (Map<String, String> map_rule : rule) {
            ps.setString(1, map_rule.get("rule"));
            ps.setString(2, map_rule.get("object"));
            ps.setString(3, map_rule.get("pname"));
            ps.setString(4, UtilityForGetParameters.getParam(map_rule.get("pvalue")));
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    @И("^Проверяем, что перевод с uin: \"([^\"]*)\" ФРОД по reason:")
    public void checkTransactionStatusFraud(String uin, List<String> reason) throws SQLException, InterruptedException {
        SqlCheckTransaction.sqlCheckStatus(uin);
        SqlCheckTransaction.checkFraud(uin, reason, "F");
    }

    @И("^Проверяем, что перевод с uin: \"([^\"]*)\" НЕ ФРОД")
    public void checkTransactionStatusOk(String uin) throws SQLException, InterruptedException {
        SqlCheckTransaction.sqlCheckStatus(uin);
        SqlCheckTransaction.checkNoFraud(uin);
    }
}

