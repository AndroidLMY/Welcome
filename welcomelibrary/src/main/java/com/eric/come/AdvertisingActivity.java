package com.eric.come;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

/**
 * 广告界面的设置
 */
public class AdvertisingActivity extends AppCompatActivity {
    public ImageView ivImage;
    public TextView tvTime;
    private CountDownTimer countDownTimer;
    private static Class<?> clss;
    private static int imageint = 0;
    private static Activity activity;
    public static ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
    private static String imageUrl;
    private static int time;
    private static boolean isSkip;
    private static boolean isToAdvertising = false;
    private static String advertisingUrl = "https://www.baidu.com/";
    private static String advertisingTitle = "";

    /**
     * 有网络时网络图片的URL
     */
    public static void setImageUrl(String imageUrls) {
        imageUrl = imageUrls;
    }

    /**
     * 广告跳转的的URL标题
     */
    public static void setAdvertisingTitle(String advertisingTitles) {
        advertisingTitle = advertisingTitles;
    }

    /**
     * 广告跳转的的URL
     */
    public static void setAdvertisingUrl(String advertisingUrls) {
        advertisingUrl = advertisingUrls;
    }

    /**
     * 有网络时网络图片的URL
     */
    public static void setImageInt(int imageints) {
        imageint = imageints;
    }

    /**
     * 设置广告页面几秒后跳转
     */
    public static void setSkipTime(int times) {
        time = times * 1000;
    }

    /**
     * 设置右上角跳转按钮是否显示
     */
    public static void setIsSkip(boolean isSkips) {
        isSkip = isSkips;
    }

    /**
     * 设置点击图片是否跳转详情页面
     */
    public static void setIsToAdvertising(boolean isToAdvertisings) {
        isToAdvertising = isToAdvertisings;
    }

    public static void show(Activity context, Class<?> cls) {
        clss = cls;
        activity = context;
        context.startActivity(new Intent(context, AdvertisingActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising);
        activity.finish();
        initView();
    }

    private void initView() {
        ivImage = findViewById(R.id.iv_image);
        tvTime = findViewById(R.id.tv_time);
        ivImage.setScaleType(scaleType);
        if (isConnectingToInternet(this)) {
            if (isSkip) {
                tvTime.setVisibility(View.VISIBLE);
                setCountdown(tvTime, time);
            } else {
                tvTime.setVisibility(View.GONE);
            }
            RequestOptions options = new RequestOptions()
                    .format(DecodeFormat.PREFER_ARGB_8888);//设置图片解码格式;
            if (imageint == 0) {
                Glide.with(this)
                        .asBitmap()
                        .load(imageUrl)
                        .apply(options)
                        .into(ivImage);
            } else {
                Glide.with(this)
                        .asBitmap()
                        .load(imageint)
                        .apply(options)
                        .into(ivImage);
            }
        } else {
            closeCountdown();
        }
        ivImage.setOnClickListener(v -> {
            if (isToAdvertising) {
                closeCountdown();
                WebViewActivity.startActivitys(this, TextUtils.isEmpty(advertisingTitle) ? advertisingUrl : advertisingTitle, advertisingUrl);
            }
        });
    }


    /**
     * 设置倒计时
     *
     * @param textView
     * @param time     1000 = 1秒;
     */
    public void setCountdown(final TextView textView, int time) {
        //实现倒计时
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                textView.setText(0 + "秒");
                closeCountdown();
            }
        }.start();
        textView.setOnClickListener(v -> {
            textView.setEnabled(false);
            closeCountdown();
        });
    }

    public void closeCountdown() {
        Intent intent = new Intent(this, clss);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    /**
     * 判断是否有网络 无网络直接跳转MainActivity
     */
    public boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivity.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivity.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
            }
        }
        return false;
    }
}
