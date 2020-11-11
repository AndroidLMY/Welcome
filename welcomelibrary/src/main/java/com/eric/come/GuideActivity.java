package com.eric.come;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.eric.come.utils.GuideAttributes;

import java.util.List;


/**
 * @author lmy
 * @功能: 引导页界面
 * @Creat 2020/11/11 4:19 PM
 * @Compony 465008238@qq.com
 */

public class GuideActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private CountDownTextView tvTime;
    private CountDownTimer countDownTimer;
    private boolean isScrolled;
    private RecyclerView listindex;
    private IndexAdapter adapter;
    private static GuideAttributes guideAttributes;

    public static void show(GuideAttributes guideAttribute) {
        guideAttributes = guideAttribute;
        guideAttributes.getStartactivity().startActivity(new Intent(guideAttributes.getStartactivity(), GuideActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.guide_activity);
        guideAttributes.getStartactivity().finish();
        initViews();
    }

    public void initViews() {
        viewpager = findViewById(R.id.viewpager);
        listindex = findViewById(R.id.listindex);
        tvTime = findViewById(R.id.tv_time);
        GradientDrawable myShape = (GradientDrawable) findViewById(R.id.tv_time).getBackground();
        myShape.setColor(guideAttributes.getSkipTextBackgroundColor() == 0 ? getResources().getColor(R.color.skipBgColor) : getResources().getColor(guideAttributes.getSkipTextBackgroundColor()));
        tvTime.setTextColor(guideAttributes.getSkipTextColor() == 0 ? getResources().getColor(R.color.colorWhite) : getResources().getColor(guideAttributes.getSkipTextColor()));
        tvTime.setPargsColors(guideAttributes.getSkipProgressColor() == 0 ? R.color.colorAccent : guideAttributes.getSkipProgressColor());
        listindex.setVisibility(guideAttributes.isIndicator() ? View.VISIBLE : View.GONE);
        if (guideAttributes.isSkip()) {
            tvTime.setVisibility(View.VISIBLE);
            if (guideAttributes.isCountdown()) {
                setCountdown(tvTime, guideAttributes.getSkipTime());
            } else {
                setUnCountdown();
            }
        } else {
            tvTime.setVisibility(View.GONE);
        }
        initData();
    }

    private void setUnCountdown() {
        tvTime.setText("跳过");
        tvTime.setOnClickListener(v -> {
            closeCountdown();
        });

    }

    public void initData() {
        adapter = new IndexAdapter(this, guideAttributes.getImagesList(), guideAttributes, 0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        listindex.setLayoutManager(linearLayoutManager);
        listindex.setAdapter(adapter);
        initAdapter();
    }

    /**
     * 第二步  加入适配器
     * true 不带滑动跳转   false  带滑动跳转
     */
    public void initAdapter() {
        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(guideAttributes.getImagesList());
        viewpager.setAdapter(vpAdapter);
        viewpager.setOffscreenPageLimit(guideAttributes.getImagesList().size());
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    adapter.setIndex(position);
                } else {
                    adapter.setIndex(position);
                }
                onPageListener(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (false) {
                    return;
                }
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        isScrolled = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        isScrolled = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (viewpager.getCurrentItem() == viewpager.getAdapter().getCount() - 1 && !isScrolled) {
                            closeCountdown();
                        }
                        isScrolled = true;
                        break;
                }
            }
        });
    }

    /**
     * 设置倒计时
     *
     * @param textView
     * @param time     1000 = 1秒;
     */
    public void setCountdown(final CountDownTextView textView, int time) {
        if (!guideAttributes.isTimeEndClick()) {
            textView.setOnClickListener(v -> {
                textView.setEnabled(false);
                closeCountdown();
            });
        }
        if (guideAttributes.isProgress()) {
            tvTime.setDuration(guideAttributes.getSkipTime());
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
                if (guideAttributes.isTimeClose()) {
                    closeCountdown();
                }
                if (guideAttributes.isTimeEndClick()) {
                    textView.setOnClickListener(v -> {
                        textView.setEnabled(false);
                        closeCountdown();
                    });
                }
            }
        }.start();
    }

    public void closeCountdown() {
        if (countDownTimer != null)
            countDownTimer.cancel();
        goHomePage();

    }

    public void goHomePage() {
        SharedPreferences sharedPreferences = getSharedPreferences("Defaulthomepage", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirstRun", false);
        editor.commit();
        Intent intent = new Intent(this, guideAttributes.getEndActivity());
        startActivity(intent);
        finish();
    }

    private void onPageListener(int position) {
    }

    /**
     * 引导页的page适配器
     */
    private class ViewPagerAdapter extends PagerAdapter {

        private List<Integer> images;

        public ViewPagerAdapter(List<Integer> images) {
            this.images = images;
        }

        // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
        @Override
        public int getCount() {
            return images.size();
        }

        // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        /**
         * 销毁条目
         * PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
         */
        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView((View) object);
        }

        /**
         * 返回要显示的view,即要显示的视图
         */
        @Override
        public Object instantiateItem(ViewGroup viewGroup, int position) {
            View view = LayoutInflater.from(GuideActivity.this).inflate(R.layout.guide_image, null);
            ImageView imageView = view.findViewById(R.id.image);
            if (guideAttributes.isEndIndexClick()) {
                imageView.setOnClickListener(v -> {
                    if (position == images.size() - 1) {
                        closeCountdown();
                    } else {
                    }
                });
            }
            Glide.with(GuideActivity.this).load(images.get(position)).into(imageView);
            viewGroup.addView(view);
            return view;
        }
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

}
