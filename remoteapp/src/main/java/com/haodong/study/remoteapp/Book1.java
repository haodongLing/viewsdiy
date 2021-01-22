package com.haodong.study.remoteapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * created by linghaoDo on 2021/1/13
 * description:
 * <p>
 * version:
 */
public class Book1 implements Parcelable {
    public int bookId;
    public String bookName;

    protected Book1(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public static final Creator<Book1> CREATOR = new Creator<Book1>() {
        @Override
        public Book1 createFromParcel(Parcel in) {
            return new Book1(in);
        }

        @Override
        public Book1[] newArray(int size) {
            return new Book1[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(bookId);
        parcel.writeString(bookName);
    }
}
