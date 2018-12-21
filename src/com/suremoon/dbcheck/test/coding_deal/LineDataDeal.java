package com.suremoon.dbcheck.test.coding_deal;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LineDataDeal {
    public static void main(String[] args) throws Exception{
        String srcPath = "./src/com/suremoon/db_about/LineData.java";
        Scanner jin = new Scanner(new BufferedInputStream(new FileInputStream(srcPath)));
        ArrayList<String> toWrite = new ArrayList<>();
        String funcName = "", paramName = "";
        while(jin.hasNext()){
            String line = jin.nextLine(), lineTrim = line.trim();
            if (lineTrim.startsWith("@")){
                continue;
            }
            if(lineTrim.startsWith("return")){
                toWrite.add("\t\treturn resultSet." + funcName + "(" + paramName + ");");
            }else {
                toWrite.add(line );
            }
            if(lineTrim.startsWith("public")){
                Scanner toScan = new Scanner(line);
                toScan.next();//public
                toScan.next();//type
                funcName = toScan.next().split("[(]")[0];
                paramName = toScan.next().split("[)]")[0];
                System.out.println(funcName +" (" + paramName + ")");
            }
        }
        FileWriter fw = new FileWriter(srcPath);
        for (String str : toWrite) {
            fw.write(str + "\n");
        }
        fw.close();
    }
}
