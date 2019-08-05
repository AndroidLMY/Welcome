# Welcome引导页启动页广告页封装
## 添加依赖<br>
implementation 'com.github.AndroidLMY:Welcome:1.0.0'<br>
## 使用方法<br>
新建activity继承WelcomeActivity实现<br>
    @Override<br>
    public void goGuide() {<br>
        //设置引导页等待时间60秒，引导页图片，最终跳转的activity<br>
        GuideActivity.show(this, 60, new int[]{<br>
                R.drawable.bg_ic,<br>
                R.drawable.bg_ic,<br>
                R.drawable.bg_ic,<br>
                R.drawable.bg_ic,<br>
                R.drawable.bg_ic<br>
        }, MainActivity.class);<br>
    }<br>
<br>
    @Override<br>
    public void goMain() {<br>
        gohome();<br>
        finish();<br>
    }<br>
    如果不需要实现广告页面直接在gomain处理跳转主页面即可<br>
    需要广告页gomain方法中设置如下<br>
       @Override
    public void goMain() {
        //设置广告位图片的url
        AdvertisingActivity.setImageUrl("http://47.100.250.181:8080/images/37WKKVZF.jpg");
        //设置是否显示跳过广告按钮
        AdvertisingActivity.setIsSkip(true);
        //设置广告默认等待时间3秒
        AdvertisingActivity.setSkipTime(3);
        //启动广告页
        AdvertisingActivity.show(this, MainActivity.class);
        //一定要finish当前页面
        finish();
    }
    
