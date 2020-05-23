package com.xxx.project.bean;

public class Result {
    private Integer code;
    private Object response;
    public Result (Object respone) {
        this.code = 200;
        this.response = respone;
    }
    public Result (Integer code, Object respone) {
        this.code = code;
        this.response = respone;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
