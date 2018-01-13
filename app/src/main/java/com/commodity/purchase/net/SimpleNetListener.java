package com.commodity.purchase.net;


import android.content.Context;
import android.content.DialogInterface;

import com.commodity.purchase.until.LoadToast;


/**
 * 网络请求的基类 实现了网络请求的回调接口
 * Created by Administrator on 2016/9/27.
 */

public class SimpleNetListener implements NetResponseListener.NetListener {
    public Context context;
    public LoadToast mToast;
    public boolean isShow;
    public SimpleNetListener(Context context, boolean isShow, DialogInterface.OnDismissListener cancelListener) {
        this.context=context;
        this.isShow=isShow;
        if (context!= null&&isShow) {
            mToast = new LoadToast(context, "正在加载");
            mToast.setCanceListener(cancelListener);
        }
    }

    @Override
    public void onStarted() {
      if(mToast != null){
          mToast.show();
      }
    }

    @Override
    public void onFinished() {
        if (context!=null&&isShow && mToast != null && mToast.isShow()) {
            mToast.dissmiss();
            mToast=null;
        }
    }
}
