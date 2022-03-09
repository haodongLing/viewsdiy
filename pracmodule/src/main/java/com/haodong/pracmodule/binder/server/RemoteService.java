package com.haodong.pracmodule.binder.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.haodong.common.util.FFLog;
import com.haodong.pracmodule.binder.bean.Person;
import com.haodong.pracmodule.binder.common.Stub;

import androidx.annotation.Nullable;



import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service {

    private ArrayList<Person> persons;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        Log.e("LeoAidlService", "success onBind");
        return iBinder;
    }

    private IBinder iBinder = new Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            FFLog.i(person.toString()+"");
            persons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return persons;
        }
    };
}
