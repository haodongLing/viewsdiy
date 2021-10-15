package io.github.haodongling.feature.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.haodong.common.util.LogUtil;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/9/29
 * Description:
 */
public class FirstActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instrumentation mInstrumentation= (Instrumentation) RefInvoke.getFieldObject(Activity.class,this,"mInstrumentation");
        Instrumentation evilInstrumentation=new EvilInstrumentation(mInstrumentation);
        RefInvoke.setFieldObject(Activity.class,this,"mInstrumentation",evilInstrumentation);
        Button tv = new Button(this);
        tv.setText("测试界面");
        setContentView(tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i();
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
