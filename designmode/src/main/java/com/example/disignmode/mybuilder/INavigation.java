package com.example.disignmode.mybuilder;

import android.view.View;
import android.view.ViewGroup;

/**
 * description:导航栏的规范
 * author: linghaoDo
 * date: 2019/3/7
 */
public interface INavigation {
    void createNavigationBar();

    /**
     * 绑定参数
     */
    void attachNavigationParams();

    /**
     * 将 NavigationView添加到父布局
     */
    void attachParent(View navigationBar, ViewGroup parent);
}
