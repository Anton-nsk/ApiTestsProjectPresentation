package ru.projectApi.refactoring.hooks;

import java.util.HashMap;
import java.util.Map;

public final class UtilityForGetParameters {

    private static final Map<String, String> params = new HashMap<String, String>();

    private UtilityForGetParameters() {
    }

    public static void addParam(String name, String value) {
        params.put(name, value);
    }

    public static String getParam(String paramName) {
        String paramValue = params.get(paramName);
        if (paramValue == null) {
            return paramName;
        }
        return paramValue;
    }
}
