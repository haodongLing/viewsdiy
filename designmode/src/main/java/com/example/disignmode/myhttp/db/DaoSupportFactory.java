package com.example.disignmode.myhttp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;

/**
 * describe :
 * date on 2019/5/2
 * author linghailong
 * email 105354999@qq.com
 */
public class DaoSupportFactory {
    private static volatile DaoSupportFactory sFactory;
    // 持有外部数据库的引用
    private SQLiteDatabase mSqLiteDatabase;

    private DaoSupportFactory(){}
    public static DaoSupportFactory getFactory(){
        if (sFactory==null){
            synchronized (DaoSupportFactory.class){
                if (sFactory==null){
                    sFactory=new DaoSupportFactory();
                }
            }
        }
        return sFactory;
    }
    public <T> IDaoSupport<T> getDao(Class<T> clazz) {
        IDaoSupport<T> daoSoupport = new DaoSupport();
        daoSoupport.init(mSqLiteDatabase, clazz);
        return daoSoupport;
    }
    public void init(Context context) {
        // 把数据库放到内存卡里面  判断是否有存储卡 6.0要动态申请权限
        File dbRoot = new File(context.getCacheDir() + File.separator + "database");
        if (!dbRoot.exists()) {
            dbRoot.mkdirs();
        }
        File dbFile = new File(dbRoot, "download.db");

        // 打开或者创建一个数据库
        mSqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
    }
}
