package com.suremoon.js_support;

import com.suremoon.db_about.DBLoader;
import com.suremoon.db_about.LineData;

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
    public void doCheck(LineData lineData, DBLoader db) throws ScriptException, NoSuchMethodException {
        invoke.invokeFunction("doCheck", lineData, db);
    }
}
