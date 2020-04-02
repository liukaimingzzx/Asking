package com.lkm.asking.entity;

public class QuestionBox {
    private Integer boxId;
    private String username;
    private String boxPassword;
    private String boxTitle;
    private String boxContent;
    private String boxTime;
    private Integer boxCount;
    private String nickname;
    private String userAvater;

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBoxPassword() {
        return boxPassword;
    }

    public void setBoxPassword(String boxPassword) {
        this.boxPassword = boxPassword;
    }

    public String getBoxTitle() {
        return boxTitle;
    }

    public void setBoxTitle(String boxTitle) {
        this.boxTitle = boxTitle;
    }

    public String getBoxContent() {
        return boxContent;
    }

    public void setBoxContent(String boxContent) {
        this.boxContent = boxContent;
    }

    public String getBoxTime() {
        return boxTime;
    }

    public void setBoxTime(String boxTime) {
        this.boxTime = boxTime;
    }

    public Integer getBoxCount() {
        return boxCount;
    }

    public void setBoxCount(Integer boxCount) {
        this.boxCount = boxCount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserAvater() {
        return userAvater;
    }

    public void setUserAvater(String userAvater) {
        this.userAvater = userAvater;
    }
}