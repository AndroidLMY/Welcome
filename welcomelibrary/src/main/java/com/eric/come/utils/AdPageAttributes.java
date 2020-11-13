package com.eric.come.utils;

import android.app.Activity;
import android.widget.ImageView;

/**
 * @author
 * @功能:
 * @Creat 2020/11/11 3:26 PM
 * @Compony 465008238@qq.com
 */


public class AdPageAttributes {
    private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
    private Class<?> endActivity;
    private Activity startactivity;

    private String imageUrl;
    private String advertisingUrl = "https://www.baidu.com/";
    private String advertisingTitle;

    private int skipTime;
    private int imageResources;
    private int skipTextColor;
    private int skipTextBackgroundColor;
    private int skipProgressColor;

    private boolean isProgress;
    private boolean isCountdown;
    private boolean isTimeEndClick;
    private boolean isTimeClose;
    private boolean isSkip;
    private boolean isToAdvertising;


    public ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    public Class<?> getEndActivity() {
        return endActivity;
    }

    public Activity getStartactivity() {
        return startactivity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAdvertisingUrl() {
        return advertisingUrl;
    }

    public String getAdvertisingTitle() {
        return advertisingTitle;
    }

    public int getSkipTime() {
        return skipTime * 1000;
    }

    public int getImageResources() {
        return imageResources;
    }

    public int getSkipTextColor() {
        return skipTextColor;
    }

    public int getSkipTextBackgroundColor() {
        return skipTextBackgroundColor;
    }

    public int getSkipProgressColor() {
        return skipProgressColor;
    }

    public boolean isProgress() {
        return isProgress;
    }

    public boolean isCountdown() {
        return isCountdown;
    }

    public boolean isTimeEndClick() {
        return isTimeEndClick;
    }

    public boolean isTimeClose() {
        return isTimeClose;
    }

    public boolean isSkip() {
        return isSkip;
    }

    public boolean isToAdvertising() {
        return isToAdvertising;
    }

    public static class Builder {
        private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;//广告页ImageView填充方式
        private Class<?> endActivity;//广告页结束跳转的界面 MainActivity.class
        private Activity startactivity;//开始跳转的上下文

        private String imageUrl;//广告页网络图片url
        private String advertisingUrl = "https://www.baidu.com/";//广告页url链接
        private String advertisingTitle;//广告页标题

        private int skipTime = 3;//倒计时时间 传入秒即可 例如：3
        private int imageResources;//广告页本地图片资源
        private int skipTextColor;//跳过TextView文字颜色
        private int skipTextBackgroundColor;//跳过TextView背景颜色
        private int skipProgressColor;//是否显示倒计时进度条 进度条颜色

        private boolean isProgress = true;//是否显示倒计时进度条
        private boolean isCountdown = true;//是否开启倒计时
        private boolean isTimeEndClick = true;//是否倒计时结束后才可以点击跳过
        private boolean isTimeClose = false;//倒计时结束后是否自动关闭界面
        private boolean isSkip = true;//是否显示右上角跳过TextView
        private boolean isToAdvertising = false;//是否为广告页添加跳转WebViewActivity功能

        public Builder(Activity activity, Class<?> clss, String imageUrls) {
            startactivity = activity;
            endActivity = clss;
            imageUrl = imageUrls;
        }

        public Builder(Activity activity, Class<?> clss, int imageResource) {
            startactivity = activity;
            endActivity = clss;
            imageResources = imageResource;
        }

        /**
         * 是否开启倒计时结束后自动跳转
         */
        public Builder isTimeClose(boolean val) {
            isTimeClose = val;
            return this;
        }

        /**
         * 是否开启倒计时功能
         */
        public Builder isCountdown(boolean val) {
            isCountdown = val;
            return this;
        }

        /**
         * 是否倒计时结束后才可以点击跳转
         */
        public Builder isTimeEndClick(boolean val) {
            isTimeEndClick = val;
            return this;
        }

        /**
         * 跳过广告的TextView文字的颜色
         */
        public Builder skipTextColor(int val) {
            skipTextColor = val;
            return this;
        }

        /**
         * 跳过广告的进度的颜色
         */
        public Builder skipProgressColor(int val) {
            skipProgressColor = val;
            return this;
        }

        /**
         * 是否显示跳过进度条
         */
        public Builder isProgress(boolean val) {
            isProgress = val;
            return this;
        }

        /**
         * 跳过广告的TextView背景的颜色
         */
        public Builder skipTextBackgroundColor(int val) {
            skipTextBackgroundColor = val;
            return this;
        }

        /**
         * 设置广告页ImageView填充方式
         */
        public Builder scaleType(ImageView.ScaleType val) {
            scaleType = val;
            return this;
        }


        /**
         * 广告页跳转WebView的url
         */
        public Builder advertisingUrl(String val) {
            advertisingUrl = val;
            return this;
        }

        /**
         * 广告页跳转WebView的标题
         */
        public Builder advertisingTitle(String val) {
            advertisingTitle = val;
            return this;
        }

        /**
         * 广告页跳过时间
         */
        public Builder skipTime(int val) {
            skipTime = val;
            return this;
        }



        /**
         * 是否显示跳过按钮
         */
        public Builder isSkipText(boolean val) {
            isSkip = val;
            return this;
        }

        /**
         * 是否为广告页添加点击事件
         */
        public Builder isToAdvertising(boolean val) {
            isToAdvertising = val;
            return this;
        }

        public AdPageAttributes build() {
            return new AdPageAttributes(this);
        }
    }

    private AdPageAttributes(Builder builder) {
        scaleType = builder.scaleType;
        imageUrl = builder.imageUrl;
        advertisingUrl = builder.advertisingUrl;
        advertisingTitle = builder.advertisingTitle;
        skipTime = builder.skipTime;
        imageResources = builder.imageResources;
        isSkip = builder.isSkip;
        isToAdvertising = builder.isToAdvertising;
        endActivity = builder.endActivity;
        startactivity = builder.startactivity;
        skipTextColor = builder.skipTextColor;
        skipTextBackgroundColor = builder.skipTextBackgroundColor;
        skipProgressColor = builder.skipProgressColor;
        isProgress = builder.isProgress;
        isTimeEndClick = builder.isTimeEndClick;
        isCountdown = builder.isCountdown;
        isTimeClose = builder.isTimeClose;
    }
}
