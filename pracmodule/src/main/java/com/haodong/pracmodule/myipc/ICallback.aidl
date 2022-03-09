// ICallback.aidl
package com.haodong.pracmodule.myipc;

// Declare any non-default types here with import statements

interface ICallback {
     void onResult(int responseCode, String actionName, String response);
}