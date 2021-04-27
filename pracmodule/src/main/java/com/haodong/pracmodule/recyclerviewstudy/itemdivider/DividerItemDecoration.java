package com.haodong.pracmodule.recyclerviewstudy.itemdivider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.example.haodong.common.util.UIUtil;

/**
 * created by linghaoDo on 2019/8/6
 * <p>
 * description:
 */
public abstract class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private Context mContext;

    public DividerItemDecoration(Context mContext) {
        this.mContext = mContext;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //outRect 看源码可知这里只是把Rect类型的outRect作为一个封装了left,right,top,bottom的数据结构,
        //作为传递left,right,top,bottom的偏移值来用的

        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        Divider divider = getDivider(itemPosition);

        if (divider == null) {
            divider = new Divider.Builder().create();
        }
        int left = divider.getLeftSideLine().isHave() ? UIUtil.convert(mContext, divider.getLeftSideLine().getWidthDp()) : 0;
        int top = divider.getTopSideLine().isHave() ? UIUtil.convert(mContext, divider.getTopSideLine().getWidthDp()) : 0;
        int right = divider.getRightSideLine().isHave() ? UIUtil.convert(mContext, divider.getRightSideLine().getWidthDp()) : 0;
        int bottom = divider.getBottomSideLine().isHave() ? UIUtil.convert(mContext, divider.getBottomSideLine().getWidthDp()) : 0;

        outRect.set(left, top, right, bottom);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int itemPosition = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewLayoutPosition();
            Divider divider = getDivider(itemPosition);
            if (divider.getLeftSideLine().isHave()) {
                int lineWidthPx = UIUtil.convert(mContext, divider.getLeftSideLine().getWidthDp());
                int startPaddingPx = UIUtil.convert(mContext, divider.getLeftSideLine().getStartPaddingDp());
                int endPaddingPx = UIUtil.convert(mContext, divider.getLeftSideLine().getEndPaddingDp());
                drawChildLeftVertical(child, c, parent, divider.getLeftSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.getTopSideLine().isHave()) {
                int lineWidthPx = UIUtil.convert(mContext, divider.getTopSideLine().getWidthDp());
                int startPaddingPx = UIUtil.convert(mContext, divider.getTopSideLine().getStartPaddingDp());
                int endPaddingPx = UIUtil.convert(mContext, divider.getTopSideLine().getEndPaddingDp());
                drawChildTopHorizontal(child, c, parent, divider.getTopSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.getRightSideLine().isHave()) {
                int lineWidthPx = UIUtil.convert(mContext, divider.getRightSideLine().getWidthDp());
                int startPaddingPx = UIUtil.convert(mContext, divider.getRightSideLine().getStartPaddingDp());
                int endPaddingPx = UIUtil.convert(mContext, divider.getRightSideLine().getEndPaddingDp());
                drawChildRightVertical(child, c, parent, divider.getRightSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.getBottomSideLine().isHave()) {
                int lineWidthPx = UIUtil.convert(mContext, divider.getBottomSideLine().getWidthDp());
                int startPaddingPx = UIUtil.convert(mContext, divider.getBottomSideLine().getStartPaddingDp());
                int endPaddingPx = UIUtil.convert(mContext, divider.getBottomSideLine().getEndPaddingDp());
                drawChildBottomHorizontal(child, c, parent, divider.getBottomSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
        }
    }

    /**
     * @param child
     * @param c
     * @param parent
     * @param color
     * @param lineWidthPx
     * @param startPaddingPx
     * @param endPaddingPx
     */
    private void drawChildBottomHorizontal(View child, Canvas c, RecyclerView parent, @ColorInt int color,
                                           int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int leftPadding = 0;
        int rightPadding = 0;
        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }

        if (endPaddingPx <= 0)
            rightPadding = endPaddingPx;
        else
            rightPadding = -endPaddingPx;
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int left = child.getLeft() - params.leftMargin + leftPadding;
        int right = child.getRight() + params.rightMargin + rightPadding;
        /*注意，是画divider的线条*/
        int top = child.getBottom() + params.bottomMargin;
        int bottom = top + lineWidthPx;
        mPaint.setColor(color);
        c.drawRect(left, top, right, bottom, mPaint);

    }

    /**
     * @param child
     * @param c
     * @param parent
     * @param color
     * @param lineWidthPx
     * @param startPaddingPx
     * @param endPaddingPx
     */
    private void drawChildTopHorizontal(View child, Canvas c, RecyclerView parent, @ColorInt int color, int lineWidthPx,
                                        int startPaddingPx, int endPaddingPx) {
        int leftPadding = 0;
        int rightPadding = 0;
        if (startPaddingPx <= 0) {
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        int left = child.getLeft() - params.leftMargin + leftPadding;
        int right = child.getRight() + params.rightMargin + rightPadding;
        int bottom = child.getTop() - params.topMargin;
        int top = bottom - lineWidthPx;
        mPaint.setColor(color);
        c.drawRect(left, top, right, bottom, mPaint);
    }

    /**
     * @param child
     * @param c
     * @param parent
     * @param color
     * @param lineWidthPx
     * @param startPaddingPx
     * @param endPaddingPx
     */
    private void drawChildLeftVertical(View child, Canvas c, RecyclerView parent, @ColorInt int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {
        int topPadding = 0;
        int bottomPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getTop() - params.topMargin + topPadding;
        int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        int right = child.getLeft() - params.leftMargin;
        int left = right - lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);

    }

    /**
     * @param child
     * @param c
     * @param parent
     * @param color
     * @param lineWidthPx
     * @param startPaddingPx
     * @param endPaddingPx
     */
    private void drawChildRightVertical(View child, Canvas c, RecyclerView parent, @ColorInt int color, int lineWidthPx, int startPaddingPx, int endPaddingPx) {

        int topPadding = 0;
        int bottomPadding = 0;

        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                .getLayoutParams();
        int top = child.getTop() - params.topMargin + topPadding;
        int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        int left = child.getRight() + params.rightMargin;
        int right = left + lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);

    }

    public abstract @Nullable
    Divider getDivider(int itemPosition);
}
