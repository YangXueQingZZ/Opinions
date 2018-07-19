package com.taowei.opinions.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.taowei.opinions.R;


/**
 * Created by lanfanxing on 2018/3/10.
 */

public class TasksCompletedView extends ProgressBar {



    // 画最外边圆环的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    //矩形画笔
    private Paint mRectPaint;
    // 圆环颜色
    private int mRingColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 矩形宽度
    private float rectWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 总进度
    private int mTotalProgress = 100;
    // 当前进度
    private int mProgress;
    // 最外层圆的宽度
    private int mcircleWidth = 6;



    public TasksCompletedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        initVariable();
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TasksCompletedView, 0, 0);
        mRadius = typeArray.getDimension(R.styleable.RoundImageView_radius, 40);
        mStrokeWidth = typeArray.getDimension(R.styleable.TasksCompletedView_strokeWidth, 10);
        rectWidth = typeArray.getDimension(R.styleable.TasksCompletedView_myrectWidth, 15);
        mRingColor = typeArray.getColor(R.styleable.TasksCompletedView_ringColor, 0xFFFFFFFF);

        mRingRadius = mRadius + mStrokeWidth / 2 - mcircleWidth/2;
    }


    private void initVariable() {
//最外层圆形画笔
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mRingColor);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mcircleWidth);

//动态圆弧画笔
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
//动态弧形的宽度
        mRingPaint.setStrokeWidth(mStrokeWidth);

        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mRectPaint.setColor(mRingColor);
        mRectPaint.setStyle(Paint.Style.FILL);

    }


    @Override
    protected void onDraw(Canvas canvas) {


        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;
        //画出最外层的圆
        canvas.drawCircle(mXCenter, mYCenter, mRadius+mcircleWidth, mCirclePaint);
        //画出里面的矩形
        canvas.drawRect(mXCenter-rectWidth, mYCenter-rectWidth, mXCenter+rectWidth, mYCenter+rectWidth, mRectPaint);

        //动态画圆环
        if (mProgress > 0 ) {
            RectF oval = new RectF();
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            canvas.drawArc(oval, -90, ((float)mProgress / mTotalProgress) * 360, false, mRingPaint); //
        }
    }

    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();
    }



}
