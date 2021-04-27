package haodong.com.views_diy.prac_remote_views;

import android.animation.ObjectAnimator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import haodong.com.views_diy.R;
import haodong.com.views_diy.prac_point.ColorEvaluator;
import haodong.com.views_diy.prac_point.MyViewEx;

public class DiyPointActivity extends AppCompatActivity {
    private MyViewEx my_view_ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        my_view_ex = (MyViewEx) findViewById(R.id.my_view_ex);
        ObjectAnimator anim = ObjectAnimator.ofObject(my_view_ex, "color", new ColorEvaluator(),
                "#0000FF", "#FF0000");
        // 设置自定义View对象、背景颜色属性值 & 颜色估值器
        // 本质逻辑：
        // 步骤1：根据颜色估值器不断 改变 值
        // 步骤2：调用set（）设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
        // 步骤3：调用invalidate()刷新视图，即调用onDraw（）重新绘制，从而实现动画效果
        anim.setDuration(8000);
        anim.start();

    }
}
