// IBookManager.aidl
package com.haodong.study.remoteapp;

// Declare any non-default types here with import statements
parcelable Book1;


interface IBookManager {
    void addBook(in Book1 book);
    List<Book1> getBookList();
}