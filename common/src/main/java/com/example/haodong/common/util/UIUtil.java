package com.example.haodong.common.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * created by linghaoDo on 2019/8/6
 * <p>
 * description:UI相关的工具类
 */
public class UIUtil {
    private UIUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int convert(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
