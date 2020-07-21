package ru.projectApi.refactoring.configurations.requstSpec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RsAddFinOper {

    private static final String BASE_URL_PROD = "http://dp.ru";
    private static final String BASE_URL_TEST = "http://dp.ru";
    private static final String BASE_PATH = "path/pathpath";
    private static final int BASE_PORT = 17711;
    public static final RequestSpecification requestsSpecificationSystem1 = new RequestSpecBuilder()
            .setBaseUri(getBaseUrl())
            .setPort(getBasePort())
            .setBasePath(getBasePath())
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .build();

    private static final String BASE_URL_PROD_YYY = "http://dp2.ru";
    private static final String BASE_PATH_FSG = "path22/pathpath222";
    private static final int BASE_PORT_FSG = 17721;
    public static final RequestSpecification requestsSpecificationSystem2 = new RequestSpecBuilder()
            .setBaseUri(BASE_URL_PROD_YYY)
            .setPort(BASE_PORT_FSG)
            .setBasePath(BASE_PATH_FSG)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .build();

    private RsAddFinOper() {
    }

    private static String getBaseUrl() {
        String platform = System.getProperty("platform");
        if (platform.equals("test")) {
            return BASE_URL_TEST;
        }
        return BASE_URL_PROD;
    }

    private static int getBasePort() {
        return BASE_PORT;
    }

    private static String getBasePath() {
        return BASE_PATH;
    }
}
