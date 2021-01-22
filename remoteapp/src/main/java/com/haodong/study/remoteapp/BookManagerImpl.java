package com.haodong.study.remoteapp;

import android.os.RemoteException;

import java.util.List;

/**
 * created by linghaoDo on 2021/1/13
 * description:
 * <p>
 * version:
 */
public class BookManagerImpl extends IBookManager.Stub {
    @Override
    public void addBook(Book1 book) throws RemoteException {

    }

    @Override
    public List<Book1> getBookList() throws RemoteException {
        return null;
    }
}
