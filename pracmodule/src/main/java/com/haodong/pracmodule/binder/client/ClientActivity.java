package com.haodong.pracmodule.binder.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.example.haodong.common.util.FFLog;
import com.haodong.pracmodule.R;
import com.haodong.pracmodule.binder.bean.Person;
import com.haodong.pracmodule.binder.common.IPersonManager;
import com.haodong.pracmodule.binder.common.Stub;
import com.haodong.pracmodule.binder.server.RemoteService;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import java.util.List;

public class ClientActivity extends AppCompatActivity {

    private IPersonManager iPersonManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_binder);

        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction("com.enjoy.binder");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FFLog.i("leo", "------------onClick:" + Thread.currentThread());
                    iPersonManager.addPerson(new Person("leo", 3));
                    List<Person> persons = iPersonManager.getPersonList();
                    FFLog.i("leo", persons.toString() + "," + Thread.currentThread());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            FFLog.i("leo", "onServiceConnected: success");
            iPersonManager = Stub.asInterface(service);// proxy
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            FFLog.i("leo", "onServiceDisconnected: success");
            iPersonManager = null;
        }
    };
}
