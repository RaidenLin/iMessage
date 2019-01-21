package com.aiiage.testimnim.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created By HuangQing on 2018/7/23 11:02
 **/
public class UserInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    private String nickname;
    private String account;


    public UserInfo(String nickname, String account) {
        this.nickname = nickname;
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "nickname='" + nickname + '\'' +
                ", account='" + account + '\'' +
                '}';
    }

}
