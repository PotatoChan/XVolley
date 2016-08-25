package com.chenjiarun.xvolley.net;

/**
 * 作用：
 * 创建者：陈佳润
 * 创建日期：16/5/3
 * 更新历史：
 */
public class StatusPair {

    private long code;

    private String msg;

    public StatusPair(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
