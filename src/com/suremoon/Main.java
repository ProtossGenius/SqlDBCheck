package com.suremoon;

import com.suremoon.ctrl.CheckCtrl;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        CheckCtrl cc = CheckCtrl.getCheckCtrl();
        cc.doCheck();
    }
}
