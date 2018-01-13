package com.commodity.purchase.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.commodity.purchase.R;
import com.commodity.purchase.app.DataManager;
import com.commodity.purchase.app.Utils;
import com.commodity.purchase.base.SActivity;
import com.commodity.purchase.ui.MainActivity;
import com.commodity.purchase.ui.self.LoginActivity;

import org.xutils.view.annotation.Event;

import java.util.Map;

/**
 * 启动页
 *
 * @author
 * @version 1.0
 * @date 2017/4/20
 */
public class SplashActivity extends SActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置为全屏 要在设置布局前
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mDownTimer.start();


   //     splash_img.setBackgroundDrawable(getBitmap(R.mipmap.start));//防止oom把res处理再返回
      //  mSplashp = new Splashp(this);
       // mSplashp.getVersion();//获取应用的版本信息和现金券的弹框信息
       // UpdataContent.instance().main = 0;//防止重新进入应用，主界面选择的不是首页
      //  UpdataContent.instance().shops = 0;//防止重新进入应用，主界面选择的不是首页
    }



    CountDownTimer mDownTimer = new CountDownTimer(5 * 1000, 1000) {//计时器，倒数
        @Override
        public void onTick(long mill) {

        }

        @Override
        public void onFinish() {
            toWings(null);//倒计时结束
        }
    };

    @Event(value = {R.id.splash_tv})
    private void toWings(View v) {
        mDownTimer.cancel();//在倒计时期间点击按钮，结束倒计时
        boolean flag = DataManager.share().getBoolean("is_first", true);//获取本地是否是安装应用后第一次进入
       if (flag) {
            DataManager.share().putBoolean("is_first", false);
            startActivity(new Intent(this, FirstShowActivity.class));//进入第一次装应用的显示的界面
        } else {
           boolean is_login= Utils.isLogin();
           if(is_login){
               startActivity(new Intent(this, MainActivity.class));
           }else {
               startActivity(new Intent(this, LoginActivity.class));
           }
        }
        finish();
    }

    /**
     * 防止在倒计时期间，点击返回按钮，应用奔溃
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
            mDownTimer.cancel();//结束倒计时
            finish();
        }
        return false;
    }

    /**
     * 设置 获取到的信息数据
     * @param data
     */
    public void setInfos(Map<String, Object> data) {
        if(data!=null) {
            String type=data.get("dialog_type") + "";//弹框的类型 1 表示版本更新  2，弹框 显示 ，3没有处理 （这个只会存在一种情况）
            if(type.equals("1")){
                /**
                 * 下面两个数据在首页可以版本更新的时候，弹出更新弹框的时候用到
                 */
           //     AppAppliction.applictions.versioninfo = (Map<String, Object>) data.get("version");//版本更新的相关数据
            //    AppAppliction.applictions.down_type = type+ "";
            }else{
     //           AppAppliction.applictions.versioninfo =null;
        //        AppAppliction.applictions.down_type = "3";
            }
           
        }else{
         //   AppAppliction.applictions.versioninfo =null;
         //   AppAppliction.applictions.down_type = "3";
        }
    }

}
