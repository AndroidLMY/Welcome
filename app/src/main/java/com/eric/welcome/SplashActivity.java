package com.eric.welcome;

import android.os.Bundle;

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
                .build();
        GuideActivity.show(attributes);
    }

    @Override
    public void goMain() {
        AdPageAttributes adPageAttributes = new AdPageAttributes.Builder(this, MainActivity.class,
                "http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c87a3add4d52a6059252da61e.jpg")
                .isTimeClose(true)
                .isToAdvertising(true)
                .advertisingUrl("https://www.tmall.com/?ali_trackid=2:mm_26632258_3504122_55934697:1605249280_291_1088741852&union_lens=recoveryid:1605249280_291_1088741852&clk1=b62a2375b07b04cd0472052384461e35&upsid=b62a2375b07b04cd0472052384461e35&bxsign=tbkGF+Q0WMNbHCUyMP4lI5VSTHw2E3S08/GypzcHKSCm6uJobVZ+66/AEKkxe251xmqs//rR3p11x/NI5v9vG4P11RA4E8YpN0taFgW3BFMtAM=")
                .advertisingTitle("天猫超市")
                .build();
        AdvertisingActivity.show(adPageAttributes);
    }
}