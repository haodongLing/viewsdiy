package com.example.disignmode.mvp;

/**
 * describe :
 * date on 2019/6/26
 * author linghailong
 * email 105354999@qq.com
 */
public interface BaseContract
{
    interface View{
//        void showName();
//        void setPresenter(T presenter);
//        Presenter initPresenter();
    }
    interface Presenter<T extends BaseContract.View>
    {
        T getView();
        void detach();
    }

}
