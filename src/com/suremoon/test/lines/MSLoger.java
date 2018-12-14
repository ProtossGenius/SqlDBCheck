package com.suremoon.test.lines;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MSLoger {
    public MSLoger() {
    }

    public static void writeFLog(String str) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        write("[" + df.format(new Date()) + "]:" + str);
    }

    public static void write(String str) {
        write("./MSLoger_log.txt", str);
    }

    public static void write(String f, String str) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(f, true);
            fw.write(str + "\r\n");
            fw.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public static void Rewrite(String f) {
        File file = new File(f);
        file.delete();
    }

    public static void RewriteLog() {
        Rewrite("./MSLoger_log.txt");
    }
}
