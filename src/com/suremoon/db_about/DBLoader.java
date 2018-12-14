package com.suremoon.db_about;

import com.suremoon.ctrl.Loger;

import java.sql.*;

public class DBLoader {
    private static final String Class_Name = "org.sqlite.JDBC";
    Connection conn;

    public DBLoader(String path) throws ClassNotFoundException, SQLException {
        Class.forName(Class_Name);
        conn = DriverManager.getConnection("jdbc:sqlite:" + path);
    }

    public TableData getTableData(String tableName) throws SQLException {
        Statement stat = conn.createStatement();
        return new TableData(stat, tableName);
    }

    @Deprecated
    public Statement getStatement() throws SQLException {
        return conn.createStatement();
    }

    public void checkStringVal(String tableName, String colName, String value) throws SQLException {
        Statement stat = conn.createStatement();
        String sql = String.format("select count(*) from`%s` where `%s`='%s';", tableName, colName, value);
        ResultSet rs = stat.executeQuery(sql);
        int nums = rs.getInt(1);
        switch (nums) {
            case 1://right;
                return;
            case 0://not found
                Loger.getLoger().writeLog(String.format("[DATA_NOT_FOUND] in table[%s] columnName[%s] value[%s]", tableName, colName, value));
                return;
            default:
                Loger.getLoger().writeLog(String.format("[DATA_MORE_THAN_ONE] in table[%s] columnName[%s] value[%s] nums %d", tableName, colName, value, nums));

        }
    }
}
