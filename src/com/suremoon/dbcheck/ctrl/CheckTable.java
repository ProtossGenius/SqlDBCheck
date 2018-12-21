package com.suremoon.dbcheck.ctrl;

import com.suremoon.dbcheck.db_about.LineData;
import com.suremoon.dbcheck.js_support.ScriptCheckLine;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckTable {
    ScriptCheckLine scl;
    ResultSet rs;
    String tableName;
    DBChecker checker;
    public CheckTable(File scriptFile, String tableName, ResultSet allData, DBChecker checker) {
        try {
            scl = new ScriptCheckLine(scriptFile.getPath());
        } catch (Exception e) {
            Loger.getLoger().writeException(e);
        }
        rs = allData;
        this.tableName = tableName;
        this.checker = checker;
    }

    public void doCheck() throws SQLException {
        while (rs.next()){
            try {
                scl.doCheck(new LineData(rs), checker);
            } catch (Exception e){
                Loger.getLoger().writeException(e);
            }
        }
    }
}
