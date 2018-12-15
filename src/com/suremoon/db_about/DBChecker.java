package com.suremoon.db_about;

import com.suremoon.ctrl.Loger;

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
    public void checkStringVal(String tableName, String colName, String value) throws SQLException {
        loader.checkStringVal(tableToCheck, tableName, colName, value);
    }

    /**
     * Maybe some more complicated queries
     *
     * @param tableName
     * @param condition The part after the sql statement's "where" keyword
     * @throws SQLException
     */
    public void checkValExist(String tableName, String condition) throws SQLException {
        loader.checkValExist(tableToCheck, tableName, condition);
    }

    /**
     *You can write logs to the log file, which can be used in conjunction with your own build query.
     * Although not recommended, it is prepared for more diverse needs.
     * @param log
     * the log to write.
     */
    public void writeLog(String log){
        loger.writeLog(log + "\n");
    }

    /**
     *Returns the Statement for building the query and processing it yourself. But remember to close in time to prevent a variety of problems.
     * @return
     * @throws SQLException
     */
    @Deprecated
    public Statement getStatement() throws SQLException {
        return this.loader.getStatement();
    }

    /**
     *
     * @param sa
     * @throws SQLException
     */
    public void sqlAct(SqlAction sa) throws SQLException {
        Statement state = getStatement();
        sa.sqlact(state);
        state.close();
    }

}
