package com.example.disignmode.mvp;

/**
 * describe :
 * date on 2019/6/28
 * author linghailong
 * email 105354999@qq.com
 */
public interface LoginConstract {
    interface Presenter extends BaseContract.Presenter {
        void queryName(int id);
    }
    interface View extends BaseContract.View {

    }
}
