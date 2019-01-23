package com.example.haodong.viewday1;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author linghailong
 * @date on 2019/1/24
 * @email 105354999@qq.com
 * @describe :
 */
public class SizeUtil {
    public static int dp2Px(Context context, int dpi) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, context
                .getResources().getDisplayMetrics());
    }

    public static int sp2Px(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources
                ().getDisplayMetrics());
    }

    public static int px2Sp(Context context, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context
                .getResources().getDisplayMetrics());
    }
}
