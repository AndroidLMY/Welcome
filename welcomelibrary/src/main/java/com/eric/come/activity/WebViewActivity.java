package com.eric.come.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eric.come.R;
import com.eric.come.utils.title_utils.StatusBarUtil;

import java.io.File;

/**
 * @author lmy
 * @功能: 进度显示WebViewActivity
 * @Creat 2020/11/11 4:19 PM
 * @Compony 465008238@qq.com
 */

public class WebViewActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private TextView tvTitle;
    private WebView webView;
    private String currentUrl;
    private ProgressBar mProgressBar;
    private String url;
    private String title;
    private static final String APP_CACAHE_DIRNAME = "/webcache";
    private ProgressBar progressBar;

    public static void startActivitys(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        //设置状态栏透明
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.colorWhite));
//        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x550000ff);
        }
        setContentView(R.layout.activity_web_view);
        initView();
        toolBar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    private void initView() {
        toolBar = findViewById(R.id.tool_bar);
        tvTitle = findViewById(R.id.tv_title);
        webView = findViewById(R.id.wb_view);
        progressBar = findViewById(R.id.progressBar);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        tvTitle.setText(title);
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webView.clearCache(true);
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


        currentUrl = url;
        clearcache();
        webView.loadUrl(url);
        //步骤3. 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //显示进度条
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    //加载完毕隐藏进度条
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;
                currentUrl = url;
                try {
                    if (url.startsWith("weixin://") || url.startsWith("alipays://") ||
                            url.startsWith("mailto://") || url.startsWith("tel://")
                        //其他自定义的scheme
                    ) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        if (url.startsWith("alipays://")) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 5000);
                        }
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return false;
                }
                //处理http和https开头的url
                return true;
            }
        });
    }

    private void clearcache() {
        //清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //WebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME);

        File webviewCacheDir = new File(getCacheDir().getAbsolutePath() + "/webviewCache");

        //删除webview 缓存目录
        if (webviewCacheDir.exists()) {
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            deleteFile(appCacheDir);
        }
    }

    private void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
        }

    }
}