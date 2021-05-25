# Welcome引导页启动页广告页封装
## 添加依赖
implementation 'com.github.AndroidLMY:Welcome:1.0.3'
## 使用方法
### 新建activity继承WelcomeActivity实现

### 该activity需要在清单文件中引用如下主题

```
   <!-- 启动页面-->
    <style name="ThemeSplash" parent="Theme.AppCompat.Light.NoActionBar">
        <!--指定启动页背景-->
        <item name="android:background">@drawable/ic_start</item>
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowFullscreen">true</item>
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>

```
清单文件引用如下

```
        <activity
            android:name=".activity.Welcome"
            android:theme="@style/ThemeSplash">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```


### 如果不需要实现广告页面直接在gomain处理跳转主页面即可
```
   @Override
   public boolean getIsShowGuide() {
        return true;//返回true需要引导页  fase不需要
   }
   @Override
   public void goGuide() {
       GuideAttributes attributes = new GuideAttributes.Builder(this, MainActivity.class,
               Arrays.asList(R.drawable.guide_01, R.drawable.guide_02, R.drawable.guide_03, R.drawable.guide_04))
               .isTimeEndClick(true)//是否倒计时结束后 才可以点击跳过按钮
               .isCountdown(true)//是否开启倒计时
               .isIndicator(true)//是否显示指示器
               .isSkipText(true)//是否显示跳过按钮
               .isTimeClose(true)//倒计时结束后 是否自动跳转
               .isProgress(true)//是否显示倒计时 进度条
               .isEndIndexClick(true)//是否为引导页最后一页添加点击跳转
               .indicatorUnSelectColor(R.color.colorAccent)//指示器未选中颜色
               .indicatorSelectColor(R.color.colorAccent)//指示器选中颜色
               .skipTime(10)//倒计时时间
               .skipTextColor(R.color.colorAccent)//跳过按钮文字颜色
               .skipTextBackgroundColor(R.color.colorAccent)//跳过按钮背景色
               .skipProgressColor(R.color.colorAccent)//跳过按钮进度条颜色
               .build();
       GuideActivity.show(attributes);
   }
   @Override
   public void goMain() {
       AdPageAttributes adPageAttributes = new AdPageAttributes.Builder(this, MainActivity.class,
               "http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c87a3add4d52a6059252da61e.jpg")
               .isToAdvertising(true)//点击广告页图片是否跳转WebView Activity
               .isTimeEndClick(true)//是否倒计时结束后 才可以点击跳过按钮
               .isCountdown(true)//是否开启倒计时
               .isSkipText(true)//是否显示跳过按钮
               .isTimeClose(true)//倒计时结束后 是否自动跳转
               .isProgress(true)//是否显示倒计时 进度条
               .advertisingUrl("https://www.tmall.com/")//跳转WebView的url
               .advertisingTitle("天猫超市")//跳转WebView的标题
               .skipTextBackgroundColor(R.color.colorAccent)//跳过按钮背景色
               .scaleType(ImageView.ScaleType.CENTER)//广告页ImageView的填充方式
               .skipTextColor(R.color.colorAccent)//跳过按钮文字颜色
               .skipProgressColor(R.color.colorAccent)//跳过按钮进度条颜色
               .skipTime(10)//倒计时时间
               .build();
       AdvertisingActivity.show(adPageAttributes);
   }
```



 
    
