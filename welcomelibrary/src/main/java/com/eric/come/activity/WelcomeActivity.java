package com.eric.come.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;


/**
 * @author lmy
 * @功能: 启动页抽象类
 * @Creat 2020/11/11 4:20 PM
 * @Compony 465008238@qq.com
 */
public abstract class WelcomeActivity extends Activity {
    private final int DELAYED_TIME = 0;//5000  5秒
    private static final int GO_MAIN = 100;
    private static final int GO_GUIDE = 101;
    private boolean isShowGuide;

    /**
     * 第一次打开App是否显示引导页 如果不打开 直接跳转广告页
     */
    public abstract boolean getIsShowGuide();

    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_MAIN:
                    goMain();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        isShowGuide = getIsShowGuide();
        firstRun();
    }

    private void firstRun() {
        SharedPreferences sharedPreferences = getSharedPreferences("Defaulthomepage", 0);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        if (isShowGuide) {
            //判断是否需要引导页
            if (isFirstRun) {
                mhandler.sendEmptyMessageDelayed(GO_GUIDE, DELAYED_TIME);
            } else {
                Log.i("Welcome", "多次运行");
                mhandler.sendEmptyMessageDelayed(GO_MAIN, DELAYED_TIME);
            }
        } else {
            mhandler.sendEmptyMessageDelayed(GO_MAIN, DELAYED_TIME);
        }

    }

    public abstract void goGuide();

    public abstract void goMain();
}
