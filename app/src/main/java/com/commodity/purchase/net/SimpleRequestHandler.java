package com.commodity.purchase.net;

import com.commodity.purchase.parse.CommonJSONParser;
import com.commodity.purchase.until.AppLogUtil;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/11.
 */

public class SimpleRequestHandler extends RequestHandler<String> {
    private NetResponseListener.NetpublicListener mPublicListener;

    public SimpleRequestHandler() {
        super();
    }
    public SimpleRequestHandler(int what) {
        super(what);
    }
    @Override
    public void onSuccess(String result) {
        AppLogUtil.i( "json>>>" + result);
        Map<String, Object> map = new CommonJSONParser().parse(result);//自自己写的万能网络json解析
        if (map != null) {//如果解析时有异常，map会为null
            int status = Integer.parseInt(map.get("status") + "");//返回数据状态值 (具体的值代表的意思参考接口文档)
            if (status == 1) {
                if(netOkListener!=null){
                    netOkListener.onSuccess(map.get("data"),map.get("message")+"",what);
                }
            } else {
                onPublics(map);
            }
        } else {
            if(netFaildListener!=null){
                netFaildListener.onFaild(new Exception("解析数据错误"),what);
            }
        }
    }
    private void onPublics(Map<String, Object> map){
       if(mPublicListener!=null){
           mPublicListener.onPublic(map,what);
       }
    }

    public void setmPublicListener(NetResponseListener.NetpublicListener mPublicListener) {
        if(mPublicListener!=null) {
            this.mPublicListener = mPublicListener;
        }
    }
}
