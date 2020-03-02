package com.haodong.pracmodule.db;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;

/**
 * created by linghaoDo on 2019-11-28
 * description:
 * <p>
 * version:
 */
public class DaoSupportFactory {
    private static volatile DaoSupportFactory mFactory;
    private SQLiteDatabase mSqLiteDatabase;

    private DaoSupportFactory() {
        File dbRoot = new File(FileUtils.getCacheRootDirectory()
                + File.separator + "nhdz" + File.separator + "database");
        if (!dbRoot.exists()) {
            dbRoot.mkdir();
        }
        File dbFile = new File(dbRoot, "nhdz.db");
        // 没有就去创建，有就去打开。
        mSqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
    }

    public static DaoSupportFactory getFactory() {
        if (mFactory == null) {
            synchronized (DaoSupportFactory.class) {
                if (mFactory == null) {
                    mFactory = new DaoSupportFactory();
                }
            }
        }
        return mFactory;
    }

    /**
     *
     * @param clazz
     * @param <T> 数据结构
     * @return
     */
    public <T> IDaoSupport<T> getDao(Class<T> clazz) {
        IDaoSupport<T> daoSupport = new DaoSupport<>();
        daoSupport.init(mSqLiteDatabase, clazz);
        return daoSupport;
    }
}
