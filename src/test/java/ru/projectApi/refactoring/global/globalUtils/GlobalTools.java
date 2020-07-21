package ru.projectApi.refactoring.global.globalUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.projectApi.refactoring.configurations.Constants;
import ru.projectApi.refactoring.connectors.ConnectForDB;
import ru.projectApi.refactoring.global.sqlRequestVariables.sqlVariablesForClearing.SqlClearingVariables;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public final class GlobalTools {

    private GlobalTools() {
    }

    public static String getProperties(String dbname) throws IOException {
        Properties property = new Properties();
        FileInputStream fis;
        fis = new FileInputStream("src/test/resources/dbProperties/db.properties");
        property.load(fis);
        return property.getProperty(dbname);
    }

    public static String jsonConfiguration(String nameJson, Map<String, String> params) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(Constants.PATH_FOR_JSON));
        Template template = cfg.getTemplate(nameJson);
        StringWriter writer = new StringWriter();
        template.process(params, writer);
        return writer.toString();
    }

    public static void clearingUinData() throws SQLException {
        Statement st = ConnectForDB.getConnection().createStatement();
        st.addBatch(SqlClearingVariables.DEL_BLW);
        st.addBatch(SqlClearingVariables.DEL_REJ_FROM_LIST);
        st.addBatch(SqlClearingVariables.DEL_MESS);
        st.addBatch(SqlClearingVariables.DEL_QP_TR);
        st.addBatch(SqlClearingVariables.DEL_QP_TRANS_ST);
        st.addBatch(SqlClearingVariables.DEL_QP_TR_REJECT);
        st.addBatch(SqlClearingVariables.DEL_QP_CHK_STATUS);
        st.addBatch(SqlClearingVariables.DEL_QP_DAY_DM_R);
        st.addBatch(SqlClearingVariables.DEL_QP_BLS_DM);
        st.addBatch(SqlClearingVariables.DEL_QP_LOG_QUARANTINE);
        st.addBatch(SqlClearingVariables.DEL_QP_QUARANTINE);
        st.addBatch(SqlClearingVariables.DEL_QP_DAY_DM);
        st.executeBatch();
        st.close();
    }

    private Date getDate(String date) throws ParseException {
        Date resultDate = new Date();
        if (date != null && Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}", date)) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date);
        }
        if (date != null && date.contains("минут назад")) {
            int time = Integer.parseInt(date.substring(0, date.indexOf(" минут назад")));
            return new Date(System.currentTimeMillis() - time * 60 * 1000);
        }
        if (date == null) {
            return new Date();
        }
        return resultDate;
    }
}