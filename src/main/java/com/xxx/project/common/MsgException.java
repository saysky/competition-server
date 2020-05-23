package com.xxx.project.common;

// 自定义异常
public class MsgException extends Exception {
    private String messsage;
    private int code = 400;

    public MsgException(String message, int code) {
        this.messsage = message;
        this.code = code;
    }

    public MsgException(String message) {
        this.messsage = message;
    }

    @Override
    public String getMessage() {
        return messsage;
    }

    public int getCode() {
        return code;
    }
}
