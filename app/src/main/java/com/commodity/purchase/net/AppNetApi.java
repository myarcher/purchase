package com.commodity.purchase.net;


import android.content.Context;
import android.content.DialogInterface;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.ref.WeakReference;
import java.util.Map;

public class AppNetApi {
    private RequestItem mItem;
    private int what = 1;
    private NetResponseListener.NetOkListener netOkListener;
    private NetResponseListener.NetFaildListener netFaildListener;
    private NetResponseListener.NetProcessListener netProcessListener;
    private NetResponseListener.NetListener mNetListener;
    private RequestHandler requestHandler;
    private static AppNetApi appNetApi;
    private boolean isShow = true;
    private Callback.Cancelable cancelable = null;

    public AppNetApi setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        return this;
    }

    public AppNetApi setListeners(NetResponseListener.NetFaildListener netFaildListener, NetResponseListener.NetProcessListener netProcessListener, NetResponseListener.NetListener netListener) {
        this.netFaildListener = netFaildListener;
        this.netProcessListener = netProcessListener;
        this.mNetListener = netListener;
        return this;
    }

    public AppNetApi setOkListener(NetResponseListener.NetOkListener netOkListener) {
        this.netOkListener = netOkListener;
        return this;
    }

    public AppNetApi setFaildListener(NetResponseListener.NetFaildListener netFaildListener) {
        this.netFaildListener = netFaildListener;
        return this;
    }

    public AppNetApi setShow(boolean show) {
        isShow = show;
        return this;
    }

    public AppNetApi setNetListener(NetResponseListener.NetListener netlistener) {
        this.mNetListener = netlistener;
        return this;
    }

    public AppNetApi setProcessListener(NetResponseListener.NetProcessListener netProcessListener) {
        this.netProcessListener = netProcessListener;
        return this;
    }

    public AppNetApi UrlAndWhat(String url, int what) {
        appNetApi.mItem.setUrl(url);
        this.what = what;
        return this;
    }

    public AppNetApi what(int what) {
        this.what = what;
        return this;
    }

    public static AppNetApi get(Context context, String url) {
        appNetApi = new AppNetApi();
        appNetApi.mItem = new RequestItem(url);
        appNetApi.mItem.setContext(context);
        appNetApi.mItem.defalut(AppNetApi.Defa.POST);
        return appNetApi;
    }

    public static AppNetApi get(Context context) {
        appNetApi = new AppNetApi();
        appNetApi.mItem = new RequestItem();
        appNetApi.mItem.setContext(context);
        appNetApi.mItem.defalut(AppNetApi.Defa.POST);
        return appNetApi;
    }

    public AppNetApi Url(String url) {
        appNetApi.mItem.setUrl(url);
        return this;
    }

    public AppNetApi with(String key, Object value) {
        appNetApi.mItem.add(key, value);
        return this;
    }

    public AppNetApi withs(Map<String, Object> map) {
        appNetApi.mItem.adds(map);
        return this;
    }

    public AppNetApi defalut(Defa defa) {

        return this;
    }

    public AppNetApi setSavepath(String filepath) {
        appNetApi.mItem.setSavepath(filepath);
        return this;
    }

    public AppNetApi setAutoResume(boolean AutoResume) {
        appNetApi.mItem.setAutoResume(AutoResume);
        return this;
    }

    public AppNetApi setCancelFast(boolean CancelFast) {
        appNetApi.mItem.setCancelFast(CancelFast);
        return this;
    }

    public Callback.Cancelable POST() {
         cancelable = x.http().post(mItem.getParams(), getRequestHandler(cancelListener));
        return cancelable;
    }


    public Callback.Cancelable GET() {
        cancelable = x.http().get(mItem.getParams(), getRequestHandler(cancelListener));
        return cancelable;
    }

    public Callback.Cancelable UP() {
        appNetApi.mItem.setMultipart(true);
        cancelable = x.http().post(mItem.getParams(), getRequestHandler(cancelListener));
        return cancelable;
    }

    public Callback.Cancelable DOWN() {
        cancelable = x.http().get(mItem.getParams(), getRequestHandler(cancelListener));
        return cancelable;
    }

    DialogInterface.OnDismissListener cancelListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            if (cancelable!= null) {
                cancelable.cancel();
            }
        }
    };

    private RequestHandler getRequestHandler(DialogInterface.OnDismissListener cancelListener) {
        if (requestHandler == null) {
            requestHandler = new SimpleRequestHandler();
        }
        if (mNetListener == null) {
            mNetListener = new SimpleNetListener(appNetApi.mItem.getContext(), isShow, cancelListener);
        }
        requestHandler.setWhat(what);
        requestHandler.setFaildListener(netFaildListener);
        requestHandler.setOkListener(netOkListener);
        requestHandler.setProcessListener(netProcessListener);
        requestHandler.setNetListener(mNetListener);
        return requestHandler;
    }

    public AppNetApi HandlerAndLoad(RequestHandler requestHandler, NetResponseListener.NetListener netListener) {
        this.requestHandler = requestHandler;
        this.mNetListener = netListener;
        return this;
    }


    static class RequestItem {
        private RequestParams params;
        private WeakReference<Context> mContext;
        private String url;
        private int timeout = 20000;
        private String charset = "UTF-8";
        private int retrycount = 1;
        private String savepath = "";

        public RequestItem() {
            params = new RequestParams();
        }

        public RequestItem(String url) {
            params = new RequestParams(url);
        }

        public RequestItem(String url, Context context) {
            params = new RequestParams(url);
            mContext = new WeakReference<>(context);
        }

        public RequestItem setMultipart(boolean isMultipart) {
            params.setMultipart(isMultipart);
            return this;
        }

        public RequestParams getParams() {
            return params;
        }

        public RequestItem setParams(RequestParams params) {
            this.params = params;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public RequestItem setUrl(String url) {
            this.url = url;
            params.setUri(url);
            return this;
        }

        public long getTimeout() {
            return timeout;
        }

        public RequestItem setTimeout(int timeout) {
            this.timeout = timeout;
            params.setConnectTimeout(timeout);
            return this;
        }

        public String getCharset() {
            return charset;
        }

        public RequestItem setCharset(String charset) {
            this.charset = charset;
            params.setCharset(charset);
            return this;
        }

        public int getRetrycount() {
            return retrycount;
        }

        public RequestItem setRetrycount(int retrycount) {
            this.retrycount = retrycount;
            params.setMaxRetryCount(retrycount);
            return this;
        }

        public String getSavepath() {
            return savepath;
        }

        public RequestItem setSavepath(String savepath) {
            this.savepath = savepath;
            return this;
        }

        public RequestItem add(String key, Object value) {
            params.addParameter(key, value);
            return this;
        }

        public RequestItem setCancelFast(boolean cancelFast) {
            params.setCancelFast(cancelFast);
            return this;
        }

        public RequestItem setAutoResume(boolean autoResume) {
            params.setCancelFast(autoResume);
            return this;
        }

        public RequestItem setContext(Context context) {
            this.mContext = new WeakReference(context);
            return this;
        }

        public Context getContext() {
            return mContext.get();
        }

        public RequestItem defalut(Defa defa) {
            if (defa != Defa.DOWN) {
                params.setConnectTimeout(timeout);
                params.setCharset(charset);
                params.setMaxRetryCount(retrycount);
                //  params.setRequestProperty("Accept-Encoding", "identity");
            }
            else {
                params.setAutoResume(false);
                params.setCancelFast(false);
                params.setAutoResume(true);
                params.setSaveFilePath(savepath);
            }

            return this;
        }

        public RequestItem adds(Map<String, Object> map) {
            if (null != map) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    params.addParameter(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }
    }

    public enum Defa {
        POST, GET, UP, DOWN
    }
}
