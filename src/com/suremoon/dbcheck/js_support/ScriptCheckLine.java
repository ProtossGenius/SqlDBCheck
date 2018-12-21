package com.suremoon.dbcheck.js_support;

import com.suremoon.dbcheck.ctrl.DBChecker;
import com.suremoon.dbcheck.db_about.LineData;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ScriptCheckLine implements LineCheckItf{
    static ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine se;
    Invocable invoke;
    public ScriptCheckLine(String path) throws FileNotFoundException, ScriptException {
        se = sem.getEngineByName("js");
        FileReader fr = new FileReader(path);
        se.eval(fr);
        invoke = (Invocable)se;
    }


    @Override
    public void doCheck(LineData lineData, DBChecker checker) throws ScriptException, NoSuchMethodException {
        invoke.invokeFunction("doCheck", lineData, checker);
    }
}
