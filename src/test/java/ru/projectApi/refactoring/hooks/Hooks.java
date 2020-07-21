package ru.projectApi.refactoring.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Step;
import ru.projectApi.refactoring.connectors.ConnectForDB;
import ru.projectApi.refactoring.global.globalUtils.GlobalTools;

import java.io.IOException;
import java.sql.SQLException;

import static ru.projectApi.refactoring.configurations.Constants.EMAIL_FROM_QA;
import static ru.projectApi.refactoring.configurations.Constants.MESSAGE_FOR_EMAIL_TEXT;

public final class Hooks {

    private static boolean dunit;

    @Before
    @Step("Очистка param'ов")
    public void presetTuning() throws IOException, SQLException {
        if (!dunit) {
            ConnectForDB.connectForDb("prod", "username", "pasword");
            GlobalTools.clearingUinData();
            ConnectForDB.closeDbConnection();
            dunit = true;
        }
        UtilityForGetParameters.addParam("$message", MESSAGE_FOR_EMAIL_TEXT);
        UtilityForGetParameters.addParam("$email", EMAIL_FROM_QA);
    }

    @After
    @Step("Закрытие соединения с БД")
    public void closeDbConnect() {
        ConnectForDB.closeDbConnection();
    }
}
