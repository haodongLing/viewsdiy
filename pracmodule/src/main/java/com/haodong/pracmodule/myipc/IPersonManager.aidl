// IPersonManager.aidl
package com.haodong.pracmodule.myipc;

// Declare any non-default types here with import statements

interface IPersonManager {
    Person getPerson();
    void writePerson(in Person person);
}