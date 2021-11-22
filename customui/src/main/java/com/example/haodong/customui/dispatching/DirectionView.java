package com.example.haodong.customui.dispatching;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.haodong.customui.R;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/6/16
 * Description:
 */
public class DirectionView extends LinearLayout implements View.OnClickListener{
    private Context mContext;
    public DirectionView(Context context) {
        this(context, null);
    }

    public DirectionView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DirectionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext=context;
        LayoutInflater.from(context).inflate(R.layout.direction_view_layout, this);
        findViewById(R.id.direction_up).setOnClickListener(this);
        findViewById(R.id.direction_down).setOnClickListener(this);
        findViewById(R.id.direction_left).setOnClickListener(this);
        findViewById(R.id.direction_right).setOnClickListener(this);
        findViewById(R.id.direction_ok).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.direction_up: {
                Toast.makeText(mContext, "up clicked", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.direction_down: {
                Toast.makeText(mContext, "down clicked", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.direction_left: {
                Toast.makeText(mContext, "left clicked", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.direction_right: {
                Toast.makeText(mContext, "right clicked", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.direction_ok: {
                Toast.makeText(mContext, "ok clicked", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
    private float lastX,lastY=0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX=ev.getX();
                lastY=ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_MOVE){
            //进行移动操作
            int offX = (int) (event.getX() - lastX);
            int offY = (int) (event.getY() - lastY);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)getLayoutParams();
            params.leftMargin = params.leftMargin + offX;
            params.topMargin = params.topMargin+offY;
            setLayoutParams(params);

            return true;
        }
        return super.onTouchEvent(event);
    }
}
