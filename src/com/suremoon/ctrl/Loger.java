package com.suremoon.ctrl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Loger {

    FileWriter log;

    static Loger loger;

    protected static void init(File f) throws IOException {
        System.out.println("set log file as " + f.getPath());
        loger = new Loger(f);
    }

    private Loger(File f ) throws IOException {
        log = new FileWriter(f);
    }

    public static Loger getLoger(){
        if(loger ==null){
            System.err.println("Loger not init, now write to ./loger.log");
            try {
                loger = new Loger(new File("./loger.log"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loger;
    }

    public void writeLog(String str) {
        System.out.println(str);
        try {
            log.write(str + "\n");
            log.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeException(Exception e){
        e.printStackTrace();
        writeLog("[EXCEPTION]" + e.toString());
    }
}