package com.example.haodong.viewday1.day4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.haodong.viewday1.R;

import java.text.DecimalFormat;

/**
 * describe :  获取每日金币进度
 * date on 2021/3/25
 * author linghailong
 */
public class GetCoinProgressView extends View {
    private int curCoin = 0; // 当天获取的金币
    private int totalCoin; // 当天获取的金币上限
    //动画需要的
    private int progressCount;
    private float scale; //进度
    private int bgColor; // 背景颜色
    //背景矩形
    private RectF bgRectF;
    private int progressColor;
    private int textColor;
    private float textSize;
    private Context mContext;
    private float radius;
    private int width;
    private int height;
    private PorterDuffXfermode mPorterDuffXfermode;
    private float baseLineY;
    private boolean isNeedAnim;
    private int topGradColor;
    private int bottomGradColor;
    private Paint textPaint;
    private Paint bgPaint;
    private Paint mProGressPaint;


    public GetCoinProgressView(Context context) {
        this(context, null);
    }

    public GetCoinProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GetCoinProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GetCoinProgressView);
        textColor = ta.getColor(R.styleable.GetCoinProgressView_textColor, Color.parseColor("#823203"));
        bgColor=ta.getColor(R.styleable.GetCoinProgressView_bgColor,Color.parseColor("#D8D8D8"));
        progressColor=ta.getColor(R.styleable.GetCoinProgressView_progressColor,Color.parseColor("#F3E61B"));
        topGradColor=ta.getColor(R.styleable.GetCoinProgressView_topGradColor,Color.parseColor("#F3E61B"));
        bottomGradColor=ta.getColor(R.styleable.GetCoinProgressView_bottomGradColor,Color.parseColor("#E28D0C"));
        textSize=ta.getDimension(R.styleable.GetCoinProgressView_textSize,sp2px(12));
        isNeedAnim=ta.getBoolean(R.styleable.GetCoinProgressView_isNeedAnim,true);
        ta.recycle();
        initPaint();
        
    }

    private void initPaint() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(textSize);
        bgPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(bgColor);
        mProGressPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mProGressPaint.setColor(progressColor);
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取View的宽高
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        //圆角半径
        radius = height / 2.0f;
        //留出一定的间隙，避免边框被切掉一部分
        if (bgRectF == null) {
            bgRectF = new RectF(0, 0, width , height );
        }

        if (baseLineY == 0.0f) {
            Paint.FontMetricsInt fm = textPaint.getFontMetricsInt();
            baseLineY = height / 2 - (fm.descent / 2 + fm.ascent / 2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isNeedAnim){
            progressCount = curCoin;
        }

        if (curCoin == 0) {
            scale = 0.0f;
        } else {
            scale = Float.parseFloat(new DecimalFormat("0.00").format((float) progressCount / (float) totalCoin));
        }

        drawBg(canvas);
        drawText(canvas);

        if(progressCount!=curCoin){
            if(progressCount<curCoin){
                progressCount++;
            }else{
                progressCount--;
            }
            postInvalidate();
        }
    }
    private void drawText(Canvas canvas){
        String scaleText = new DecimalFormat("#%").format(scale);
        float scaleTextWidth = textPaint.measureText(scaleText);
        Bitmap textBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas textCanvas = new Canvas(textBitmap);
        textPaint.setColor(textColor);
        textCanvas.drawText(scaleText, width / 2 - scaleTextWidth / 2, baseLineY, textPaint);
        textPaint.setXfermode(mPorterDuffXfermode);
        textPaint.setColor(Color.WHITE);
        textCanvas.drawRoundRect(
                new RectF(0, 0, (width ) * scale, height ),
                radius, radius, textPaint);
        canvas.drawBitmap(textBitmap, 0, 0, null);
        textPaint.setXfermode(null);
    }


    private void drawBg(Canvas canvas) {
        int round = height/2; //半径
        RectF rectBg = new RectF(0, 0, width, height);
        canvas.drawRoundRect(rectBg, round, round, bgPaint);//绘制 最外面的大 圆角矩形，背景为白色

        float section = curCoin/totalCoin; //进度条的比例
        RectF rectProgressBg = new RectF(0, 0, width*section, height);
        mProGressPaint.setShader(getLinearGradient());
        canvas.drawRoundRect(rectProgressBg, round, round, mProGressPaint); //最左边的圆角矩形

        if (totalCoin != curCoin){ //如果不是100%，绘制第三段矩形
            RectF rectProgressBg2 = new RectF(width*section-round, 0, width*section, height);
            mProGressPaint.setShader(getLinearGradient());
            canvas.drawRect(rectProgressBg2, mProGressPaint);

        }

    }

    private LinearGradient linearGradient;
    private LinearGradient getLinearGradient(){
        if(linearGradient==null){
            linearGradient = new LinearGradient(0, 0,0, height,
                    new int[]{topGradColor,
                    bottomGradColor}, null, Shader.TileMode.CLAMP); //根据R文件中的id获取到color
        }
        return linearGradient;
    }


    private int dipToPx(int dip) {
        float spValue = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    private int sp2px(float spValue) {
        float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }
    public void setTotalAndCurrentCount(int totalCount, int currentCount) {
        this.totalCoin = totalCount;
        if (currentCount > totalCount) {
            currentCount = totalCount;
        }
        this.curCoin = currentCount;
        postInvalidate();
    }

}
