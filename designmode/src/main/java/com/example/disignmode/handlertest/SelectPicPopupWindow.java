package com.example.disignmode.handlertest;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.disignmode.R;

import butterknife.ButterKnife;

/**
 * created by linghaoDo on 2020-03-19
 * description:
 * <p>
 * version:
 */

public class SelectPicPopupWindow extends PopupWindow implements View.OnTouchListener, View.OnKeyListener {
    private Context mContext;
    private View rootView;

    public SelectPicPopupWindow(Context context) {

        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        rootView = inflater.inflate(R.layout.popupwindow_selectpic, null);
        setContentView(rootView);
        ButterKnife.bind(rootView);
        //设置高度和宽度。
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        //设置动画效果
        //当单击Back键或者其他地方使其消失、需要设置这个属性。
        rootView.findViewById(R.id.btn_popup_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPicPopupWindow.this.dismiss();
            }
        });
        rootView.setOnTouchListener(this);
        rootView.setOnKeyListener(this);
        rootView.setFocusable(true);
        rootView.setFocusableInTouchMode(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }

    //点击外部popup消失
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int height = rootView.findViewById(R.id.linearlayout_window).getTop();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (y < height) {
                dismiss();
            }
        }
        return true;
    }

    //点back键消失
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.isShowing()) {
            this.dismiss();
            return true;
        }
        return false;
    }


    private OnWindowItemClickListener listener;

    public void setOnWindowItemClickListener(OnWindowItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnWindowItemClickListener {
        void onClickTakePhoto();

        void onClickSelectPic();
    }
}