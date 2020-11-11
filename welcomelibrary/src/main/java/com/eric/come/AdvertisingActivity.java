package com.eric.come;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.eric.come.utils.AdPageAttributes;

/**
 * @author lmy
 * @功能: 广告页界面
 * @Creat 2020/11/11 4:19 PM
 * @Compony 465008238@qq.com
 */

public class AdvertisingActivity extends AppCompatActivity {
    public ImageView ivImage;
    public CountDownTextView tvTime;
    private CountDownTimer countDownTimer;
    private static AdPageAttributes adPageAttributes;

    public static void show(AdPageAttributes adPageAttribute) {
        adPageAttributes = adPageAttribute;
        adPageAttribute.getStartactivity().startActivity(new Intent(adPageAttribute.getStartactivity(), AdvertisingActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising);
        adPageAttributes.getStartactivity().finish();
        initView();
    }

    private void initView() {
        ivImage = findViewById(R.id.iv_image);
        tvTime = findViewById(R.id.tv_time);
        ivImage.setScaleType(adPageAttributes.getScaleType());
        GradientDrawable myShape = (GradientDrawable) findViewById(R.id.tv_time).getBackground();
        myShape.setColor(adPageAttributes.getSkipTextBackgroundColor() == 0 ? getResources().getColor(R.color.skipBgColor) : getResources().getColor(adPageAttributes.getSkipTextBackgroundColor()));
        tvTime.setTextColor(adPageAttributes.getSkipTextColor() == 0 ? getResources().getColor(R.color.colorWhite) : getResources().getColor(adPageAttributes.getSkipTextColor()));
        tvTime.setPargsColors(adPageAttributes.getSkipProgressColor() == 0 ? R.color.colorAccent : adPageAttributes.getSkipProgressColor());
        if (isConnectingToInternet(this)) {
            if (adPageAttributes.isSkip()) {
                tvTime.setVisibility(View.VISIBLE);
                if (adPageAttributes.isCountdown()) {
                    setCountdown(tvTime, adPageAttributes.getSkipTime());
                } else {
                    setUnCountdown();
                }
            } else {
                tvTime.setVisibility(View.GONE);
            }
            Glide.with(this)
                    .asBitmap()
                    .load(((adPageAttributes).getImageResources() == 0) ? adPageAttributes.getImageUrl() : adPageAttributes.getImageResources())
                    .apply(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                    .into(ivImage);
        } else {
            closeCountdown();
        }
        ivImage.setOnClickListener(v -> {
            if (adPageAttributes.isToAdvertising()) {
                closeCountdown();
                WebViewActivity.startActivitys(this, TextUtils.isEmpty(adPageAttributes.getAdvertisingTitle()) ? adPageAttributes.getAdvertisingUrl() : adPageAttributes.getAdvertisingTitle(), adPageAttributes.getAdvertisingUrl());
            }
        });
    }

    private void setUnCountdown() {
        tvTime.setText("跳过");
        tvTime.setOnClickListener(v -> {
            closeCountdown();
        });
    }


    /**
     * 设置倒计时
     *
     * @param textView
     * @param time     1000 = 1秒;
     */
    public void setCountdown(final CountDownTextView textView, int time) {
        if (!adPageAttributes.isTimeEndClick()) {
            textView.setOnClickListener(v -> {
                textView.setEnabled(false);
                closeCountdown();
            });
        }
        if (adPageAttributes.isProgress()) {
            tvTime.setDuration(adPageAttributes.getSkipTime());
            tvTime.start();
        }
        //实现倒计时
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                textView.setText("跳过");
                textView.reset();
                if (adPageAttributes.isTimeClose()) {
                    closeCountdown();
                }
                if (adPageAttributes.isTimeEndClick()) {
                    textView.setOnClickListener(v -> {
                        textView.setEnabled(false);
                        closeCountdown();
                    });
                }

            }
        }.start();

    }

    public void closeCountdown() {
        Intent intent = new Intent(this, adPageAttributes.getEndActivity());
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
        tvTime.stop();

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
