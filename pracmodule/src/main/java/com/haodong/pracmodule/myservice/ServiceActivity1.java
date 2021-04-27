package com.haodong.pracmodule.myservice;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.haodong.common.util.LogUtil;
import com.haodong.pracmodule.R;

public class ServiceActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service1);
        //连续启动Service
        Intent intentOne = new Intent(this, TestOneService.class);
        startService(intentOne);
        Intent intentTwo = new Intent(this, TestOneService.class);
        startService(intentTwo);
        Intent intentThree = new Intent(this, TestOneService.class);
        startService(intentThree);

        //停止Service
        Intent intentFour = new Intent(this, TestOneService.class);
        stopService(intentFour);

        //再次启动Service
        Intent intentFive = new Intent(this, TestOneService.class);
        startService(intentFive);

        LogUtil.i("after StartService");
    }
}
