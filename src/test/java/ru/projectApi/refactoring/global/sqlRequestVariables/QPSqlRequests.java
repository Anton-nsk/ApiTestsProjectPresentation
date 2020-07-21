package ru.projectApi.refactoring.global.sqlRequestVariables;

public final class QPSqlRequests {

    public static final String INSERT_RULE_PARAMETERS = "INSERT INTO table_name (RULE, OBJECT, PNAME, PVALUE) values (?, ?, ?, ?)";
    public static final String INSERT_SCENARIO = "INSERT INTO table_name (SCENARIO, RULE_ORDER, RULE) VALUES (?, ?, ?)";
    public static final String INSERT_CONTROL_SCENARIO = "INSERT INTO table_name (CODE, DESCRIPTION, DEFAULT_STATUS, SYNCH_MODE, SYSTEM_ID, STEP) "
                                                         + "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_COUNT_QUARANTINE_NO_FRAUD = "select count(*) from table_name where uin = ?";
    public static final String SELECT_COUNT_QUARANTINE = "select count(*) from table_name where uin = ? and status = ?";
    public static final String SELECT_COUNT_QUARANTINE_AND_REASON = "select count(*) from table_name where uin = ? and reason in (?)  and status = ?";
    public static final String SELECT_ISCHECKED = "SELECT ischecked FROM table_name WHERE UIN = ?";
    public static final String SELECT_REASON = "SELECT reason FROM table_name WHERE UIN = ? and status = ?";

    private QPSqlRequests() {
    }
}
