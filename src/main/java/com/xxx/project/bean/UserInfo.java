package com.xxx.project.bean;

// 登录后返回的用户信息
public class UserInfo {
    // 用户id
    private String userId;
    // 用户名
    private String userName;

    //标识
    private Integer mark;

    // 其它信息
    private Object other;


    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }
}
