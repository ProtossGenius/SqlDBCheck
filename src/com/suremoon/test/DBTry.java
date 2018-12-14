package com.suremoon.test;

import java.sql.*;

public class DBTry {
    private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:./db/shop.db";

    public static void main(String[] args) throws Exception {
//        testCreate();
        testFind();
    }

    static void testCreate() throws Exception {
        Class.forName(Class_Name);
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stat = conn.createStatement();
        stat.executeUpdate("create table `user`(id int, name varchar(20) );");
        stat.executeUpdate("insert into `user` values(10000, 'moon')");
        stat.executeUpdate("insert into `user` values(10001, 'sui')");
        stat.executeUpdate("create table  items(id int, uid int, itype int);");
        stat.executeUpdate("insert  into items values (1, 10000, 1);");
        stat.executeUpdate("insert  into items values (1, 100001, 1);");//uid not exist.
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
        ;
    }

}
