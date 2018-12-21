package com.suremoon.dbcheck.ctrl;

import com.suremoon.dbcheck.db_about.DBLoader;
import com.suremoon.dbcheck.db_about.TableData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckCtrl {

    private String DBPath = "./db/shop.db", scriptDirectory = "./scripts",
            logFile = "./check_log/check.log", className = "org.sqlite.JDBC";
    private DBLoader loader;
    private static CheckCtrl cc = null;

    protected CheckCtrl() {
    }

    public synchronized static CheckCtrl getCheckCtrl() {
        if (cc == null) {
            return cc = new CheckCtrl();
        }
        return cc;
    }

    public void doCheck(String configPath) throws SQLException, IOException, ClassNotFoundException {
        initCfg(configPath);
        loader = new DBLoader(className, DBPath);
        File f = new File(logFile);
        if (f.isDirectory()) {
            f = new File(logFile + "/check.log");
            System.err.printf("log file is a directory, now set as %s\n", f.getPath());
        } else {
            File parent = f.getParentFile();
            parent.mkdirs();
        }
        Loger.init(f);
        File base = new File(scriptDirectory);
        if (!base.exists()) {
            System.err.printf("script directory [%s] not exist. existing...", scriptDirectory);
            return;
        }
        if (!base.isDirectory()) {
            System.err.printf("config for script [%s] not directory", base.getPath());
            return;
        }
        System.out.println("=============================start check================================");
        fileLoop(base);
        System.out.println("=============================check end =================================");
    }


    private void fileLoop(File file) throws SQLException {
        if (file.isDirectory() && !file.getName().equals("libs")) {
            File files[] = file.listFiles();
            for (File f : files) {
                fileLoop(f);
            }
            return;
        }
        String name = file.getName();
        if (name.endsWith(".js")) {
            String tableName = name.substring(0, name.length() - 3);
            TableData data = loader.getTableData(tableName);
            new CheckTable(file, name, data.getResultSet(), new DBChecker(tableName, loader)).doCheck();
            data.close();
        }
    }

    private void initCfg(String configPath) throws FileNotFoundException {
        File cfgFile = new File(configPath);
        if (!cfgFile.exists()) {
            System.err.println("config file not exist! try use default config.\n" +
                    "dbPath = ./db/shop.db\n" +
                    "scriptDirectory = ./scripts\n" +
                    "logFile = ./check_log/check.log\n");
            return;
        }
        Scanner jin = new Scanner(new FileInputStream(cfgFile));
        int lineNum = 0;
        while (jin.hasNext()) {
            ++lineNum;
            String line = jin.nextLine(), lineTrim = line.trim();
            if (lineTrim.length() == 0 || lineTrim.charAt(0) == '#') {
                continue;
            }
            String pair[] = line.split("=");
            if (pair.length < 2) {
                System.err.printf("the config in file [%s], line[%d] not right. config is: %s", cfgFile, lineNum, line);
            }
            String name = pair[0].trim().toLowerCase(), value = line.substring(pair[0].length() + 1).trim();
            switch (name) {
                case "dbpath":
                case "db_path":
                    this.DBPath = value;
                    break;
                case "scriptdirectory":
                case "script_directory":
                    this.scriptDirectory = value;
                    break;
                case "logfile":
                case "log_file":
                    this.logFile = value;
                    break;
                case "classname":
                case "class_name":
                    this.className = value;
                    break;
                default:
                    System.err.println("undeal name: " + name);
            }
        }
    }
}
