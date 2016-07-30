package com.cyl.cylserver.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.cyl.cylserver.R;

/**
 * Created by cyl
 * on 2016/7/30.
 * email:670654904@qq.com
 * 圆形的  在圆形上画小圆点
 */
public class CirclePointProgressBar extends View {
    private Paint paint;
    /**
     * 小圆点 的半径
     */
    private float pointRadius = 2;
    /**
     * 小圆点 的选中颜色
     */
    private int pointSelectedColor = Color.parseColor("#FFFFFF");
    /**
     * 小圆点 的未选中颜色
     */
    private int pointUnSelectedColor = Color.GRAY;
    /**
     * 整个圆的进度
     */
    private int circleProgress = 10;
    /**
     * 整个圆形的半径 最大不能大于 所选区域的 宽度的一半
     */
    private float circleBarRadius = 30;

    /**
     * 小圆点在
     */
    private int circlePointStep = 2;


    public CirclePointProgressBar(Context context) {
        super(context);
    }

    public CirclePointProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CirclePointProgressBar);
        this.circleBarRadius = typedArray.getDimension(R.styleable.CirclePointProgressBar_circleBarRadius,circleBarRadius);
        this.circlePointStep = typedArray.getInteger(R.styleable.CirclePointProgressBar_circlePointStep,circlePointStep);
        this.circleProgress = typedArray.getInteger(R.styleable.CirclePointProgressBar_circleProgress,circleProgress);
        this.pointRadius = typedArray.getDimension(R.styleable.HalfProgressBar_pointRadius,pointRadius);
        this.pointSelectedColor = typedArray.getColor(R.styleable.CirclePointProgressBar_pointSelectedColor,pointSelectedColor);
        this.pointUnSelectedColor = typedArray.getColor(R.styleable.CirclePointProgressBar_pointUnSelectedColor,pointUnSelectedColor);
        paint = new Paint();
        paint.setColor(pointSelectedColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        //资源回收
        typedArray.recycle();
    }

    public CirclePointProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
