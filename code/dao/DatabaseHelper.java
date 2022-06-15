package com.abc.code.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static DatabaseHelper dataBaseHelper;
    private Dao<UserBean, Integer> planBeans;
    public static final String DB_NAME = "plan.db"; // 数据库文件名
    public static final int DB_VERSION = 1; // 数据库版本号


    private DatabaseHelper(Context context) {
        super(context,  DB_NAME, null, DB_VERSION);
    }

    public synchronized static DatabaseHelper getInstance(Context mContext) {

        if (dataBaseHelper == null) {
            dataBaseHelper = new DatabaseHelper(mContext);
        }
        return dataBaseHelper;
    }

    public Dao<UserBean, Integer> getPlanDao() throws SQLException {
        if (planBeans == null) {
            planBeans = getDao(UserBean.class);
        }
        return planBeans;
    }


    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i("hedb", "SQLiteDatabase onCreate: ");
            TableUtils.createTableIfNotExists(connectionSource, UserBean.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库更新
     */
    @Override
    public void onUpgrade(SQLiteDatabase arg0, ConnectionSource connectionSource, int arg2, int arg3) {
        Log.i("hedb", "SQLiteDatabase onUpgrade: arg2:" + arg2 + "arg3:  " + arg3);
        try {
            TableUtils.createTableIfNotExists(connectionSource, UserBean.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }

}
