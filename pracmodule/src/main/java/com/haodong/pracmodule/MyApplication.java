package com.haodong.pracmodule;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.haodong.pracmodule.db.DaoMaster;
import com.haodong.pracmodule.db.DaoSession;

/**
 * created by linghaoDo on 2021/1/24
 * description:
 * <p>
 * version:
 */
public class MyApplication extends Application {
    public static final String DB_NAME = "test.db";

    @Override
    public void onCreate() {
        super.onCreate();
        initDao();
    }

    DaoSession daoSession;

    private void initDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }
}
