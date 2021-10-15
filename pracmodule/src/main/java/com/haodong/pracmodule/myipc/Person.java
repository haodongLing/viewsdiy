package com.haodong.pracmodule.myipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: tangyuan
 * Time : 2021/9/30
 * Description:
 */
public class Person implements Parcelable {
    public String name;
    public int age;

    protected Person(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
