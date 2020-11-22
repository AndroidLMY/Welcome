package com.eric.welcome;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.eric.come.activity.AdvertisingActivity;
import com.eric.come.activity.GuideActivity;
import com.eric.come.activity.WelcomeActivity;
import com.eric.come.utils.AdPageAttributes;
import com.eric.come.utils.GuideAttributes;

import java.util.Arrays;

public class SplashActivity extends WelcomeActivity {

    @Override
    public boolean getIsShowGuide() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void goGuide() {
        GuideAttributes attributes = new GuideAttributes.Builder(this, MainActivity.class,
                Arrays.asList(R.drawable.guide_01, R.drawable.guide_02, R.drawable.guide_03, R.drawable.guide_04))
                .isTimeEndClick(true)//是否倒计时结束后 才可以点击跳过按钮
                .isCountdown(true)//是否开启倒计时
                .isIndicator(true)//是否显示指示器
                .isSkipText(true)//是否显示跳过按钮
                .isTimeClose(true)//倒计时结束后 是否自动跳转
                .isProgress(true)//是否显示倒计时 进度条
                .isEndIndexClick(true)//是否为引导页最后一页添加点击跳转
                .indicatorUnSelectColor(R.color.colorAccent)//指示器未选中颜色
                .indicatorSelectColor(R.color.colorAccent)//指示器选中颜色
                .skipTime(10)//倒计时时间
                .skipTextColor(R.color.colorAccent)//跳过按钮文字颜色
                .skipTextBackgroundColor(R.color.colorAccent)//跳过按钮背景色
                .skipProgressColor(R.color.colorAccent)//跳过按钮进度条颜色
                .build();
        GuideActivity.show(attributes);
    }

    @Override
    public void goMain() {
        AdPageAttributes adPageAttributes = new AdPageAttributes.Builder(this, MainActivity.class,
                "http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c87a3add4d52a6059252da61e.jpg")
                .isTimeClose(true)
                .isTimeEndClick(false)
                .isCountdown(true)
                .skipTime(3)
                .build();
        AdvertisingActivity.show(adPageAttributes);

    }
}