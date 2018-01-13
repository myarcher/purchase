package com.commodity.purchase.app;

import com.commodity.purchase.until.AppLogUtil;
import com.commodity.purchase.constance.AppContanse;
import com.mauiie.aech.AECHConfiguration;
import com.mauiie.aech.AECrashHelper;
import com.meiqia.core.MQManager;
import com.meiqia.meiqiasdk.util.MQConfig;

import org.xutils.x;

/**
 * @author
 * @version 1.0
 * @date 2018/1/10
 */

public class AppHelper {
    public static void init(AppAppliction appliction) {
        initCustome(appliction);
      // initCrash(appliction);
        initXutil(appliction);
        initMeiqiaSDK(appliction);

    }

    /**
     * 初始化应用相关的
     *
     * @param appliction
     */
    private static void initCustome(AppAppliction appliction) {
        DataManager.manager.init(appliction);
        DataManager.manager.setDebug(true);
    }

    /**
     * 全局异常的捕获初始化
     *
     * @param context
     */
    private static void initCrash(AppAppliction context) {
        //  AECrashHelper.initCrashHandler(this);
        AECrashHelper.initCrashHandler(context, new AECHConfiguration.Builder().setSaveToLocal(true).setReportToServer(true).setReporter(new AECHConfiguration.IAECHReporter() {
            @Override
            public void report(Throwable ex) {
                AppLogUtil.d("向服务器发送报告由你自己根据项目的环境定制实现");
            }
        })
                //.setLocalFolderPath()//设置本地异常日志文件存储路径
                .build());
    }

    /**
     * 初始化xutil
     *
     * @param appliction
     */
    private static void initXutil(AppAppliction appliction) {
        x.Ext.init(appliction);//初始化xutil
        x.Ext.setDebug(DataManager.isDebug()); //设置为调试模式
    }

    /**
     * 美洽的客服初始化
     */
    private static void initMeiqiaSDK(AppAppliction appliction) {
        MQManager.setDebugMode(DataManager.isDebug());
        MQConfig.init(appliction, AppContanse.meiqiaKey, null);
        // 可选
        customMeiqiaSDK();
    }

    /**
     * 美洽的客服界面的自定义样式
     */
    private static void customMeiqiaSDK() {
        // 配置自定义信息
        //  MQConfig.ui.titleGravity = MQConfig.ui.MQTitleGravity.CENTER;
        //  MQConfig.ui.backArrowIconResId = R.mipmap.icon_back1;
        //   MQConfig.ui.titleBackgroundResId = R.color.title_bg;
        //   MQConfig.ui.titleTextColorResId = R.color.white_color;
        //  MQConfig.isShowClientAvatar = true;
        //        MQConfig.ui.leftChatBubbleColorResId = R.color.test_green;
        //        MQConfig.ui.leftChatTextColorResId = R.color.test_red;
        //        MQConfig.ui.rightChatBubbleColorResId = R.color.test_red;
        //        MQConfig.ui.rightChatTextColorResId = R.color.test_green;
        //        MQConfig.ui.robotEvaluateTextColorResId = R.color.test_red;
        //        MQConfig.ui.robotMenuItemTextColorResId = R.color.test_blue;
        //        MQConfig.ui.robotMenuTipTextColorResId = R.color.test_blue;
    }


}
