package com.suremoon.ctrl;

import com.suremoon.db_about.DBChecker;
import com.suremoon.db_about.DBLoader;
import com.suremoon.db_about.TableData;

import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckCtrl {

    private String DBPath = "./db/shop.db", scriptDirectory = "./scripts", logFile = "./check_log/check.log";
    private DBLoader loader;
    private static CheckCtrl cc = null;
    protected CheckCtrl() throws IOException, SQLException, ClassNotFoundException {
        initCfg();
        loader = new DBLoader(DBPath);
        File f = new File(logFile);
        if(f.isDirectory()){
            f =new File(logFile + "/check.log");
            System.err.printf("log file is a directory, now set as %s\n", f.getPath());
        }else {
            File parent = f.getParentFile();
            parent.mkdirs();
        }
        Loger.init(f);
    }

    public synchronized static CheckCtrl getCheckCtrl() throws SQLException, IOException, ClassNotFoundException {
        if(cc == null){
            return cc = new CheckCtrl();
        }
        return cc;
    }

    public void doCheck() throws SQLException {
        File base = new File(scriptDirectory);
        if(!base.exists()){
            System.err.printf("script directory [%s] not exist. existing...", scriptDirectory);
            return;
        }
        if(!base.isDirectory()){
            System.err.println("config for script [%s] not directory");
            return;
        }
        fileLoop(base);
    }


    private void fileLoop(File file) throws SQLException {
        if(file.isDirectory()){
            File files[] = file.listFiles();
            for (File f : files) {
                fileLoop(f);
            }
            return;
        }
        String name = file.getName();
        if(name.endsWith(".js")){
            String tableName = name.substring(0, name.length()-3);
            TableData data = loader.getTableData(tableName);
            new CheckTable(file, name, data.getResultSet(), new DBChecker(tableName, loader)).doCheck();
            data.close();
        }
    }

    private void initCfg() throws FileNotFoundException {
        File cfgFile = new File("./configs/check_ctrl.cfg");
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
                default:
                    System.err.println("undeal name: " + name);
            }
        }
    }

}
