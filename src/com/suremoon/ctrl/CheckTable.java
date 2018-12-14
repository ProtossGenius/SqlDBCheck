package com.suremoon.ctrl;

import com.suremoon.db_about.DBLoader;
import com.suremoon.db_about.LineData;
import com.suremoon.js_support.ScriptCheckLine;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckTable {
    ScriptCheckLine scl;
    ResultSet rs;
    String tableName;
    DBLoader dbLoader;
    public CheckTable(File scriptFile, String tableName, ResultSet allData, DBLoader dbLoader) {
        try {
            scl = new ScriptCheckLine(scriptFile.getPath());
        } catch (Exception e) {
            Loger.getLoger().writeException(e);
        }
        rs = allData;
        this.tableName = tableName;
        this.dbLoader = dbLoader;
    }

    public void doCheck() throws SQLException {
        while (rs.next()){
            try {
                scl.doCheck(new LineData(rs), dbLoader);
            } catch (Exception e){
                Loger.getLoger().writeException(e);
            }
        }
    }
}
