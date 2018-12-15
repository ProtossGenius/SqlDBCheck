package com.suremoon.db_about;

import com.suremoon.ctrl.Loger;

import java.sql.*;
import java.util.HashSet;

public class DBLoader {
    private static final String Class_Name = "org.sqlite.JDBC";
    Connection conn;
    private HashSet<String> querySet = new HashSet<>();
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
        String sql = String.format("select count(*) from`%s` where `%s`='%s';", tableName, colName, value);
        if(querySet.contains(sql)){
            return;
        }
        querySet.add(sql);
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        int nums = rs.getInt(1);
        rs.close();
        stat.close();
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

    /**
     * Maybe some more complicated queries
     * @param tableName
     * @param condition
     * The part after the sql statement's "where" keyword
     * @throws SQLException
     */
    public void checkValExist(String tableName, String condition) throws SQLException{
        String sql = String.format("select count(*) from`%s` where %s;", tableName, condition);
        if(querySet.contains(sql)){
            return;
        }
        Statement stat = conn.createStatement();
        querySet.add(sql);
        ResultSet rs = stat.executeQuery(sql);
        int nums = rs.getInt(1);
        rs.close();
        stat.close();
        switch (nums) {
            case 1://right;
                return;
            case 0://not found
                Loger.getLoger().writeLog(String.format("[DATA_NOT_FOUND] in table[%s] condition is:[%s]", tableName, condition));
                return;
            default:
                Loger.getLoger().writeLog(String.format("[DATA_MORE_THAN_ONE] in table[%s] condition[%s] nums %d", tableName, condition, nums));
        }
    }

}
