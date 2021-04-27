package haodong.com.views_diy.prac_point;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @auther linghaoDo QQ:1052354999
 * Created on 2018/10/14
 * @Describu:
 */
public class MyViewEx extends MyView {
    // 设置需要用到的变量
    public static final float RADIUS = 100f;// 圆的半径 = 100
    private Paint mPaint;// 绘图画笔

    private String color;
    // 设置背景颜色属性

    // 设置背景颜色的get() & set()方法
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        // 将画笔的颜色设置成方法参数传入的颜色
        invalidate();
        // 调用了invalidate()方法,即画笔颜色每次改变都会刷新视图，然后调用onDraw()方法重新绘制圆
        // 而因为每次调用onDraw()方法时画笔的颜色都会改变,所以圆的颜色也会改变
    }


    public MyViewEx(Context context) {
        super(context);
    }

    public MyViewEx(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MyViewEx(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(){
        // 初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(500, 500, RADIUS, mPaint);
    }
}
