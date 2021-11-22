package com.haodong.study.pracarouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.example.haodong.common.util.LogUtil;

/**
 * Author: tangyuan
 * Time : 2021/11/2
 * Description:
 */
@Interceptor(priority = 1)
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getExtras().getBoolean("needLogin")){
//            callback.onInterrupt(new Throwable());
            LogUtil.i("还没有登录，去登陆");
        }else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        LogUtil.i("LoginInterceptor init");
    }
}
