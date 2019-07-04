package com.example.disignmode.mvp;

/**
 * describe :
 * date on 2019/6/28
 * author linghailong
 * email 105354999@qq.com
 */
public class LoginPresenter extends BasePresenter<LoginConstract.View> implements LoginConstract.Presenter{
    public LoginPresenter(LoginConstract.View mView) {
        super(mView);
    }
    @Override
    public void queryName(int id) {
    }
}
