package com.suremoon.dbcheck;

import com.suremoon.dbcheck.ctrl.CheckCtrl;
import com.suremoon.start_param.StartParam;
import com.suremoon.start_param.StartParamDeal;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        StartParamDeal spd = StartParamDeal.getStartParamDeal();
        StartParam configPath = spd.paramInit("cfg", "./configs/check_ctrl.cfg", "config path.");
        spd.parse(args);
        CheckCtrl cc = CheckCtrl.getCheckCtrl();
        cc.doCheck(configPath.getValue());
    }
}
