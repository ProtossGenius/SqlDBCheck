package com.suremoon.test.lines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Water Moon on 2017/9/17.
 */
public class GetProjectLines {
    public static void main(String[] args) throws IOException {
        File f = new File("./src");
        getLines(f);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MSLoger.write("GetProjectLines.txt", "[" + df.format(new Date()) + "]");
        MSLoger.write("GetProjectLines.txt", "总行数：        " + totalLines);
        MSLoger.write("GetProjectLines.txt", "非空行总行数：  " + notNullTotalLines);
        MSLoger.write("GetProjectLines.txt", "非行注释总行数：" + notLineCommentsTotalLines);
        MSLoger.write("GetProjectLines.txt", "空行数：        " + (totalLines - notNullTotalLines));
        System.out.println("总行数：        " + totalLines);
        System.out.println("非空行总行数：  " + notNullTotalLines);
        System.out.println("非行注释总行数：" + notLineCommentsTotalLines);
        System.out.println("空行数：        " + (totalLines - notNullTotalLines));
    }
    static int totalLines = 0, notNullTotalLines, notLineCommentsTotalLines;
    public static int getFileLines(File f) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        int lines = 0;
        String inp;
        while((inp = br.readLine()) != null){
            if(!inp.trim().equals("")) {
                ++notNullTotalLines;
                if (!inp.trim().startsWith("//")) ++notLineCommentsTotalLines;
            }
            ++lines;
        }
        return lines;
    }
    public static void getLines(File f) throws IOException {
        if(f.isDirectory()){
            File fl[] = f.listFiles();
            for(File it: fl){
                getLines(it);
            }
        }else if(f.getName().endsWith(".java")){
            totalLines += getFileLines(f);
        }
    }
}
