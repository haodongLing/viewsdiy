package com.example.disignmode.mvp;

/**
 * describe :
 * date on 2019/6/26
 * author linghailong
 * email 105354999@qq.com
 */
public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter {
    protected T mView;

    public BasePresenter(T mView) {
        this.mView = mView;
//        this.mView.setPresenter(this);
    }

    @Override
    public T getView() {
        return mView;
    }

    @Override
    public void detach() {
        if (mView != null) {
            this.mView = null;
        }
    }
}
