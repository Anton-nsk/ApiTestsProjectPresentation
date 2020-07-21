package ru.projectApi.refactoring.global.sqlRequestVariables.sqlVariablesForClearing;

public final class SqlClearingVariables {

    public static final String DEL_BLW = "delete from table_name where ref_dm_id in (select uin from table_name where uin between 1 and "
                                         + "100000000)";
    public static final String DEL_REJ_FROM_LIST = "delete from table_name where id_from_dm in (select uin from table_name where uin between"
                                                   + " 1 and 100000000)";
    public static final String DEL_MESS = "delete from table_name where uin in (select uin from table_name where uin between 1 and 100000000)";

    public static final String DEL_QP_TR = "delete from table_name where uin in (select uin from table_name where uin between 1 and 100000000)";

    public static final String DEL_QP_TRANS_ST = "delete from table_name where uin in (select uin from table_name where uin between 1 and "
                                                 + "100000000)";
    public static final String DEL_QP_TR_REJECT = "delete from table_name where uin in (select uin from table_name where uin between 1 "
                                                  + "and 100000000)";
    public static final String DEL_QP_CHK_STATUS = "delete from table_name where uin between 1 and 100000000";

    public static final String DEL_QP_DAY_DM_R = "delete from table_name where uin in (select uin from table_name where uin between 1 and 100000000)";

    public static final String DEL_QP_BLS_DM = "delete from table_name where uin in (select uin from table_name where uin between 1 and "
                                               + "100000000)";
    public static final String DEL_QP_LOG_QUARANTINE = "delete from table_name where uin in (select uin from table_name where uin between 1 "
                                                       + "and 100000000)";
    public static final String DEL_QP_QUARANTINE = "delete from table_name where uin in (select uin from table_name where uin between 1 and "
                                                   + "100000000)";
    public static final String DEL_QP_DAY_DM = " delete from table_name where uin in (select uin from table_name where uin between 1 and 100000000)";

    private SqlClearingVariables() {
    }
}
