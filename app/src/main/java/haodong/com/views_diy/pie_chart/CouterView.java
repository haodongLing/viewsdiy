package haodong.com.views_diy.pie_chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @auther linghaoDo QQ:1052354999
 * Created on 2018/10/12
 * @Describe: 计数器View，这个View可以响应用户的点击事件，并自动记录一共点击了多少次。新建一个CounterView继承自View
 */
public class CouterView extends View implements View.OnClickListener{
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    //矩形
    private Rect mBounds;
    private int mCount=0;
    public CouterView(Context context) {
        super(context);
        init();
    }

    public CouterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CouterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(30);
        String text=String.valueOf(mCount);
        mPaint.getTextBounds(text,0,text.length(),mBounds);
        float textWidth=mBounds.width();
        float textHeight=mBounds.height();
        canvas.drawText(text,getWidth() / 2 - textWidth / 2, getHeight() / 2
                + textHeight / 2, mPaint);
    }

    private void init(){
        mBounds=new Rect();
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }
}
