package com.eric.welcome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * @author gjy 引导页 底部索引
 **/
public class IndexChangeView extends View {
    private final static String TAG = IndexChangeView.class.getSimpleName();
    private Paint first;
    private Paint second;
    private int currentIndex = 0; //当前位置
    private int radius = 15, distance = 40;//圆的半径,间距
    private int number, defaultcolor = Color.GRAY, selectColor = Color.RED;//数量，默认颜色，当前颜色
    private ArrayList<Float> widthLists;

    private float height = 0;

    public IndexChangeView(Context context) {
        super(context);
    }

    public IndexChangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IndexChangeView);
        init(a);
    }

    public IndexChangeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IndexChangeView, defStyleAttr, 0);
        init(a);
    }

    /**
     * 通过自定义属性 查找 索引的个数
     *
     * @param a
     */
    private void init(TypedArray a) {
        number = a.getInt(R.styleable.IndexChangeView_number, 1);
        defaultcolor = a.getInt(R.styleable.IndexChangeView_defaultColor, defaultcolor);
        selectColor = a.getInt(R.styleable.IndexChangeView_selectColor, selectColor);
        distance = a.getInt(R.styleable.IndexChangeView_spaceBetween, distance);
        radius = a.getInt(R.styleable.IndexChangeView_dotRadius, radius);
        a.recycle();
        first = getPaint(8, Paint.Style.FILL, selectColor);
        second = getPaint(8, Paint.Style.FILL, defaultcolor);
        this.getWidth();
        this.getHeight();
        widthLists = new ArrayList();
    }

    /**
     * 设置画笔
     *
     * @param strokeWidth 画笔宽度
     * @param style       画笔风格  填充
     * @param color       画笔颜色
     * @return
     */
    private Paint getPaint(int strokeWidth, Paint.Style style, int color) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(style);
        paint.setColor(color);
        paint.setAntiAlias(true);
        return paint;
    }

    /**
     * 设置点的半径
     *
     * @param radius 15
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * 设置默认颜色
     *
     * @param defaultcolor
     */
    public void setDefaultcolor(int defaultcolor) {
        this.defaultcolor = defaultcolor;
    }

    /**
     * 当前颜色
     *
     * @param selectColor 圆点当前颜色
     */
    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    /**
     * 圆点数量
     *
     * @param number 根据视图确认数量
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 圆点初始位置
     *
     * @return
     */
    public int getNumber() {
        return number;
    }
    /**
     * 圆点当前位置
     *
     * @return
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * 视图颜色初始位置
     * @param i
     */
    public void setViewColorChange(int i) {
        currentIndex = i;
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { // TODO
        super.onSizeChanged(w, h, oldw, oldh);
        height = h / 2;
        // 获取一半的个数
        int half;
        if (number % 2 == 0) {
            half = number / 2;
        } else {
            half = (number - 1) / 2;
        } // 每个索引球距离为 20
        for (int i = 0; i < number; i++) {
            // 偶数个
            if (number % 2 == 0) {
                if (half - 1 == i) {
                    float temp = w / 2 - 10;
                    widthLists.add(i, temp);
                } else if (half == i) {
                    float temp = w / 2 + 10;
                    widthLists.add(i, temp);
                } else if (i < half - 1) {
                    // 一般宽度 - 使用过的宽度
                    float temp = w / 2 - ((half - (i + 1)) *distance + 10);
                    widthLists.add(i, temp);
                } else if (i > half) {
                    float temp = w / 2 + ((i - half) * distance + 10);
                    widthLists.add(i, temp);
                }
            } else {
                // 奇数个
                if (i == half) {
                    float temp = w / 2;
                    widthLists.add(i, temp);
                } else if (i < half) {
                    float temp = w / 2 - ((half - i) * distance);
                    widthLists.add(i, temp);
                } else {
                    float temp = w / 2 + ((i - half) * distance);
                    widthLists.add(i, temp);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < number; i++) {
            if (i == currentIndex) {
                Log.i(TAG, "索引:======" + currentIndex);
                canvas.drawCircle(widthLists.get(i), height, radius, first);
                continue;
            }
            canvas.drawCircle(widthLists.get(i), height, radius, second);
        }
    }
}
