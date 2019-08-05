package com.eric.welcome;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

/**
 * 广告界面的设置
 */
public class AdvertisingActivity extends AppCompatActivity {
    private ImageView ivImage;
    private TextView tvTime;
    private static String imageUrl;
    private static int time;
    private static boolean isSkip;
    private CountDownTimer countDownTimer;
    private static Class<?> clss;


    /**
     * 有网络时网络图片的URL
     */
    public static void setImageUrl(String imageUrl) {
        AdvertisingActivity.imageUrl = imageUrl;
    }

    /**
     * 设置广告页面几秒后跳转
     */
    public static void setSkipTime(int time) {
        AdvertisingActivity.time = time * 1000;
    }

    /**
     * 设置右上角跳转按钮是否显示
     */
    public static void setIsSkip(boolean isSkip) {
        AdvertisingActivity.isSkip = isSkip;
    }

    public static void show(Context context, Class<?> cls) {
        clss = cls;
        context.startActivity(new Intent(context, AdvertisingActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising);
        initView();
    }
    private void initView() {
        ivImage = findViewById(R.id.iv_image);
        tvTime = findViewById(R.id.tv_time);
        if (isConnectingToInternet(this)) {
            if (isSkip) {
                tvTime.setVisibility(View.VISIBLE);
            } else {
                tvTime.setVisibility(View.GONE);
            }
            Glide.with(this).load(imageUrl).into(ivImage);
            setCountdown(tvTime, time);
        } else {
            closeCountdown();
        }
    }
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
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setEnabled(false);
                closeCountdown();
            }
        });
    }

    public void closeCountdown() {
        if (countDownTimer != null)
            countDownTimer.cancel();
        Intent intent = new Intent(this, clss);
        startActivity(intent);
        finish();
    }
}
