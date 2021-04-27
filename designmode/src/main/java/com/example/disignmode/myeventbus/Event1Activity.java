package com.example.disignmode.myeventbus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.disignmode.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Event1Activity extends AppCompatActivity {

    Button btnJump;
    TextView tvShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event1);
        EventBus.getDefault().register(this);
        btnJump = findViewById(R.id.event1_jump);
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Event1Activity.this, Event2Activity.class));
            }
        });
        tvShowData = findViewById(R.id.event1_show_data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void event(MessageEvent messageEvent) {
        tvShowData.setText(messageEvent.getMessage());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }
}
