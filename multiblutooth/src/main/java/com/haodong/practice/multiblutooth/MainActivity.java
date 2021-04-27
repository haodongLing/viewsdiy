package com.haodong.practice.multiblutooth;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.haodong.common.util.LogUtil;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getHtmlFromData(null);
        EditText editText = findViewById(R.id.edit);
        editText.setKeyListener(null);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText.setBackgroundResource( R.drawable.bg_sorting_cancel_sunmi);
                } else {
                    editText.setBackgroundResource(R.drawable.bg_sorting_cancel_gray_sunmi);
                }
            }
        });

        EditText editText2= findViewById(R.id.edit2);
        editText2.setKeyListener(null);
        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText2.setBackgroundResource( R.drawable.bg_sorting_cancel_sunmi);
                } else {
                    editText2.setBackgroundResource(R.drawable.bg_sorting_cancel_gray_sunmi);
                }
            }
        });
        Button button=findViewById(R.id.btn_set_value);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.hasFocus()){
                    editText.setText("afafjal;fajf;a");
                }else {
                    editText2.setText("123456");
                }
            }
        });
    }

    public String getHtmlFromData(String data) {
        LogUtil.e("Main", "getHtmlFromData");
        try {
            String html = "";
            JSONArray jsonArray = new JSONArray(data);
            if (jsonArray.length() == 0) {
                return null;
            }
            return "ss";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
