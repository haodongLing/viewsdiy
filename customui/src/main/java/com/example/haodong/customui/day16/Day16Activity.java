package com.example.haodong.customui.day16;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haodong.customui.R;

public class Day16Activity extends AppCompatActivity {
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        mToolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(Day16Activity.this).inflate(R.layout.item_behavior,
                        parent,false);

                // View itemView = View.inflate(BehaviorActivity.this,R.layout.item_behavior,null);
                ViewHolder viewHolder = new ViewHolder(itemView);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 50;
            }
        });
    }
    private class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
