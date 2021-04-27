package com.haodong.myswitch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.haodong.myanimator.R;

public class SwitchButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_button);
        MySwitchButton mySwitchButton=findViewById(R.id.btn_switch_switch);
        mySwitchButton.setmScrollCompletedListener(new MySwitchButton.ScrollCompletedListener() {
            @Override
            public void scrollCompleted() {

            }
        });
    }
}
