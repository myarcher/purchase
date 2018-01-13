package com.commodity.purchase.app;

/**
 * Created by Administrator on 2018/1/13.
 */

public class Utils {
    public static boolean isLogin() {
        int is_login = DataManager.share().getInt("is_login", 0);
        if (is_login == 1) {
            return true;
        }
        return false;
    }
}
