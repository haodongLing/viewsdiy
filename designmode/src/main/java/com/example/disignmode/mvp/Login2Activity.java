package com.example.disignmode.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.disignmode.R;

public class Login2Activity extends BaseActivity<LoginConstract.Presenter> implements LoginConstract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    }

    @Override
    protected void setContentView() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public LoginConstract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }
//    @Override
//    public void showName() {
//        mPresenter.queryName(1);
//    }

}
