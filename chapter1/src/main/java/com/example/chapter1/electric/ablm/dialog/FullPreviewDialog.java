package com.example.chapter1.electric.ablm.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.chapter1.R;

/**
 * description:
 * author: linghailong
 * date: 2019/3/22
 */
public class FullPreviewDialog extends AlertDialog {
    private Context mContext;

    public FullPreviewDialog(Context context) {
        this(context, R.style.dialog);
    }

    public FullPreviewDialog(Context context, int themeResId) {
        super(context, R.style.dialog);
        mContext = context;
    }

    @Override
    public void create() {
        super.create();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_full, null);
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        //设置dialog的位置在底部
        lp.gravity = Gravity.CENTER;
        //设置dialog的动画
        lp.windowAnimations = R.style.BottomDialog;
        dialogWindow.setAttributes(lp);
        //设置背景透明
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogWindow.setAttributes(lp);
        dialogWindow.setContentView(view);
    }
    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
// @Nullable Bundle savedInstanceState) {
//
//        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        Window window = this.getDialog().getWindow();
//        //去掉dialog默认的padding
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//        //设置dialog的位置在底部
//        lp.gravity = Gravity.CENTER;
//        //设置dialog的动画
//        lp.windowAnimations = R.style.BottomDialog;
//        window.setAttributes(lp);
//        window.setBackgroundDrawable(new ColorDrawable());
//        //设置背景透明
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        View view=LayoutInflater.from(getContext()).inflate(R.layout.dialog_full,container,false);
//
//        initView(view);
//        return view;
//    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//
//    }
//
//    private void initView(View view) {
//
//    }
//
//    @Override
//    public void setStyle(int style, int theme) {
//        super.setStyle(style, theme);
//    }
}
