package com.gioppl.myviewdemo;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * create time：2019/4/13 11:29
 * creater：17664
 * desc:
 */
public class DownView extends View {

    private PointBean pointCircle;
    private PointBean pointCircle2;

    //几种状态
    public static MoveStatus moveStatus = MoveStatus.STATUS0;//初始化最开始的状态为0


    public enum MoveStatus {
        STATUS0,
        STATUS1,
        ERROR_STATUS
    }


    //画笔
    public Paint paint_black_fill_5, paint_white_stroke_15, paint_black_stroke_5, paint_white_fill_5;


    //动画的value
    private int value_disk_to_circle = 0;//圆盘变成同心圆
    private int value_circle_to_line = 0;//贝塞尔画的圆变成线
    private int value_line_up_and_down = 0;//线条上下震动
    private int value_arrow_to_scutcheon;//箭头变成标牌


    private long theAnimationExecuteTime = 500;//动画时间


    public DownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    //初始化画笔和圆
    private void init() {
        pointCircle = new PointBean(500, 500, 100);
        pointCircle2 = new PointBean(700, 500, 100);
        paint_black_fill_5 = MyPaint.getPaintByAntiAliasAndDither(Color.BLACK, Paint.Style.FILL, 5);
        paint_white_stroke_15 = MyPaint.getPaintByAntiAliasAndDither(Color.WHITE, Paint.Style.STROKE, 15);
        paint_black_stroke_5 = MyPaint.getPaintByAntiAliasAndDither(Color.BLACK, Paint.Style.STROKE, 5);
        paint_white_fill_5 = MyPaint.getPaintByAntiAliasAndDither(Color.WHITE, Paint.Style.FILL, 5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
//        MyCanvas.drawCircle(canvas, value_disk_to_circle, paint_white_fill_5, this, pointCircle, MyPath.BezierCircle(pointCircle));//饼渐变圆
        MyCanvas.drawCircleLine4_2(canvas, value_circle_to_line, paint_white_stroke_15, this, pointCircle, MyPath.BezierCircle(pointCircle));//四个点的贝塞尔圆

//        MyCanvas.drawArrow(canvas,value_circle_to_line,paint_white_stroke_5,this,pointCircle,MyPath.arrowPath(pointCircle));//箭头
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ValueAnimator valueAnimator = animationCircleToLine2();
                valueAnimator.start();
                break;
        }
        return true;
    }

    //圆渐变成同心圆的动画
    private ValueAnimator animationDiskToCircle() {
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, (int) (pointCircle.getR()-15));
        valueAnimator.setDuration(theAnimationExecuteTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_disk_to_circle = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        return valueAnimator;
    }


    //让圆形上部分展开动画2
    private ValueAnimator animationCircleToLine2() {
        ValueAnimator controllerPointAnimation1 = ValueAnimator.ofInt(0, (int) (pointCircle.getR() /4));
        controllerPointAnimation1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_circle_to_line = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        controllerPointAnimation1.setDuration(theAnimationExecuteTime *3);
        return controllerPointAnimation1;
    }

    //让圆形上部分展开动画
    private ValueAnimator animationCircleToLine() {
        ValueAnimator controllerPointAnimation1 = ValueAnimator.ofInt(0, (int) (pointCircle.getR() * 2));
        controllerPointAnimation1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_circle_to_line = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        controllerPointAnimation1.setDuration(theAnimationExecuteTime *10);
        return controllerPointAnimation1;
    }

    //弧线向上震荡的动画
    private ValueAnimator animationLineVibrate() {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(120, value_line_up_and_down - 120);
        valueAnimator.setDuration(theAnimationExecuteTime);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(20);//设置重复次数。
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_line_up_and_down = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        return valueAnimator;
    }

    //从箭头变成标牌的动画
    private ValueAnimator animationArrowToScutcheon() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, (int) pointCircle.getR());
        valueAnimator.setDuration(theAnimationExecuteTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_arrow_to_scutcheon = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        return valueAnimator;
    }












}
