package com.suremoon.js_support;

import com.suremoon.db_about.DBLoader;
import com.suremoon.db_about.LineData;

import javax.script.ScriptException;

public interface LineCheckItf {
    void doCheck(LineData lineData, DBLoader db) throws ScriptException, NoSuchMethodException;
}
