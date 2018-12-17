package com.suremoon.db_about.interfaces;

import java.sql.SQLException;
import java.sql.Statement;

public interface SqlActionItf {
    void sqlact(Statement statement) throws SQLException;
}
