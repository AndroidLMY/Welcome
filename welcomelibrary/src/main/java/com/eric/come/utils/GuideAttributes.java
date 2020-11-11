package com.eric.come.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.eric.come.R;

import java.util.List;

/**
 * @author
 * @功能:
 * @Creat 2020/11/11 3:26 PM
 * @Compony 465008238@qq.com
 */


public class GuideAttributes {
    private Class<?> endActivity;
    private List<Integer> imagesList;
    private Activity startactivity;

    private int skipTextColor;
    private int skipTextBackgroundColor;
    private int skipProgressColor;
    private int skipTime = 15;
    private int indicatorSelectColor;
    private int indicatorUnSelectColor;

    private boolean isProgress;
    private boolean isCountdown;
    private boolean isTimeEndClick;
    private boolean isTimeClose;
    private boolean isSkip;
    private boolean isIndicator;
    private boolean isEndIndexClick;

    public Class<?> getEndActivity() {
        return endActivity;
    }

    public List<Integer> getImagesList() {
        return imagesList;
    }

    public Activity getStartactivity() {
        return startactivity;
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

    public int getSkipTime() {
        return skipTime*1000;
    }

    public int getIndicatorSelectColor() {
        return indicatorSelectColor;
    }

    public int getIndicatorUnSelectColor() {
        return indicatorUnSelectColor;
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

    public boolean isIndicator() {
        return isIndicator;
    }

    public boolean isEndIndexClick() {
        return isEndIndexClick;
    }

    public static class Builder {
        private Class<?> endActivity;
        private List<Integer> imagesList;
        private Activity startactivity;

        private int skipTextColor;//跳过TextView文字颜色
        private int skipTextBackgroundColor;//跳过TextView背景颜色
        private int skipProgressColor;//是否显示倒计时进度条 进度条颜色
        private int skipTime = 15;//倒计时时间 传入秒即可 例如：3
        private int indicatorSelectColor;//底部指示器选中颜色
        private int indicatorUnSelectColor;//底部指示器未选中颜色

        private boolean isProgress = true;//是否显示倒计时进度条
        private boolean isCountdown = true;//是否开启倒计时
        private boolean isTimeEndClick = true;//是否倒计时结束后才可以点击跳过
        private boolean isTimeClose = false;//倒计时结束后是否自动关闭界面
        private boolean isSkip = true;//是否显示右上角跳过TextView
        private boolean isIndicator = false;//是否显示底部指示器
        private boolean isEndIndexClick = true;//是否添加最后一页点击图片跳转主界面默认开启

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
         * 跳转引导页上下文 Activty 引导页的资源文件
         */
        public Builder(Activity activity, Class<?> clss, List<Integer> asList) {
            imagesList = asList;
            startactivity = activity;
            endActivity = clss;
        }

        /**
         * 引导页底部指示器选中颜色
         */
        public Builder indicatorSelectColor(int val) {
            indicatorSelectColor = val;
            return this;
        }

        /**
         * 引导页底部指示器未选中颜色
         */
        public Builder indicatorUnSelectColor(int val) {
            indicatorUnSelectColor = val;
            return this;
        }

        /**
         * 是否显示底部指示器
         */
        public Builder isIndicator(boolean val) {
            isIndicator = val;
            return this;
        }

        /**
         * 是否倒计时结束自动跳转endActivity
         */
        public Builder isTimeClose(boolean val) {
            isTimeClose = val;
            return this;
        }

        /**
         * 是否为最后一个引导页添加点击跳转的功能
         */
        public Builder isEndIndexClick(boolean val) {
            isEndIndexClick = val;
            return this;
        }

        /**
         * 跳过引导页的TextView文字的颜色
         */
        public Builder skipTextColor(int val) {
            skipTextColor = val;
            return this;
        }

        /**
         * 跳过引导页的进度的颜色
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
         * 跳过引导页TextView背景的颜色
         */
        public Builder skipTextBackgroundColor(int val) {
            skipTextBackgroundColor = val;
            return this;
        }


        /**
         * 引导页跳过时间
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


        public GuideAttributes build() {
            return new GuideAttributes(this);
        }
    }

    private GuideAttributes(Builder builder) {
        skipTime = builder.skipTime;
        isSkip = builder.isSkip;
        endActivity = builder.endActivity;
        startactivity = builder.startactivity;
        skipTextColor = builder.skipTextColor;
        skipTextBackgroundColor = builder.skipTextBackgroundColor;
        skipProgressColor = builder.skipProgressColor;
        isProgress = builder.isProgress;
        imagesList = builder.imagesList;
        isIndicator = builder.isIndicator;
        isEndIndexClick = builder.isEndIndexClick;
        isTimeClose = builder.isTimeClose;
        indicatorSelectColor = builder.indicatorSelectColor;
        indicatorUnSelectColor = builder.indicatorUnSelectColor;
        isTimeEndClick = builder.isTimeEndClick;
        isCountdown = builder.isCountdown;


    }
}
