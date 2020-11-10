package com.eric.welcome;

import android.content.Intent;
import android.os.Bundle;

import com.eric.come.AdvertisingActivity;
import com.eric.come.GuideActivity;
import com.eric.come.WelcomeActivity;

public class SplashActivity extends WelcomeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void goGuide() {
        //设置引导页等待时间60秒，引导页图片，最终跳转的activity
        GuideActivity.show(this, 60, new int[]{
                R.drawable.guide_01,
                R.drawable.guide_02,
                R.drawable.guide_03,
                R.drawable.guide_04,
        }, MainActivity.class);
    }

    @Override
    public void goMain() {
        //设置广告位图片的url
        AdvertisingActivity.setImageUrl("http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c87a3add4d52a6059252da61e.jpg");
        //设置是否显示跳过广告按钮
        AdvertisingActivity.setIsSkip(true);
        //设置广告默认等待时间3秒
        AdvertisingActivity.setSkipTime(10);
        //设置广告页是否点击跳转
        AdvertisingActivity.setIsToAdvertising(true);
        //广告页点击跳转链接
        AdvertisingActivity.setAdvertisingTitle("天猫首页");
        //广告页点击跳转标题
        AdvertisingActivity.setAdvertisingUrl("https://www.tmall.com/?spm=a211oj.20748425/ssrn.a2226mz.1.36583fd9R8P65Q");
        //启动广告页
        AdvertisingActivity.show(this, MainActivity.class);
    }
}