package com.haodong.pracmodule.myipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.haodong.pracmodule.R;

public class IPC1Activity extends AppCompatActivity {
    UserAidl mUserAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc1);

    }

    private void initData() {
        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mUserAidl = UserAidl.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(new Intent(IPC1Activity.this, MessageService.class), connection, Context.BIND_AUTO_CREATE);
    }

}
