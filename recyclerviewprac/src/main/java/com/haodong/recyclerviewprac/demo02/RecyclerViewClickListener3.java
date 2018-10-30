package com.haodong.recyclerviewprac.demo02;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author linghailong
 * @date on 2018/10/29
 * @email 105354999@qq.com
 * @describe :
 */
public class RecyclerViewClickListener3  implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mOnItemClickListener;
    private GestureDetector mGestureDetector;
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view, int position);

    }

    public RecyclerViewClickListener3(Context context, final RecyclerView recyclerView, final OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
        mGestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView=recyclerView.findChildViewUnder(e.getX(),e.getY());
                if (childView!=null&&mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(childView,recyclerView.getChildLayoutPosition(childView));
                        return true;
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
              View childView=recyclerView.findChildViewUnder(e.getX(),e.getY());
              if (childView!=null&&mOnItemClickListener!=null){
                  mOnItemClickListener.onItemLongClick(childView,recyclerView.getChildLayoutPosition(childView));
              }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
       if (mGestureDetector.onTouchEvent(e)){
           return true;
       }else
       {
           return false;
       }
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
