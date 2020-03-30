package com.haodong.pracmodule.myipc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.haodong.pracmodule.R;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    Button btnStartLocal;
    Button btnCloseLocal;
    Button btnCloseRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btnStartLocal = findViewById(R.id.btn_start_local_service);
        btnStartLocal.setOnClickListener(this);
        btnCloseLocal = findViewById(R.id.btn_close_local_service);
        btnCloseLocal.setOnClickListener(this);
        btnCloseRemote = findViewById(R.id.btn_start_remote_service);
        btnCloseRemote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.btn_start_remote_service) {
            stopService(new Intent(this, RemoteService.class));

        } else if (id == R.id.btn_start_local_service) {
            startService(new Intent(this, LocalService.class));
        } else if (id == R.id.btn_close_local_service) {
            stopService(new Intent(this, LocalService.class));
        }
    }
}
