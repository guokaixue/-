package com.abc.code.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class UserBean implements Serializable {

    @DatabaseField( generatedId = true, canBeNull = false)
    private int id;

    @DatabaseField
    private String userName;

    @DatabaseField
    private String passWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord == null ? "" : passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
