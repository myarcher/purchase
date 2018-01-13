package com.commodity.purchase.until;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.commodity.purchase.app.AppAppliction;
import com.commodity.purchase.app.DataManager;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * @author
 * @version 1.0
 * @date 2018/1/10
 */

public class AppUntils {

    //判断一个activity是否在后台运行
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    Log.i("后台", appProcess.processName);
                    return true;
                } else {
                    Log.i("前台", appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断有没有网络
     *
     * @return
     */
    public static boolean verifyNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            if (activeNetInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public static final boolean isClassExists(String classFullName) {
        try {
            Class.forName(classFullName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

 
    public static BitmapDrawable getBitmap(Context context,int re) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(re);
        Bitmap bm = BitmapFactory.decodeStream(is, null, opt);
        BitmapDrawable bd = new BitmapDrawable(context.getResources(), bm);
        return bd;
    }


    //获取保存的token
    public String tokens() {
        String token = DataManager.share().getString("token", "");
        return token;
    }


    //获取手机的唯一标识，这个在登陆之后会存起来，在退出登录的时候清除
    public String divice() {
        String divice =DataManager.share().getString("divice", "");
        if (TextUtils.isEmpty(divice)) {
            UUID uuid = UUID.randomUUID();
            divice = uuid.toString();
        }
        return divice;
    }
    public static int getSW() {
        DisplayMetrics disp =  AppAppliction.applictions.getResources().getDisplayMetrics();
        return disp.widthPixels;
    }

    public static int getSH() {
        DisplayMetrics disp = AppAppliction.applictions.getResources().getDisplayMetrics();
        return disp.heightPixels;
    }


}
