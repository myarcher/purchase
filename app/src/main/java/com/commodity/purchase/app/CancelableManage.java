package com.commodity.purchase.app;


import org.xutils.common.Callback;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2018/1/11
 */

public class CancelableManage {
    private List<Callback.Cancelable> mList;
    public CancelableManage() {
        mList=new ArrayList<>();
    }

    public void add(Callback.Cancelable cancelable) {
        if (cancelable != null && !cancelable.isCancelled()) {
            mList.add(cancelable);
        }
    }


    public void remove(Callback.Cancelable cancelable) {
        if (cancelable != null && !cancelable.isCancelled()) {
            cancelable.cancel();
            mList.remove(cancelable);
        }
    }

    public void clear() {
        for (int i = 0; i < mList.size(); i++) {
            remove(mList.get(i));
        }
    }
}
