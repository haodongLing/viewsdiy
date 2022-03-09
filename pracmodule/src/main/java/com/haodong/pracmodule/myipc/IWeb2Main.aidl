// IWeb2Main.aidl
package com.haodong.pracmodule.myipc;

// Declare any non-default types here with import statements
import com.haodong.pracmodule.myipc.ICallback;
interface IWeb2Main {
    void handleWebAction(String actionName,String jsonParams,in ICallback callback);
}