package com.eric.come;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.Map;

/**
 * Created by damon on 2018/9/26.
 */

public class ProgressWebView extends WebView {
    private ProgressBar mProgressBar;

    public ProgressWebView(Context context) {
        super(context);
        initWebView(context);
    }

    public ProgressWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initWebView(context);

    }

    public ProgressWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initWebView(context);
    }

    public ProgressWebView(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
        initWebView(context);

    }

    public ProgressWebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);

        initWebView(context);
    }


    /*初始化webView*/
    private void initWebView(Context context) {

        /*添加进度条*/
        mProgressBar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 10);
        mProgressBar.setLayoutParams(layoutParams);

        mProgressBar.setProgress(0);
        addView(mProgressBar);

        /*设置Settings*/
        final WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);//开启js脚本
        webSetting.setAllowFileAccess(true);//是否允许访问文件
        webSetting.setSupportZoom(true);//是否支持缩放
        webSetting.setBuiltInZoomControls(true);//是否显示缩放控制栏

        /*初始化webClient*/

        setWebViewClient(new WebViewClient() {

            /*页面内跳转*/
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return super.shouldOverrideUrlLoading(webView, s);
            }
        });


        /*webChromeClient*/
        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
            }

            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);

                if (i == 100) {
                    mProgressBar.setVisibility(GONE);
                } else {
                    if (mProgressBar.getVisibility() == GONE)
                        mProgressBar.setVisibility(VISIBLE);
                    mProgressBar.setProgress(i);
                }
            }
        });

    }

    public ProgressBar getmProgressBar() {
        return mProgressBar;
    }
}

