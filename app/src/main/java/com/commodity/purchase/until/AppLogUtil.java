package com.commodity.purchase.until;

import android.util.Log;

/**
 * @author
 * @version 1.0
 * @date 2018/1/10
 */

public class AppLogUtil {
    private static final String TAG = "purchase";
    private static boolean isDebug = true;
    private final static boolean IS_DEBUG_OF_THREAD = false;


    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }


    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }


    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }


    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }


    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }


    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }


    public static void v(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.v(clazz.getSimpleName(), msg);
        }
    }

    public static void i(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.i(clazz.getSimpleName(), msg);
        }
    }

    public static void d(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.d(clazz.getSimpleName(), msg);
        }
    }

    public static void w(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.w(clazz.getSimpleName(), msg);
        }
    }

    public static void e(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.e(clazz.getSimpleName(), msg);
        }
    }


    public static void printThread(Class<?> clazz, String msg) {
        if (IS_DEBUG_OF_THREAD) {
            Log.w(clazz.getSimpleName(), "### " + msg + " -> " + " {name: " + Thread.currentThread().getName() + " , " + "id:" + Thread.currentThread().getId() + "}");
        }
    }

    public static void printThread(String tag, String msg) {
        if (IS_DEBUG_OF_THREAD) {
            Log.w(tag, "### " + msg + " -> " + "{name: " + Thread.currentThread().getName() + " , " + "id:" + Thread.currentThread().getId() + "}");
        }
    }
    

}
