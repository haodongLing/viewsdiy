package com.haodong.pracmodule.myipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Field;

/**
 * created by linghaoDo on 2020-02-20
 * description:
 * <p>
 * version:
 */
public class Book2 implements Parcelable {
    private int price;
    private String name;

    public Book2() {
    }

    protected Book2(Parcel in) {
        price = in.readInt();
        name = in.readString();
    }

    public static final Creator<Book2> CREATOR = new Creator<Book2>() {
        @Override
        public Book2 createFromParcel(Parcel in) {
            return new Book2(in);
        }

        @Override
        public Book2[] newArray(int size) {
            return new Book2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
        dest.writeString(name);
    }
    public static void main(String args[]){
        try {
            Class book=Class.forName("com.haodong.pracmodule.myipc.Book2");
            // 封装values
            Field[] fields = book.getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                System.out.println("field.getName()-->"+field.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
