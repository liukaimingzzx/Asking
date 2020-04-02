package com.lkm.asking.entity;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String profile;
    private Integer gender;
    private Integer coin;
    private String avater;
    private String lastSign;
    private Integer queCount;
    private Integer ansCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getLastSign() {
        return lastSign;
    }

    public void setLastSign(String lastSign) {
        this.lastSign = lastSign;
    }

    public Integer getQueCount() {
        return queCount;
    }

    public void setQueCount(Integer queCount) {
        this.queCount = queCount;
    }

    public Integer getAnsCount() {
        return ansCount;
    }

    public void setAnsCount(Integer ansCount) {
        this.ansCount = ansCount;
    }
}
