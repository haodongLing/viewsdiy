package com.example.disignmode.mvp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * describe :
 * date on 2019/6/28
 * author linghailong
 * email 105354999@qq.com
 */
public abstract class BaseActivity<P extends BaseContract.Presenter> extends AppCompatActivity implements BaseContract.View {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initPresenter();
        initView();
    }

    protected abstract void setContentView();

    protected abstract void initView();

    public abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }
}
