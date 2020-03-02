package com.haodong.pracmodule.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * created by linghaoDo on 2019-11-28
 * description:
 * <p>
 * version:
 */
public interface IDaoSupport<T> {
    void init(SQLiteDatabase sqLiteDatabase,Class<T> clazz);

    /**
     * 插入数据
     * @param t
     * @return
     */
    public long insert(T t);

    public void insert(List<T> datas);



    int delete(String whereClause,String... wwhereArgs);
    int update(T obj,String whereClause,String... whereArgs);
}
