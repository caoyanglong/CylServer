package com.cyl.cylserver.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.cyl.cylserver.R;

/**
 * 提供方法可以 画 在圆上  或者 各种估画 各种图像
 * 目前的实现的是在圆上 画 小圆点
 */
public class HalfProgressBar extends View {
    private int maxProgress = 100;
    //设置进度条背景宽度
    private float progressStrokeWidth = 3;
    //设置进度条进度宽度
    private float marxArcStorkeWidth = 6;
    //设置进度条圆点的宽度
    private float circularDotWidth = 15;

    private int progressHeigh = 90;
    private int progressWidth = 140;
    //小圆点半径
    private float pointRadius = 10;
    /**
     * 每个小圆点 之间的距离
     */
    private int ponitStep = 5;
    /**
     * 小圆 的大小
     */
    private double smallCircle = 0.5;
    /**
     * 画笔对象的引用
     */
    private Paint paint;

    public synchronized int getProgress() {
        return progress;
    }

    /**
     * Android提供了Invalidate方法实现界面刷新，但是Invalidate不能直接在线程中调用，因为他是违背了单线程模型：Android UI操作并不是线程安全的，并且这些操作必须在UI线程中调用。
     * 而postInvalidate()在工作者线程中被调用 使用postInvalidate则比较简单，不需要handler，直接在线程中调用postInvalidate即可。
     *
     * @param progress 传过来的进度
     */
    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }
        if (progress > maxProgress) {
            progress = maxProgress;
        }
        if (progress <= maxProgress) {
            this.progress = progress;
            postInvalidate();
        }
    }

    /**
     * 当前进度
     */
    private int progress = 99;

    private RectF oval;
    private int roundProgressColor;
    private int roundColor;
    private int circularDotColor;
    /**
     * 为未中颜色
     */
    private int circularDotGrey = Color.parseColor("#FFF000");

    public HalfProgressBar(Context context) {
        super(context);
    }

    public HalfProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        oval = new RectF();
        //这是自定义view 必须要写的
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.HalfProgressBar);
        roundProgressColor = mTypedArray.getColor(R.styleable.HalfProgressBar_roundProgressColor1, Color.YELLOW);
        roundColor = mTypedArray.getColor(R.styleable.HalfProgressBar_roundColor1, Color.YELLOW);
        circularDotColor = mTypedArray.getColor(R.styleable.HalfProgressBar_circularDotColor1, Color.YELLOW);
        progress = mTypedArray.getInt(R.styleable.HalfProgressBar_circularProgress, progress);
        pointRadius = mTypedArray.getDimension(R.styleable.HalfProgressBar_pointRadius, pointRadius);
        ponitStep = mTypedArray.getInteger(R.styleable.HalfProgressBar_ponitStep, ponitStep);
        circularDotGrey = mTypedArray.getColor(R.styleable.HalfProgressBar_circularDotGrey, circularDotGrey);
    }

    public HalfProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO 自动生成的方法存根
        super.onDraw(canvas);
        paint.setAntiAlias(true); // 设置画笔为抗锯齿
        paint.setColor(roundColor); // 设置画笔颜色

        paint.setStrokeWidth(progressStrokeWidth); // 线宽
        paint.setStyle(Paint.Style.STROKE);

        oval.left = marxArcStorkeWidth / 2; // 左上角x
        oval.top = circularDotWidth; // 左上角y
        oval.right = progressWidth - circularDotWidth / 2; // 左下角x
        oval.bottom = progressWidth - circularDotWidth / 2; // 右下角y


        //调整圆背景的大小
        canvas.drawArc(oval, 0, 360, false, paint); // 绘制红丝圆圈，即进度条背景
        oval = new RectF(0, 0, oval.right + 10
                , oval.bottom + 10);
        canvas.drawRect(oval, paint);
        float bangjing = ((progressWidth - circularDotWidth / 2) / 2);//半径
//        oval = new RectF(oval.left-10,oval.top-10,oval.right-10,oval.bottom-10);
        //进度条颜色
        paint.setColor(roundProgressColor);
        paint.setStrokeWidth(marxArcStorkeWidth);
        canvas.drawArc(oval, 0, 360, false, paint); // 绘制进度圆弧，这里是蓝色


        //画圆点
        paint.setColor(circularDotColor);
        paint.setAntiAlias(true); // 设置画笔为抗锯齿
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(circularDotWidth);
        //当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式Cap.ROUND,或方形样式Cap.SQUARE
        paint.setStrokeCap(Paint.Cap.ROUND);

        for (int i = 0; i < 100; i = i + ponitStep) {
            float jindu = ((float) i * 1.8f * 2);
            float x = bangjing - ((float) (Math.sin((Math.PI / (double) 180) * (jindu <= 90 ? 90 - (jindu) : -jindu + 90))) * bangjing);
            float y = bangjing + circularDotWidth - ((float) (Math.cos((Math.PI / (double) 180) * (double) (jindu <= 90 ? 90 - jindu : -jindu + 90))) * bangjing);
//
            if (i <= progress) {
                canvas.drawCircle(x + 2, y, pointRadius, paint);
            } else {
                paint.setColor(circularDotGrey);
                canvas.drawCircle(x, y, pointRadius, paint);
            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.progressWidth = getWidth();
        this.progressHeigh = getHeight();
    }
}