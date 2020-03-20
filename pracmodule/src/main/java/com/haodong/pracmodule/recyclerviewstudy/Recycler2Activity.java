package com.haodong.pracmodule.recyclerviewstudy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.example.haodong.common.util.UIUtil;
import com.haodong.pracmodule.R;
import com.haodong.pracmodule.recyclerviewstudy.adapters.Recycler2Adapter;

import java.util.ArrayList;
import java.util.List;

public class Recycler2Activity extends AppCompatActivity {
    private Recycler2Adapter mAdapter;
    private ArrayList<String> mDatas = new ArrayList<>();
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler2);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                    int childCountc
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(getResources().getColor(R.color.Pink));

                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                for (int i = 0; i < parent.getChildCount() - 1; i++) {
                    View view = parent.getChildAt(i);
                    float top = view.getBottom();
                    float bottom = view.getBottom() + UIUtil.convert(Recycler2Activity.this, 2);
                    ;
                    c.drawRect(left, top, right, bottom, paint);
                }

            }

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = UIUtil.convert(Recycler2Activity.this, 2);
            }
        });
        mAdapter = new Recycler2Adapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);
        addData();
        mAdapter.notifyDataSetChanged();


    }

    void addData() {
        for (int i = 0; i < 15; i++) {
            mDatas.add("我是数据==>" + i);
        }

    }
}
