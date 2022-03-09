package com.haodong.pracmodule.myipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.example.haodong.common.util.FFLog;
import com.example.haodong.common.util.LogUtil;
import com.haodong.pracmodule.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Author: tangyuan
 * Time : 2022/3/8
 * Description:
 */
public class LocalActivity extends AppCompatActivity {
    ServiceConnection connection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                /*获取远端代理对象*/
                IWeb2Main serverBinder = IWeb2Main.Stub.asInterface(service);
                try {
                    serverBinder.handleWebAction("local", "ss", localCallback);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalActivity.this, WebService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    private ICallback.Stub localCallback = new ICallback.Stub() {
        @Override
        public void onResult(int responseCode, String actionName, String response) throws RemoteException {
            FFLog.i("responseCode-->" + responseCode + "actionName-->" + actionName + "response==>" + response);

        }
    };
}
