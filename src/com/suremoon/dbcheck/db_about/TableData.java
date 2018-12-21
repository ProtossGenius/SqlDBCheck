package com.suremoon.dbcheck.db_about;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableData {
    ResultSet rs;
    Statement stat;
    public TableData(Statement stat, String tableName) throws SQLException {
        rs = stat.executeQuery("select * from `" +tableName + "`");
        this.stat = stat;
    }

    public ResultSet getResultSet(){
        return rs;
    }

    public void close() throws SQLException {
        stat.close();
    }

}
