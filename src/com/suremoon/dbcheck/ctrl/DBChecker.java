package com.suremoon.dbcheck.ctrl;

import com.suremoon.dbcheck.db_about.DBLoader;
import com.suremoon.dbcheck.db_about.interfaces.QueryResultDealItf;
import com.suremoon.dbcheck.db_about.interfaces.SqlActionItf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBChecker {
    Loger loger = Loger.getLoger();
    String tableToCheck;
    DBLoader loader;

    public DBChecker(String tableToCheck, DBLoader loader) {
        this.tableToCheck = tableToCheck;
        this.loader = loader;
    }

    /**
     * check if the value exist in table.column
     *
     * @param tableName
     * @param colName
     * @param value
     * @throws SQLException
     */
    public void singleValueCheck(String tableName, String colName, String value) throws SQLException {
        loader.checkStringVal(tableToCheck, tableName, colName, value);
    }

    /**
     * Maybe some more complicated queries
     *
     * @param tableName
     * @param condition The part after the sql statement's "where" keyword
     * @throws SQLException
     */
    public void conditionCheck(String tableName, String condition) throws SQLException {
        loader.checkValExist(tableToCheck, tableName, condition);
    }

    /**
     * You can write logs to the log file, which can be used in conjunction with your own build query.
     * Although not recommended, it is prepared for more diverse needs.
     *
     * @param log the log to write.
     */
    public void writeLog(String log) {
        loger.writeLog(log + "\n");
    }

    /**
     * Returns the Statement for building the query and processing it yourself. But remember to close in time to prevent a variety of problems.
     *
     * @return
     * @throws SQLException
     */
    @Deprecated
    public Statement getStatement() throws SQLException {
        return this.loader.getStatement();
    }

    /**
     * Safe use statement.
     * This can be easily optimized using techniques such as connection pooling.
     *
     * @param sa
     * @throws SQLException
     */
    public void sqlAct(SqlActionItf sa) throws SQLException {
        Statement state = getStatement();
        sa.sqlact(state);
        state.close();
    }

    /**
     * safe use ResultSet
     * @param sql
     * @param resultDeal
     * @throws SQLException
     */
    public void safeQuery(String sql, QueryResultDealItf resultDeal) throws SQLException {
        sqlAct((statement) -> {
            ResultSet resultSet = statement.executeQuery(sql);
            resultDeal.DealResult(resultSet);
            resultSet.close();
        });
    }

}
