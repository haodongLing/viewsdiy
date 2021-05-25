package com.example.haodong.customui.day7;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.haodong.customui.R;

/**
 * duscribe:
 * created at 2019/1/30
 * Auther linghailong
 */
public class SeventhActivity extends AppCompatActivity implements LetterSideBar
        .LetterTouchedListener {
    private TextView mLetterTv;
    private LetterSideBar mLetterSideBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_7);
        mLetterTv = (TextView) findViewById(R.id.letter_tv);
        mLetterSideBar = (LetterSideBar) findViewById(R.id.letter_side_bar);
        mLetterSideBar.setOnLetterTouchedListener(this);
    }

    @Override
    public void onTouched(String letter, boolean isTouched) {
        if (isTouched) {
            mLetterTv.setVisibility(View.VISIBLE);
            mLetterTv.setText(letter);
        } else {
            mLetterTv.setVisibility(View.GONE);
        }

    }
}
