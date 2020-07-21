package ru.projectApi.refactoring.steps.httpReqsters;

import freemarker.template.TemplateException;
import io.cucumber.java.ru.Затем;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.projectApi.refactoring.configurations.requstSpec.RsAddFinOper;
import ru.projectApi.refactoring.global.globalUtils.GlobalTools;

import java.io.IOException;
import java.util.Map;

public final class HttpRequestsForQP {

    private static String Writer;

    @Затем("^Отправить перевод .* используя шаблон \"([^\"]*)\" :")
    public static void sendRequestForQP(String nameJson, Map<String, String> params) throws IOException, TemplateException {
        Writer = GlobalTools.jsonConfiguration(nameJson, params);
        Response response = RestAssured.given(RsAddFinOper.requestsSpecificationSystem1)
                .body(Writer)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .response();
        Allure.addAttachment("request", "application/json", Writer);
        Allure.addAttachment("responses", "application/json", response.asString());
    }
}
