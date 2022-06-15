package com.abc.code.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;


public class DaoHelper {

    private static DaoHelper daoHelper;
    private Dao<UserBean, Integer> planDao;


    private DaoHelper(Context mContext) {
        try {
            planDao = DatabaseHelper.getInstance(mContext).getPlanDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized static DaoHelper getInstance(Context mContext) {

        if (daoHelper == null) {
            daoHelper = new DaoHelper(mContext);
        }
        return daoHelper;
    }

    public UserBean registerUser(UserBean userBean) {
        try {
            UserBean ifNotExists = planDao.createIfNotExists(userBean);
            return ifNotExists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public UserBean queryUser(String userName, String passWord) {
        try {
            QueryBuilder<UserBean, Integer> queryBuilder = planDao.queryBuilder();
            queryBuilder.where().eq("userName",userName).and().eq("passWord",passWord);
            UserBean userBean = queryBuilder.queryForFirst();
            return userBean;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public UserBean updateUser(UserBean userBean) {
        try {
            QueryBuilder<UserBean, Integer> queryBuilder = planDao.queryBuilder();
            queryBuilder.where().eq("id",userBean.getId());
            UserBean userBean1 = queryBuilder.queryForFirst();
            userBean1.setUserName(userBean.getUserName());
            planDao.update(userBean1);
            return userBean;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public UserBean updateUserPass(UserBean userBean) {
        try {
            QueryBuilder<UserBean, Integer> queryBuilder = planDao.queryBuilder();
            queryBuilder.where().eq("id",userBean.getId());
            UserBean userBean1 = queryBuilder.queryForFirst();
            userBean1.setPassWord(userBean.getPassWord());
            planDao.update(userBean1);
            return userBean;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
