package com.eric.welcome;

import android.os.Bundle;

import com.eric.come.AdvertisingActivity;
import com.eric.come.GuideActivity;
import com.eric.come.WelcomeActivity;
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
                .build();
        GuideActivity.show(attributes);
    }

    @Override
    public void goMain() {
        AdPageAttributes adPageAttributes = new AdPageAttributes.Builder(this, MainActivity.class,
                "http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c87a3add4d52a6059252da61e.jpg")
                .build();
        AdvertisingActivity.show(adPageAttributes);
    }
}