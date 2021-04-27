package com.example.disignmode.myeventbus;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.disignmode.R;

import org.greenrobot.eventbus.EventBus;

public class Event2Activity extends AppCompatActivity {
    private EditText etv;
    private Button btnSetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event2);
        etv=findViewById(R.id.event2_etv_edit_data);
        btnSetData=findViewById(R.id.event2_set_data);
        btnSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent(etv.getText().toString()));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
