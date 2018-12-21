package com.suremoon.start_param;

public class StartParam {
    volatile private String value;
    private String desc;

    protected StartParam(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    protected void setDesc(String desc) {
        this.desc = desc;
    }
}
