package com.example.haodong.viewday1.day14;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * describe :
 * date on 2019/5/6
 * author linghailong
 * email 105354999@qq.com
 */
public class VDListView extends FrameLayout {
    public VDListView(@NonNull Context context) {
        this(context,null);
    }

    public VDListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VDListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
