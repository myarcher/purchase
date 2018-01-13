package com.commodity.purchase.net;

import com.commodity.purchase.until.AppLogUtil;

import org.xutils.common.Callback;


@SuppressWarnings("hiding")
public class RequestHandler<T> implements Callback.ProgressCallback<T> {
    public NetResponseListener.NetOkListener netOkListener;
    public NetResponseListener.NetFaildListener netFaildListener;
    public NetResponseListener.NetProcessListener netProcessListener;
    public NetResponseListener.NetListener mNetListener;

  

    public int what = 1;
    public RequestHandler() {
    }
    public RequestHandler(int what) {
        this.what = what;
    }
    
    public <T> RequestHandler setOkListener(NetResponseListener.NetOkListener<T> OkListener) {
        if (OkListener != null) {
            this.netOkListener = OkListener;
        }
        return this;
    }

    public RequestHandler setFaildListener(NetResponseListener.NetFaildListener FaildListener) {
        if (FaildListener != null) {
            this.netFaildListener = FaildListener;
        }
        return this;
    }

    public RequestHandler setProcessListener(NetResponseListener.NetProcessListener ProcessListener) {
        if (ProcessListener != null) {
            this.netProcessListener = ProcessListener;
        }
        return this;
    }
    public void setNetListener(NetResponseListener.NetListener netlistener) {
        if (netlistener != null) {
            mNetListener = netlistener;
        }
    }
    @Override
    public void onWaiting() {

    }

    @Override
    public void onStarted() {
        if (mNetListener != null) {
            mNetListener.onStarted();
        }
    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {
        if (netProcessListener != null) {
            netProcessListener.onProcess(total, current, isDownloading, what);
        }
    }

    @Override
    public void onSuccess(T result) {
        AppLogUtil.i(result+"");
        if (netOkListener != null) {
            netOkListener.onSuccess(result,"",what);
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        if (netFaildListener != null) {
            netFaildListener.onFaild(ex, what);
        }
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {
        if (mNetListener != null) {
            mNetListener.onFinished();
        }
    }

    public void setWhat(int what) {
        this.what=what;
    }
}
