package com.example.chapter1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * @Auther linghailong
 * created at 2018/12/12
 * @duscribe:
 */
public class SpiderView extends View {
//    private Paint radarPaint=null;
//    private Paint valuePaint=null;

    /*雷达相关*/
    /*网格的最大半径*/
    private float radius = 0;
    private int centerX = 0;
    private int centerY = 0;
    /*数据个数*/
    private int count = 8;
    /*每个扇形的弧度*/
    private float angle = (float) (Math.PI * 2 / count);
    /*各标题*/
    private String[] titles = {"旅游", "吃饭", "购物", "娱乐", "会友", "转账", "红包", "看病"};
    // 各维度分值
    private double[] data = {100, 60, 75, 100, 50, 30, 70};
    // 刻度值
    private String[] graduationValue = {"0", "10", "20", "30", "40", "50", "60", "70", "80",
            "90", "100"};
    // 数值最大值
    private float maxValue = 100;
    // 数据区画笔
    private Paint valuePaint;
    // title画笔
    private Paint titlePaint;
    // 雷达区画笔
    private Paint mainPaint;
    // 刻度值画笔
    private Paint graduationPaint;
    //判断点的所在区域的临界值，根据临界值设置点的颜色
    //100~70（包含）之间绿色，30（不包含）~70（不包含）橙色，小于等于30红色
    private float curR4;
    private float curR7;
    private float curR10;
    private int ten;

    public SpiderView(Context context) {
        super(context);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        ten = (int) (0.0094 * screenWidth);
        int forty = (int) (0.036 * screenWidth);
        count = Math.min(data.length, titles.length);

        /*初始化雷达区的画笔*/
        mainPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.parseColor("#6bd18e"));
        // 初始化数据区画笔
        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.parseColor("#00BF33"));
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        // 初始化title画笔
        titlePaint = new Paint();
        titlePaint.setTextSize(forty);
        titlePaint.setStyle(Paint.Style.FILL);
        titlePaint.setColor(Color.BLACK);
        // 初始化刻度值画笔
        graduationPaint = new Paint();
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        graduationPaint.setTypeface(font);
        graduationPaint.setTextSize(18);
        graduationPaint.setStyle(Paint.Style.FILL);
        graduationPaint.setColor(Color.parseColor("#D0D0D0"));

    }

    /**
     * 获取数据
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w, h) / 2 * 0.7f;
        //中心坐标
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCircle(canvas);
        drawLines(canvas);
        drawGraduationValue(canvas);
        drawRegion(canvas);
    }


    /**
     * 绘制区域
     *
     * @param canvas Canvas
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }

            double sx = Math.PI
                    * ((x - centerX) * (x - centerX) + (y - centerY)
                    * (y - centerY));
            double s1 = Math.PI * curR4 * curR4;
            double s2 = Math.PI * curR7 * curR7;
            double s3 = Math.PI * curR10 * curR10;
            if (0 < sx && sx <= s1) {
                valuePaint.setColor(Color.parseColor("#FF2022"));
                canvas.drawCircle(x, y, ten, valuePaint);
            }
            if (s1 < sx && sx <= s2) {
                valuePaint.setColor(Color.parseColor("#FFA10C"));
                canvas.drawCircle(x, y, ten, valuePaint);
            }
            if (s2 < sx && sx <= s3) {
                valuePaint.setColor(Color.parseColor("#00BF33"));
                canvas.drawCircle(x, y, ten, valuePaint);
            }
        }
        path.close();
        valuePaint.setColor(Color.parseColor("#00BF33"));
        // 描边
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);

        // 绘制填充区域的透明度(不需要填充可注释掉下边三行代码)
        valuePaint.setAlpha(127);
        // 绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }

    /**
     * 绘制刻度值
     *
     * @param canvas Canvas
     */
    private void drawGraduationValue(Canvas canvas) {
        Paint.FontMetrics fontMetrics = graduationPaint.getFontMetrics();
        //文本高度
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        //刻度值的X轴坐标
        float graduationX = centerX - fontHeight * 6 / 5;
        //网层与网层之间的距离
        float r = radius / 10;
        for (int j = 0; j < graduationValue.length; j++) {
            float curR = r * j;
            float graduationY = centerY - curR;
            canvas.drawText(graduationValue[j], graduationX, graduationY, graduationPaint);
        }
    }

    /**
     * 绘制直线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.cos(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
            drawTitles(canvas, i, x, y);
        }
    }

    /**
     * 绘制圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        Path path = new Path();
        float r = radius / 10;
        for (int i = 1; i < 11; i++) {
            float curR = r * i;
            curR4 = r * 4;
            curR7 = r * 7;
            curR10 = r * 10;
            for (int j = 1; j < 11; j += 2) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                }
            }
            canvas.drawCircle(centerX, centerY, curR, mainPaint);
        }
    }

    /**
     * 绘制title
     *
     * @param canvas Canvas
     * @param i      index
     * @param x      每条线的终点的X坐标
     * @param y      每条线的终点的Y坐标
     */
    private void drawTitles(Canvas canvas, int i, float x, float y) {
        Paint.FontMetrics fontMetrics = titlePaint.getFontMetrics();
        //文本高度
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        Rect rect = new Rect();
        titlePaint.getTextBounds(titles[i], 0, titles[i].length(), rect);
        //文本的宽度
        int fontWidth = rect.width();
        //在每条线的终点基础之上去确定title的坐标
        float titleX = (float) (x + fontWidth * Math.cos(angle * i)) - (fontWidth / 2);
        float titleY = (float) (y + fontHeight * Math.sin(angle * i)) + ten;
        canvas.drawText(titles[i], titleX, titleY, titlePaint);
    }


    // 设置标题
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    // 设置数值
    public void setData(double[] data) {
        this.data = data;
    }

    public float getMaxValue() {
        return maxValue;
    }

    // 设置最大数值
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    // 设置蜘蛛网颜色
    public void setMainPaintColor(int color) {
        mainPaint.setColor(color);
    }

    // 设置标题颜色
    public void setTextPaintColor(int color) {
        titlePaint.setColor(color);
    }

    // 设置覆盖局域颜色
    public void setValuePaintColor(int color) {
        valuePaint.setColor(color);
    }
}
