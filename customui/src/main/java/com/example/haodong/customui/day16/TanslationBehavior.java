package com.example.haodong.customui.day16;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;

/**
 * describe:
 * created at 2019/2/15
 * Author linghailong
 */
public class TanslationBehavior extends FloatingActionButton.Behavior {
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull
            FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target,
                                       int axes, int type) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull
            FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int
            dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, type);
    }
}
