package com.commodity.purchase.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.commodity.purchase.app.AppAppliction;
import com.commodity.purchase.app.AppManager;
import com.commodity.purchase.app.CancelableManage;
import com.commodity.purchase.listener.SimpleListener;
import com.commodity.purchase.until.toasty.Toasty;

import org.xutils.common.Callback;
import org.xutils.x;



public abstract class SActivity extends AppCompatActivity implements SimpleListener {
    public CancelableManage mCancelableManage;
    public AppAppliction abApplication = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        abApplication = AppAppliction.applictions;
         x.view().inject(this);
        mCancelableManage = new CancelableManage();
        initData(savedInstanceState);
    }
    public abstract void initData(Bundle savedInstanceState);//初始化数据



    /**
     * 移除注解，和请求
     */
    @Override
    @CallSuper
    protected void onDestroy() {
        mCancelableManage.clear();
        AppManager.getInstance().finishActivity(this);
        super.onDestroy();
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
        Toasty.normal(this,mess).show();
    }

    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void callBack(long code, long stat, Object value1, Object value2) {

    }
}
