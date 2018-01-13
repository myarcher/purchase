package com.commodity.purchase.app;

import android.support.multidex.MultiDexApplication;

/**
 * @author
 * @version 1.0
 * @date 2017/4/19
 */

public class AppAppliction extends MultiDexApplication {
    public static AppAppliction applictions;
    @Override
    public void onCreate() {
        super.onCreate();
        applictions=this;
        AppHelper.init(this);
    }
    
}
