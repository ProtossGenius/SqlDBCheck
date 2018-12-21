package com.suremoon.start_param;

import java.util.HashMap;
import java.util.SplittableRandom;

public class StartParamDeal {
    private HashMap<String, StartParam> params;
    private boolean isWriteHelp;

    private StartParamDeal() {
        params = new HashMap<>();
    }

    private static StartParamDeal spd = new StartParamDeal();

    public static StartParamDeal getStartParamDeal() {
        return spd;
    }

    public StartParam paramInit(String name, String initVal, String desc) {
        StartParam param = new StartParam(initVal, desc);
        params.put(name, param);
        return param;
    }

    public StartParam getParam(String name){
        return params.get(name);
    }

    public void parse(String[] args) {
        PopOnlyQueue<String> stack = new PopOnlyQueue<>(args);
        while (!stack.isEmpty()) {
            String name = stack.pop();
            if (!name.startsWith("-")) {
                continue;
            }
            doParamDeal(name, stack);
        }
        if (isWriteHelp) {
            writeHelp();
            System.exit(0);
        }
    }

    private void doParamDeal(String sParam, PopOnlyQueue<String> queue) {
        if (sParam.equals("-h") || sParam.equals("-help")) {
            isWriteHelp = true;
            return;
        }
        String name;
        int eqaIdx = -1;
        if (sParam.contains("=")) {
            eqaIdx = sParam.indexOf('=');
            name = sParam.substring(1, eqaIdx);
        } else {
            name = sParam.substring(1);
        }
        if (!params.containsKey(name)) {
            System.err.println("Unknow start param: -" + name);
            return;
        }
        StartParam param = params.get(name);
        boolean paramInited = false;
        if (eqaIdx != -1 && eqaIdx != sParam.length() - 1) {
            param.setValue(sParam.substring(eqaIdx + 1, sParam.length()));
            paramInited = true;
        }
        if (!paramInited && !queue.isEmpty() && !queue.topSee().startsWith("-")) {
            param.setValue(queue.pop());
            paramInited = true;
        }
        if (!paramInited) {
            param.setValue("true");
        }
        params.put(name, param);
    }

    private void writeHelp() {
        System.out.println("Startup Params:");
        params.forEach((key, val) -> {
            System.out.println("\t[-" + key + "]:\n" +
                    "\t\tdefault value<" + val.getValue() + ">, describe:<" + val.getDesc() + ">");
        });
        System.out.println("\t[-h/-help]:\n" +
                "\t\tdefault value<nil>, describe:<get startup param list.>");
    }
}
