package com.suremoon.js_support;

import com.suremoon.db_about.DBChecker;
import com.suremoon.db_about.LineData;

import javax.script.ScriptException;

public interface LineCheckItf {
    void doCheck(LineData lineData,  DBChecker checker) throws ScriptException, NoSuchMethodException;
}
