package com.example.disignmode.mvp.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.disignmode.mvp.BaseContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by linghaoDo on 2019/7/20
 * description:基类Fragment
 */
public abstract class BaseFragment<P extends BaseContract.Presenter> extends Fragment implements BaseContract.View {
    private View mRootView = null;
    protected Unbinder mRootUnBinder;
    // 标示是否第一次初始化数据
    protected boolean mIsFirstInitData = true;
    protected P mPresenter;

    public abstract P initPresenter();

    public abstract Object setLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        mRootView = rootView;
        ButterKnife.bind(mRootView);
        initWidget(mRootView);
        initData();
        return rootView;
    }

    protected void initData() {

    }

    /**
     * 初始化空间
     *
     * @param mRootView
     */

    protected void initWidget(View mRootView) {

    }

    /**
     * 初始化相关参数
     */
    protected void initArgs(Bundle bundle) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
        initPresenter();
    }

    protected void setPresenter(P presenter) {
        // View中赋值Presenter
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRootUnBinder.unbind();
        if (mPresenter != null)
            mPresenter.detach();
    }
}
