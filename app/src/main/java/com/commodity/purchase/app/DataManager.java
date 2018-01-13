package com.commodity.purchase.app;

import android.app.Application;
import com.commodity.purchase.until.AppShareUntil;
import com.commodity.purchase.constance.AppContanse;


/**
 * @author
 * @version 1.0
 * @date 2018/1/10
 */

public class DataManager {

    public static Application apps() {
        if (DataManager.manager.app == null) {
            DataManager.manager.app= AppAppliction.applictions;
        }
        return DataManager.manager.app;
    }

    public static boolean isDebug() {
        return DataManager.manager.debug;
    }

    public static AppShareUntil share() {
        if (DataManager.manager.mShareUntil == null) {
            AppShareUntil mShareUntil = AppShareUntil.getInstance(AppContanse.SHARE_NAME, apps());
            DataManager.manager.setAppShareUntil(mShareUntil);
        }
        return DataManager.manager.mShareUntil;
    }

    public static class manager {
        private static boolean debug=true;
        private static AppAppliction app;
        private static AppShareUntil mShareUntil;

        private manager() {
        }

        public static void init(AppAppliction app) {
            if (DataManager.manager.app == null) {
                DataManager.manager.app = app;
            }
        }

        public static void setDebug(boolean debug) {
            DataManager.manager.debug = debug;
        }

        public static void setApp(AppAppliction app) {
            if (DataManager.manager.app == null) {
                DataManager.manager.app = app;
            }
        }

        public static void setAppShareUntil(AppShareUntil shareUntil) {
            if (DataManager.manager.mShareUntil == null) {
                DataManager.manager.mShareUntil = shareUntil;
            }
        }
    }

   
}
