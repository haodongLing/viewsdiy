package com.haodong.recyclerviewprac;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author linghailong
 * @date on 2018/10/28
 * @email 105354999@qq.com
 * @describe : 对Adapter中使用到的点击事件进行优化、解耦。在实现方法二的RecyclerViewClickListener的时候，
 * 在内部对事件的实现了单击、长按的判断，但是这个长按事件不是标准的，只有松开手指的时候才会触发长按事件，
 * 这也算是一点瑕疵，同时如果要增加别的事件，比如说双击事件，则需要增加相应的逻辑，如果需要判断的事件种类变多则会给我们的代码编写带来困难，
 * 那么有没有更加简便的方法呢？其实安卓SDK为我们提供了一个手势检测类：GestureDetector来处理各种不同的手势，
 * 那么我们完全可以利用GestureDetector来对方法二进行改进。


 */
public class RecyclerViewClickListener2 implements RecyclerView.OnItemTouchListener{
    private GestureDetector mGestureDetector;
    private OnItemClickListener mListener;
    private Context mContext;

    public interface OnItemClickListener
    {
        void onItemClick(View view ,int position);

        void onItemLongClick(View view,int position);
    }

    public RecyclerViewClickListener2(Context context, final RecyclerView recyclerView, final OnItemClickListener listener) {
        this.mListener =listener;
        mGestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            /**
             * 单击事件
             * @param e
             * @return
             */
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                /** 通过手指当前的点击坐标获取当前的childView*/
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if (childView!=null&& mListener!=null){
                    mListener.onItemClick(childView,recyclerView.getChildLayoutPosition(childView));
                    return true;
                }
                return false;
            }

            /**
             * 长按事件
             * @param e
             */
            @Override
            public void onLongPress(MotionEvent e) {
                View childView=recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView!=null&&mListener!=null){
                    mListener.onItemLongClick(childView,recyclerView.getChildLayoutPosition(childView));
                }
                super.onLongPress(e);
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        // 把事件交给GestureDetector处理
        if(mGestureDetector.onTouchEvent(e)){
            return true;
        }else
            return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
