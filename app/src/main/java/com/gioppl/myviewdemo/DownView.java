package com.gioppl.myviewdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * create time：2019/4/13 11:29
 * creater：17664
 * desc:
 */
public class DownView extends View {
    private static final float BIZER_CONSTANT = 0.5522f;
    private CirclePoint pointCircle;
    private ArrayList<CirclePoint> points;
    private Path mArrowPath;
    private Path circleLinePath;
    private Paint mArrowPaint;
    private Paint mTextPaint;
    private Paint circlePaint1, circleLinePaint;
    private ValueAnimator animator;
    private static int circleAnimatorValue = 1;
    private int radius = 0;
    private RectF mCircleRectF;

    public DownView(Context context) {
        super(context);
        Log.i("DownView", "1");
        init();
    }

    public DownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("DownView", "2");//这个
        init();
    }

    public DownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("DownView", "3");
        init();
    }

    private void init() {
        mArrowPaint = new Paint();
        mArrowPaint.setColor(Color.WHITE);
        mArrowPaint.setAntiAlias(true);
        mArrowPaint.setDither(true);
        mArrowPaint.setStyle(Paint.Style.STROKE);
        mArrowPaint.setStrokeWidth(5);
        mArrowPaint.setPathEffect(new CornerPathEffect(10f));

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setStrokeWidth(3);
        mTextPaint.setTextSize(50f);
        mTextPaint.setPathEffect(new CornerPathEffect(10f));

        circlePaint1 = new Paint();
        circlePaint1.setColor(Color.WHITE);
        circlePaint1.setAntiAlias(true);
        circlePaint1.setDither(true);
        circlePaint1.setStyle(Paint.Style.FILL);
        circlePaint1.setStrokeWidth(3);
        circlePaint1.setTextSize(100f);
        circlePaint1.setPathEffect(new CornerPathEffect(10f));

        circleLinePaint = new Paint();
        circleLinePaint.setColor(Color.WHITE);
        circleLinePaint.setAntiAlias(true);
        circleLinePaint.setDither(true);
        circleLinePaint.setStyle(Paint.Style.STROKE);
        circleLinePaint.setStrokeWidth(3);
        circleLinePaint.setTextSize(50f);
        circleLinePaint.setPathEffect(new CornerPathEffect(10f));


        mArrowPath = new Path();
        circleLinePath = new Path();
        mCircleRectF = new RectF(400, 400, 600, 600);

        pointCircle = new CirclePoint(500, 500, 200);
        points=new ArrayList<>();
//        CirclePoint point1 = new CirclePoint(pointCircle.getX(), pointCircle.getY() - pointCircle.getR());
//        CirclePoint point2 = new CirclePoint(pointCircle.getX() - pointCircle.getR(), pointCircle.getY() - pointCircle.getR());
//        CirclePoint point3 = new CirclePoint(pointCircle.getX() - pointCircle.getR(), pointCircle.getY());
//        CirclePoint point4 = new CirclePoint(pointCircle.getX() - pointCircle.getR(), pointCircle.getY() + pointCircle.getR());
//        CirclePoint point5 = new CirclePoint(pointCircle.getX(), pointCircle.getY() + pointCircle.getR());
//        CirclePoint point6 = new CirclePoint(pointCircle.getX() + pointCircle.getR(), pointCircle.getY() + pointCircle.getR());
//        CirclePoint point7 = new CirclePoint(pointCircle.getX() + pointCircle.getR(), pointCircle.getY());
//        CirclePoint point8 = new CirclePoint(pointCircle.getX() + pointCircle.getR(), pointCircle.getY() - pointCircle.getR());
//


        CirclePoint point0 = new CirclePoint(pointCircle.getX(), pointCircle.getY() - pointCircle.getR());
        CirclePoint point1 = new CirclePoint(pointCircle.getX()-pointCircle.getR()/2, pointCircle.getY() - pointCircle.getR());
        CirclePoint point2 = new CirclePoint(pointCircle.getX()-pointCircle.getR(), pointCircle.getY() - pointCircle.getR()/2);
        CirclePoint point3 = new CirclePoint(pointCircle.getX()-pointCircle.getR(), pointCircle.getY());
        CirclePoint point4 = new CirclePoint(pointCircle.getX()-pointCircle.getR(),pointCircle.getY()+pointCircle.getR()/2);
        CirclePoint point5 = new CirclePoint(pointCircle.getX()-pointCircle.getR()/2, pointCircle.getY()+pointCircle.getR());
        CirclePoint point6= new CirclePoint(pointCircle.getX(), pointCircle.getY()+pointCircle.getR());
        CirclePoint point7 = new CirclePoint(pointCircle.getX()+pointCircle.getR()/2, pointCircle.getY()+pointCircle.getR());
        CirclePoint point8 = new CirclePoint(pointCircle.getX()+pointCircle.getR(), pointCircle.getY()+pointCircle.getR()/2);
        CirclePoint point9 = new CirclePoint(pointCircle.getX()+pointCircle.getR(), pointCircle.getY());
        CirclePoint point10 = new CirclePoint(pointCircle.getX()+pointCircle.getR(), pointCircle.getY()-pointCircle.getR()/2);
        CirclePoint point11 = new CirclePoint(pointCircle.getX()+pointCircle.getR()/2, pointCircle.getY()-pointCircle.getR());
        points.add(point0);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);
        points.add(point6);
        points.add(point7);
        points.add(point8);
        points.add(point9);
        points.add(point10);
        points.add(point11);



    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
//        drawFlag(canvas);
//        drawCircle(canvas);
//        drawCircleLine(canvas);
        drawCircleLine2(canvas);
    }

    private void drawCircleLine2(Canvas canvas) {
        circleLinePath.moveTo(points.get(0).getX(),points.get(0).getY());
        circleLinePath.cubicTo(points.get(1).getX(),points.get(1).getY(),points.get(2).getX(),points.get(2).getY(),points.get(3).getX(),points.get(3).getY());
        circleLinePath.moveTo(points.get(3).getX(),points.get(3).getY());
        circleLinePath.cubicTo(points.get(4).getX(),points.get(4).getY(),points.get(5).getX(),points.get(5).getY(),points.get(6).getX(),points.get(6).getY());
        circleLinePath.moveTo(points.get(6).getX(),points.get(6).getY());
        circleLinePath.cubicTo(points.get(7).getX(),points.get(7).getY(),points.get(8).getX(),points.get(8).getY(),points.get(9).getX(),points.get(9).getY());
        circleLinePath.moveTo(points.get(9).getX(),points.get(9).getY());
        circleLinePath.cubicTo(points.get(10).getX(),points.get(10).getY(),points.get(11).getX(),points.get(11).getY(),points.get(0).getX(),points.get(0).getY());
        canvas.drawPath(circleLinePath, circleLinePaint);
        canvas.drawPoint(points.get(0).getX(),points.get(0).getY(),circlePaint1);
        canvas.drawPoint(points.get(1).getX(),points.get(1).getY(),circlePaint1);
        canvas.drawPoint(points.get(2).getX(),points.get(2).getY(),circlePaint1);
        canvas.drawPoint(points.get(3).getX(),points.get(3).getY(),circlePaint1);
        canvas.drawPoint(points.get(4).getX(),points.get(4).getY(),circlePaint1);
        canvas.drawPoint(points.get(5).getX(),points.get(5).getY(),circlePaint1);
        canvas.drawPoint(points.get(6).getX(),points.get(6).getY(),circlePaint1);
        canvas.drawPoint(points.get(7).getX(),points.get(7).getY(),circlePaint1);
        canvas.drawPoint(points.get(8).getX(),points.get(8).getY(),circlePaint1);
        canvas.drawPoint(points.get(9).getX(),points.get(9).getY(),circlePaint1);
        canvas.drawPoint(points.get(10).getX(),points.get(10).getY(),circlePaint1);
        canvas.drawPoint(points.get(11).getX(),points.get(11).getY(),circlePaint1);

    }

    private void drawCircleLine(Canvas canvas) {
        circleLinePath.moveTo(points.get(0).getX(),points.get(0).getY());
        circleLinePath.quadTo(points.get(1).getX(),points.get(1).getY(),points.get(2).getX(),points.get(2).getY());
        circleLinePath.quadTo(points.get(3).getX(),points.get(3).getY(),points.get(4).getX(),points.get(4).getY());
        circleLinePath.quadTo(points.get(5).getX(),points.get(5).getY(),points.get(6).getX(),points.get(6).getY());
        circleLinePath.quadTo(points.get(7).getX(),points.get(7).getY(),points.get(0).getX(),points.get(0).getY());
        canvas.drawPath(circleLinePath, circleLinePaint);
    }

    private void drawFlag(Canvas canvas) {
        int width = getWidth() - 200;
        int height = getHeight() - 200;
        mArrowPath.moveTo(width / 2, height / 2);
        mArrowPath.lineTo(width / 2 + 200, height / 2);
        mArrowPath.lineTo(width / 2 + 200, height / 2 + 100);
        mArrowPath.lineTo(width / 2 + 130, height / 2 + 100);
        mArrowPath.lineTo(width / 2 + 100, height / 2 + 150);
        mArrowPath.lineTo(width / 2 + 70, height / 2 + 100);
        mArrowPath.lineTo(width / 2, height / 2 + 100);
        mArrowPath.lineTo(width / 2, height / 2 + 100);
        mArrowPath.lineTo(width / 2, height / 2);
        canvas.drawText("100%", width / 2 + 35, height / 2 + 70, mTextPaint);
        canvas.drawPath(mArrowPath, mArrowPaint);
    }

    private void drawCircle(Canvas canvas) {
        int layoutCont = canvas.saveLayer(mCircleRectF, circlePaint1, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(500, 500, 100, circlePaint1);
        circlePaint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawCircle(500, 500, radius, circlePaint1);
        circlePaint1.setXfermode(null);
        Log.i("园", "" + radius);
        canvas.restoreToCount(layoutCont);
    }

    private void addCircleAnimation() {


        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(radius, 90);
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animatorSet.playSequentially(valueAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                Log.i("动画", "onAnimationCancel");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.i("动画", "onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                Log.i("动画", "onAnimationRepeat");
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Log.i("动画", "onAnimationStart");
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
                Log.i("动画", "onAnimationPause");
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
                Log.i("动画", "onAnimationResume");
            }
        });
        animatorSet.start();


    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                addCircleAnimation();
                Log.i("Down", "(" + event.getX() + "," + event.getY() + ")");
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.i("Move","("+event.getX()+","+event.getY()+")");
                break;
            case MotionEvent.ACTION_UP:
//                Log.i("Up","("+event.getX()+","+event.getY()+")");
                break;
        }
        return true;
    }

}
