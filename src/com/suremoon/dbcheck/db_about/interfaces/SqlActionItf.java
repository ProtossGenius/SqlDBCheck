package com.suremoon.dbcheck.db_about.interfaces;

import java.sql.SQLException;
import java.sql.Statement;

public interface SqlActionItf {
    void sqlact(Statement statement) throws SQLException;
}
