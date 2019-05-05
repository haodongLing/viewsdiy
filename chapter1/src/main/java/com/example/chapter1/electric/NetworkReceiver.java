package com.example.chapter1.electric;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * describe :
 * date on 2019/5/5
 * author linghailong
 * email 105354999@qq.com
 */
public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        ConnectivityManager connectivityManager=context.getApplicationContext().getSystemService(Context
//                .CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
//        if (null==networkInfo||!networkInfo.isConnected()){
//            return;
//        }
    }
}
