package com.commodity.purchase.net;

import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2018/1/11
 */

public interface NetResponseListener {
    public interface NetOkListener<T> {
        void onSuccess(T response,String mess, int requestCode);
    }
    public interface NetFaildListener {
        void onFaild(Throwable t, int requestCode);
    }
    public interface NetpublicListener {
        void onPublic(Map<String,Object> response, int requestCode);
    }
    
    public interface NetProcessListener {
        public void onProcess( long total,  long current,  boolean isDownloading,int requestCode);
    }
    public interface NetListener {
        public void onStarted();
        public void onFinished();
    }
}
