package com.example.disignmode;


import android.app.Dialog;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.disignmode.mybuilder.NavigationBar;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout mViewRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dialog dialog = new AlertDialog.Builder(this).create();
        mViewRoot=findViewById(R.id.view_root);
        NavigationBar navigationBar = (NavigationBar) new NavigationBar.Builder(this, R.layout
                .ui_defualt_navigation_bar, mViewRoot)
                .setText(R.id.back_tv,"返回")
                .setOnClickListener(R.id.back_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("Main", "onClick: --->" );
                    }
                })
                .create();
    }
}
