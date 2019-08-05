# Welcome
引导页启动页广告页封装
添加依赖    implementation 'com.github.AndroidLMY:Welcome:1.0.0'
新建activity继承WelcomeActivity实现
    @Override
    public void goGuide() {
        //设置引导页等待时间60秒，引导页图片，最终跳转的activity
        GuideActivity.show(this, 60, new int[]{
                R.drawable.bg_ic,
                R.drawable.bg_ic,
                R.drawable.bg_ic,
                R.drawable.bg_ic,
                R.drawable.bg_ic
        }, MainActivity.class);
    }

    @Override
    public void goMain() {
        gohome();
        finish();
    }
    如果不需要实现广告页面直接在gomain处理跳转主页面即可
    需要广告页gomain方法中设置如下
       @Override
    public void goMain() {
        //设置没有网络时广告位默认图片
        AdvertisingActivity.setImageDefault(R.drawable.bg_ic);
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
    
