package com.commodity.purchase.ui.other;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.commodity.purchase.R;
import com.commodity.purchase.base.SActivity;
import com.commodity.purchase.ui.adapter.FirstShowAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * 版本特性的显示界面 安装应用后的第一次进入
 */
@ContentView(R.layout.activity_first_show)
public class FirstShowActivity extends SActivity {
    @ViewInject(R.id.viewpager)
    ViewPager viewpager;
    @ViewInject(R.id.viewGroup)
    LinearLayout viewGroup;
    PagerAdapter mPgAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//显示全屏
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initgroup();
    }

    /**
     * 初始化界面底部的椭圆控件 
     */
    private void initgroup() {
        // 清除所有子视图
        viewGroup.removeAllViews();
        // 图片广告数量
    }


    private boolean flag;

    /**
     * viewpager 和adapter 并将两个相关联
     */
    private void initView() {
        mPgAdapter = new FirstShowAdapter(getSupportFragmentManager(), this);//初始化pager对应的adapter
        viewpager.setAdapter(mPgAdapter);//绑定pager和adapter
        viewpager.setOffscreenPageLimit(4);
    }
}
