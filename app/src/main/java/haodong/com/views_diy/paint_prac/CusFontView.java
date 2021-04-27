package haodong.com.views_diy.paint_prac;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author linghailong
 * @date on 2018/11/2
 * @email 105354999@qq.com
 * @describe :
 */
public class CusFontView extends View {
    private static final String TEXT="哈哈哈";

    private Paint mPaint;
    /**文本测量*/
    private Paint.FontMetrics mFontMetrics;
    private Path mPath=null;


    public CusFontView(Context context) {
        super(context);

    }

    public CusFontView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CusFontView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化画笔
     */
    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(20);
        mPaint.setColor(Color.YELLOW);
        mFontMetrics=mPaint.getFontMetrics();

        mPath=new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias( true );
        mPaint.setStrokeWidth( 100 );
        mPaint.setColor(Color.parseColor("#00ff00") );
        // 线帽，即画的线条两端是否带有圆角，butt，无圆角
        mPaint.setStrokeCap( Paint.Cap.BUTT );
        canvas.drawLine( 100,100,400, 100, mPaint );

        mPaint.setColor(Color.parseColor("#ff0000") );
        // 线帽，即画的线条两端是否带有圆角，ROUND，圆角
        mPaint.setStrokeCap( Paint.Cap.ROUND );
        canvas.drawLine( 100,300,400, 300, mPaint );

        mPaint.setColor(Color.parseColor("#0000ff") );
        // 线帽，即画的线条两端是否带有圆角，SQUARE，矩形
        mPaint.setStrokeCap( Paint.Cap.SQUARE );
        canvas.drawLine( 100,450,400, 450, mPaint );

        mPaint.setAntiAlias( true );
        mPaint.setStrokeWidth( 20 );
        mPaint.setStyle(Paint.Style.STROKE ); // 默认是填充 Paint.Style.FILL
        mPaint.setColor( Color.parseColor("#0000ff") );

        mPath.moveTo( 100, 600 ); // 路径path默认是在原点(0,0)，当前移植到(100,100)
        mPath.lineTo( 400, 600 );
        mPath.lineTo( 200, 700 );
        mPaint.setStrokeJoin(Paint.Join.BEVEL);

        canvas.drawPath( mPath, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
