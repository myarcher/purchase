package com.commodity.purchase.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commodity.purchase.app.AppAppliction;
import com.commodity.purchase.app.CancelableManage;
import com.commodity.purchase.listener.SimpleListener;
import com.commodity.purchase.until.toasty.Toasty;

import org.xutils.common.Callback;
import org.xutils.x;


/**
 * 所有fragmeng的基类，处理和activity基类类似 ，懒加载 （参考sacyivity）
 *
 * @author
 * @version 1.0
 * @date 2017/5/2
 */

public abstract class SFragment extends Fragment implements SimpleListener {
    // 当前Fragment 是否初始化
    protected boolean isViewInit = false;
    // 当前Fragment 是否可见
    protected boolean isVisible = false;
    // 是否加载过数据
    protected boolean isLoadData = false;
    //fragment对应的 activity
    public SActivity mActivity;
    //全局上下文环境
    public AppAppliction abApplication = null;
    //fragment 的页面请求防止内存泄漏
    public CancelableManage mCancelableManage;
    // 构造函数，一般使用带参数的
     private View mView;
    public SFragment() {
    }

    public SFragment(SActivity baseUi) {
        this.mActivity = baseUi;
        abApplication = AppAppliction.applictions;
        getArguments();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (SActivity) activity;
    }

    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisible = isVisibleToUser;
        preLoadData(false);
    }

    protected abstract void loadData();//数据请求

    public void preLoadData(boolean forceLoad) {//预加载
        //只有在页面可见，view已经初始化好，没有加载过数据，或者强制刷新的时候在加载数据
        if (isViewInit && isVisible && (!isLoadData || forceLoad)) {
            loadData();
            isLoadData = true;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.isViewInit = true;//view已经初始化好
        initData(savedInstanceState);
        // 防止一开始加载的时候未 调用 preLoadData 方法， 因为setUserVisibleHint 比 onActivityCreated 触发 前
        if (getUserVisibleHint()) {
            preLoadData(false);
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (null != parent) {
                parent.removeView(mView);
            }
        } else {
                mView= x.view().inject(this, inflater, container);
                mCancelableManage = new CancelableManage();
                initView();
        }
        return mView;
    }

    public void initView() {//初始化数据

    }



    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();
    }
    /**
     * 添加请求
     *
     * @param
     */
    public void addmCancelable(Callback.Cancelable cancelable) {
        mCancelableManage.add(cancelable);
    }

    public void showMess(String mess) {
        Toasty.normal(mActivity,mess).show();
    }

    @Override
    public void onPause() {
        mCancelableManage.clear();
        super.onPause();
    }

    @Override
    @CallSuper
    public void onDestroy() {
       
        super.onDestroy();
    }



    @Override
    public void callBack(long code, long stat, Object value1, Object value2) {

    }
}
