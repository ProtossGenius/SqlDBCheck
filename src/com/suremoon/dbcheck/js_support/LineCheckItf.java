package com.suremoon.dbcheck.js_support;

import com.suremoon.dbcheck.ctrl.DBChecker;
import com.suremoon.dbcheck.db_about.LineData;

import javax.script.ScriptException;

public interface LineCheckItf {
    void doCheck(LineData lineData,  DBChecker checker) throws ScriptException, NoSuchMethodException;
}
