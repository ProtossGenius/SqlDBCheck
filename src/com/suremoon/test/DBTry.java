package com.suremoon.test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Scanner;

public class DBTry {
    private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:./db/shop.db";

    public static void main(String[] args) throws Exception {
        testCreate();
//        testFind();
    }

    static void testCreate() throws Exception {
        Class.forName(Class_Name);
        File sqlin = new File("./db/shop.sql");
        Scanner sin = new Scanner(new FileInputStream(sqlin));
        File f = new File("./db/shop.db");
        if (f.exists()) {
            f.delete();
        }
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stat = conn.createStatement();
        while(sin.hasNext()){
            stat.executeUpdate(sin.nextLine());
        }
        conn.close();
    }

    static void testFind() throws Exception {
        Class.forName(Class_Name);
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select count(*) from `user` where id=10000;");
        System.out.println(rs.getInt(1));
        rs.close();
        rs = stat.executeQuery("select count(*) from `user` where id=100001;");//not exist.
        System.out.println("----+" + rs.getString(1));
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println(rs.getInt(1));
        conn.close();
    }

}
