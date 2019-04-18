package com.gioppl.myviewdemo;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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
    private static int circleAnimatorValue = 1;
    //动画的value
    private int radius = 0;
    private int upValue=0;
    private int upValue2=0;


    private enum MoveStatus {
        STATUS0,
        STATUS1,
        STATUS2,
        STATUS3,
        STATUS4,
    }
    private AnimatorSet animatorSet;
    private MoveStatus moveStatus = MoveStatus.STATUS0;

    //四大控制点,动画value
    private int controllerValue = 0;
    private long theAnimationExecuteTime = 500;//动画时间
    private int controllerValue2 = 0;

    public DownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("DownView", "2");//这个
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

        pointCircle = new CirclePoint(500, 500, 100);
        points = new ArrayList<>();
        CirclePoint point0 = new CirclePoint(pointCircle.getX(), pointCircle.getY() - pointCircle.getR());
        CirclePoint point1 = new CirclePoint(pointCircle.getX() - pointCircle.getR(), pointCircle.getY() - pointCircle.getR());
        CirclePoint point2 = new CirclePoint(pointCircle.getX() - pointCircle.getR(), pointCircle.getY());
        CirclePoint point3 = new CirclePoint(pointCircle.getX() - pointCircle.getR(), pointCircle.getY() + pointCircle.getR());
        CirclePoint point4 = new CirclePoint(pointCircle.getX(), pointCircle.getY() + pointCircle.getR());
        CirclePoint point5 = new CirclePoint(pointCircle.getX() + pointCircle.getR(), pointCircle.getY() + pointCircle.getR());
        CirclePoint point6 = new CirclePoint(pointCircle.getX() + pointCircle.getR(), pointCircle.getY());
        CirclePoint point7 = new CirclePoint(pointCircle.getX() + pointCircle.getR(), pointCircle.getY() - pointCircle.getR());
        CirclePoint point8 = new CirclePoint(pointCircle.getX(), pointCircle.getY() - pointCircle.getR());
        points.add(point0);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);
        points.add(point6);
        points.add(point7);
        points.add(point8);


//        CirclePoint point0 = new CirclePoint(pointCircle.getX(), pointCircle.getY() - pointCircle.getR());
//        CirclePoint point1 = new CirclePoint(pointCircle.getX()-pointCircle.getR()/2, pointCircle.getY() - pointCircle.getR());
//        CirclePoint point2 = new CirclePoint(pointCircle.getX()-pointCircle.getR(), pointCircle.getY() - pointCircle.getR()/2);
//        CirclePoint point3 = new CirclePoint(pointCircle.getX()-pointCircle.getR(), pointCircle.getY());
//        CirclePoint point4 = new CirclePoint(pointCircle.getX()-pointCircle.getR(),pointCircle.getY()+pointCircle.getR()/2);
//        CirclePoint point5 = new CirclePoint(pointCircle.getX()-pointCircle.getR()/2, pointCircle.getY()+pointCircle.getR());
//        CirclePoint point6= new CirclePoint(pointCircle.getX(), pointCircle.getY()+pointCircle.getR());
//        CirclePoint point7 = new CirclePoint(pointCircle.getX()+pointCircle.getR()/2, pointCircle.getY()+pointCircle.getR());
//        CirclePoint point8 = new CirclePoint(pointCircle.getX()+pointCircle.getR(), pointCircle.getY()+pointCircle.getR()/2);
//        CirclePoint point9 = new CirclePoint(pointCircle.getX()+pointCircle.getR(), pointCircle.getY());
//        CirclePoint point10 = new CirclePoint(pointCircle.getX()+pointCircle.getR(), pointCircle.getY()-pointCircle.getR()/2);
//        CirclePoint point11 = new CirclePoint(pointCircle.getX()+pointCircle.getR()/2, pointCircle.getY()-pointCircle.getR());
//        points.add(point0);
//        points.add(point1);
//        points.add(point2);
//        points.add(point3);
//        points.add(point4);
//        points.add(point5);
//        points.add(point6);
//        points.add(point7);
//        points.add(point8);
//        points.add(point9);
//        points.add(point10);
//        points.add(point11);

        animatorSet = new AnimatorSet();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        switch (moveStatus) {
            case STATUS0:
                drawCircle(canvas);
                break;
            case STATUS1:
                drawCircleLine(canvas);
                break;
            case STATUS2:
                drawWave(canvas);
                break;
            case STATUS3:
                break;
            case STATUS4:
                break;
        }
    }


    private void drawCircleLine2(Canvas canvas) {
        circleLinePath.moveTo(points.get(0).getX(), points.get(0).getY());
        circleLinePath.cubicTo(points.get(1).getX(), points.get(1).getY(), points.get(2).getX(), points.get(2).getY(), points.get(3).getX(), points.get(3).getY());
        circleLinePath.moveTo(points.get(3).getX(), points.get(3).getY());
        circleLinePath.cubicTo(points.get(4).getX(), points.get(4).getY(), points.get(5).getX(), points.get(5).getY(), points.get(6).getX(), points.get(6).getY());
        circleLinePath.moveTo(points.get(6).getX(), points.get(6).getY());
        circleLinePath.cubicTo(points.get(7).getX(), points.get(7).getY(), points.get(8).getX(), points.get(8).getY(), points.get(9).getX(), points.get(9).getY());
        circleLinePath.moveTo(points.get(9).getX(), points.get(9).getY());
        circleLinePath.cubicTo(points.get(10).getX(), points.get(10).getY(), points.get(11).getX(), points.get(11).getY(), points.get(0).getX(), points.get(0).getY());
        canvas.drawPath(circleLinePath, circleLinePaint);
        canvas.drawPoint(points.get(0).getX(), points.get(0).getY(), circlePaint1);
        canvas.drawPoint(points.get(1).getX(), points.get(1).getY(), circlePaint1);
        canvas.drawPoint(points.get(2).getX(), points.get(2).getY(), circlePaint1);
        canvas.drawPoint(points.get(3).getX(), points.get(3).getY(), circlePaint1);
        canvas.drawPoint(points.get(4).getX(), points.get(4).getY(), circlePaint1);
        canvas.drawPoint(points.get(5).getX(), points.get(5).getY(), circlePaint1);
        canvas.drawPoint(points.get(6).getX(), points.get(6).getY(), circlePaint1);
        canvas.drawPoint(points.get(7).getX(), points.get(7).getY(), circlePaint1);
        canvas.drawPoint(points.get(8).getX(), points.get(8).getY(), circlePaint1);
        canvas.drawPoint(points.get(9).getX(), points.get(9).getY(), circlePaint1);
        canvas.drawPoint(points.get(10).getX(), points.get(10).getY(), circlePaint1);
        canvas.drawPoint(points.get(11).getX(), points.get(11).getY(), circlePaint1);

    }

    private void drawCircleLine(Canvas canvas) {
        if ((controllerValue<pointCircle.getR()*2) && (moveStatus==MoveStatus.STATUS1)){
            circleLinePath.moveTo(points.get(0).getX() - controllerValue, points.get(0).getY());
            circleLinePath.quadTo(points.get(1).getX(), points.get(1).getY(), points.get(2).getX(), points.get(2).getY() - controllerValue / 2);
            circleLinePath.quadTo(points.get(3).getX(), points.get(3).getY() - controllerValue, points.get(4).getX(), points.get(4).getY() - controllerValue);
            circleLinePath.quadTo(points.get(5).getX(), points.get(5).getY() - controllerValue, points.get(6).getX(), points.get(6).getY() - controllerValue / 2);
            circleLinePath.quadTo(points.get(7).getX(), points.get(7).getY(), points.get(8).getX() + controllerValue, points.get(8).getY());
            canvas.drawPath(circleLinePath, circleLinePaint);
            circleLinePath.reset();
        }else {
            moveStatus=MoveStatus.STATUS2;
            ValueAnimator valueAnimator1 = upArcAnimation();
            valueAnimator1.start();
            ValueAnimator valueAnimator2 = upArcAnimation2();
            valueAnimator2.start();
            invalidate();
        }
    }

    int offset=0;
    //直线振荡
    private void drawWave(Canvas canvas) {
        float r = pointCircle.getR();
        float x = pointCircle.getX();
        float y = pointCircle.getY()-r;
        Path wavePath = new Path();


        if (moveStatus==MoveStatus.STATUS2) {

                CirclePoint point1=new CirclePoint(x-2*r,y);
                CirclePoint point2=new CirclePoint(x,y-upValue);
                CirclePoint point3=new CirclePoint(x+2*r,y);
                wavePath.moveTo(point1.getX(),point1.getY());
                wavePath.quadTo(point2.getX(),point2.getY(),point3.getX(),point3.getY());
                canvas.drawPath(wavePath,circleLinePaint);
                invalidate();

        }else {

        }
        offset++;
    }
    //画一个箭头
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
    //画圆
    private void drawCircle(Canvas canvas) {
        if (radius<pointCircle.getR()&& moveStatus==MoveStatus.STATUS0){
            float x = pointCircle.getX();
            float y = pointCircle.getY();
            float r = pointCircle.getR();
            canvas.drawCircle(x, y, r, circlePaint1);
            circlePaint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            canvas.drawCircle(x, y, radius, circlePaint1);
            circlePaint1.setXfermode(null);
            invalidate();
        }else {
            moveStatus=MoveStatus.STATUS1;
            invalidate();
        }

    }
    //弧线向上的动画
    private ValueAnimator upArcAnimation() {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(120, upValue-120);
        valueAnimator.setDuration(theAnimationExecuteTime);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(20);//设置重复次数。
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                upValue = (int) animation.getAnimatedValue();
                Log.i("1111111",upValue+"");
                invalidate();
            }
        });
        return valueAnimator;
    }
    //弧线向上的动画
    private ValueAnimator upArcAnimation2() {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 120);
        valueAnimator.setDuration(theAnimationExecuteTime);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(20);//设置重复次数。
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                upValue2 = (int) animation.getAnimatedValue();
                Log.i("2222222",upValue2+"");
                invalidate();
            }
        });
        return valueAnimator;
    }
    //圆渐变的动画
    private ValueAnimator addCircleAnimation() {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(radius, (int) (pointCircle.getR()));
        valueAnimator.setDuration(theAnimationExecuteTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        return valueAnimator;
    }

    //让圆形上部分展开动画
    private ValueAnimator addCircleToLineAnimation_1() {
        ValueAnimator controllerPointAnimation1 = ValueAnimator.ofInt(0, (int) (pointCircle.getR() * 2));
        controllerPointAnimation1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (controllerValue >= pointCircle.getR() * 2 - 1) {
                    circleLinePath.close();
                    circleLinePath.reset();
                }
                controllerValue = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        controllerPointAnimation1.setDuration(theAnimationExecuteTime/2);
        return controllerPointAnimation1;
//        controllerPointAnimation1.start();
    }

    //让圆形上部分展开后向上突起
    private void addCircleToLineAnimation_2() {
        ValueAnimator controllerPointAnimation1 = ValueAnimator.ofInt(0, (int) (pointCircle.getR() * 2));
        controllerPointAnimation1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                controllerValue = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        controllerPointAnimation1.setDuration(theAnimationExecuteTime);
        controllerPointAnimation1.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ValueAnimator valueAnimator = addCircleAnimation();
                ValueAnimator valueAnimator1 = addCircleToLineAnimation_1();
                animatorSet.play(valueAnimator).before(valueAnimator1);
                animatorSet.start();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
