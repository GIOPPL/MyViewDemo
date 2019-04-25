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
        STATUS2,
        STATUS3,
        STATUS4,
        STATUS5,
        STATUS6,
        ERROR_STATUS
    }


    //画笔
    public Paint paint_black_fill_5, paint_white_stroke_15, paint_black_stroke_5, paint_white_fill_5,paint_white_stroke_35;


    //动画的value
    private int value_disk_to_circle = 0;//圆盘变成同心圆
    private int value_circle_to_line = 0;//贝塞尔画的圆变成线
    private int value_line_up_and_down = 0;//线条上下震动
    private int value_arrow_to_scutcheon;//箭头变成标牌
    private float value_simulate_x=0;//模拟下载0-100
    private float value_simulate_y=0;//模拟下载0-r/4
    private float value_overturn=0;//标牌翻转0-180
    private float value_shake=0;//抖一个激灵0->20->-20


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
        paint_white_stroke_15 = MyPaint.getPaintByAntiAliasAndDither(Color.WHITE, Paint.Style.STROKE, 10);
        paint_black_stroke_5 = MyPaint.getPaintByAntiAliasAndDither(Color.BLACK, Paint.Style.STROKE, 5);
        paint_white_fill_5 = MyPaint.getPaintByAntiAliasAndDither(Color.WHITE, Paint.Style.FILL, 5);
        paint_white_stroke_35 = MyPaint.getPaintByAntiAliasAndDither(Color.WHITE, Paint.Style.FILL, 5);
        paint_white_stroke_35.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        switch (moveStatus){
            case STATUS0://由饼变成圆
                MyCanvas.drawCircle(canvas, value_disk_to_circle, paint_white_fill_5, this, pointCircle, MyPath.BezierCircle(pointCircle));//饼渐变圆
                MyCanvas.drawArrow(canvas, value_arrow_to_scutcheon, paint_black_fill_5, MyPath.arrowPath(pointCircle));//静止箭头

                break;
            case STATUS1://由圆变成弧线
                MyCanvas.drawArrow(canvas, value_arrow_to_scutcheon, paint_black_fill_5, MyPath.arrowPath(pointCircle));//静止箭头箭头
                MyCanvas.drawCircleLine4_2(canvas, value_circle_to_line, paint_white_stroke_15, this, pointCircle, MyPath.BezierCircle(pointCircle));//四个点的贝塞尔圆
                break;
            case STATUS2://弧线变直伴随着箭头往上移动
                MyCanvas.arrowUpToZeroLine(canvas, value_circle_to_line, paint_black_fill_5, MyPath.arrowPoints);//上升箭头
                MyCanvas.drawLineToStraighten(canvas, value_circle_to_line, paint_white_stroke_15, this, pointCircle, MyPath.circlePoints);//贝塞尔线
                MyCanvas.drawSkipLine(MyPath.skipWayPath(pointCircle));//画一条运动轨迹
                break;
            case STATUS3://箭头跳起来伴随着箭头变成标牌和直线震动
                MyCanvas.drawStraightenLine(canvas, value_line_up_and_down, paint_white_stroke_15, MyPath.straightLinePath());//画一条直线
                MyCanvas.drawScutcheon(canvas, value_arrow_to_scutcheon,skipPosition, paint_black_fill_5, MyPath.arrowPoints,pointCircle.getR());//跳跃箭头
                break;
            case STATUS4://画一条普通直线，两段
                MyCanvas.drawNormalLine(canvas, value_simulate_x,value_simulate_y, paint_white_stroke_15, MyPath.normalPath());//画一条普通直线
                MyCanvas.drawScutcheonDown(canvas, value_simulate_x,value_simulate_y, paint_black_fill_5,paint_white_stroke_35, MyPath.arrowPointsDown);//标牌下载的动画
                break;
            case STATUS5://标牌翻转180°
                MyCanvas.drawNormalLine(canvas, value_simulate_x,value_simulate_y, paint_white_stroke_15, MyPath.normalPath());//画一条普通直线
                MyCanvas.drawScutcheonOverturn(canvas, value_overturn, paint_black_fill_5,paint_white_stroke_35, MyPath.arrowPointsDownSuccess);//翻转动画
                break;
            case STATUS6://抖一个激灵
                MyCanvas.drawNormalLine(canvas, value_simulate_x,value_simulate_y, paint_white_stroke_15, MyPath.normalPath());//画一条普通直线
                MyCanvas.drawShakeScutcheon(canvas, value_shake, paint_black_fill_5,paint_white_stroke_35);//抖一个激灵
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveStatus=MoveStatus.STATUS0;
                ValueAnimator valueAnimator = animationDiskToCircle();
                valueAnimator.start();
                break;
        }
        return true;
    }

    //圆渐变成同心圆的动画
    private ValueAnimator animationDiskToCircle() {
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, (int) (pointCircle.getR()-15));
        valueAnimator.setDuration(theAnimationExecuteTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_disk_to_circle = (int) animation.getAnimatedValue();
                invalidate();
                if (value_disk_to_circle>=(pointCircle.getR()-15)){
                    moveStatus=MoveStatus.STATUS1;
                    animationCircleToLine2().start();
                }
            }
        });
        return valueAnimator;
    }


    //让圆形上部分展开变成一条向下的曲线，这是曲线运行的第一个状态
    private ValueAnimator animationCircleToLine2() {
        ValueAnimator controllerPointAnimation1 = ValueAnimator.ofInt(0, (int) (pointCircle.getR() /4));
        controllerPointAnimation1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_circle_to_line = (int) animation.getAnimatedValue();
                invalidate();
                if (value_circle_to_line>=(pointCircle.getR() /4)){
                    moveStatus=MoveStatus.STATUS2;
                    animationLineToStraighten().start();
                    value_circle_to_line=0;
                }
            }
        });
        controllerPointAnimation1.setDuration(theAnimationExecuteTime);
        return controllerPointAnimation1;
    }

    //让曲线变直，这是曲线运行的第二个状态
    private ValueAnimator animationLineToStraighten() {
        ValueAnimator controllerPointAnimation1 = ValueAnimator.ofInt(0, (int) pointCircle.getR());
        controllerPointAnimation1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_circle_to_line = (int) animation.getAnimatedValue();
                invalidate();
                if (value_circle_to_line>=(pointCircle.getR())){
                    moveStatus=MoveStatus.STATUS3;
                    animationLineVibrate().start();
                    animationArrowToScutcheon().start();
                    animationScutcheonSkip().start();
                    MyPath.setArrowPointsOffset();//增加偏移量
                }
            }
        });
        controllerPointAnimation1.setDuration(theAnimationExecuteTime );
        return controllerPointAnimation1;
    }

    //让曲线震荡
    private ValueAnimator animationLineVibrate() {
        ValueAnimator controllerPointAnimation1 = ValueAnimator.ofInt(0, (int) pointCircle.getR(),-(int) pointCircle.getR(),(int) pointCircle.getR()/2,-(int) pointCircle.getR()/2,0);
        controllerPointAnimation1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_line_up_and_down = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        controllerPointAnimation1.setDuration(theAnimationExecuteTime);
        return controllerPointAnimation1;
    }


    //从箭头变成标牌的动画
    private ValueAnimator animationArrowToScutcheon() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, (int) pointCircle.getR()/4);
        valueAnimator.setDuration(theAnimationExecuteTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_arrow_to_scutcheon = (int) animation.getAnimatedValue();
                invalidate();
                if (value_arrow_to_scutcheon>=(int) pointCircle.getR()/4){
                    moveStatus=MoveStatus.STATUS4;
                    animationSimulate_X().start();
                    animationSimulate_Y().start();
                }
            }
        });
        return valueAnimator;
    }

    //贝塞尔路基图，箭头跳动的方向
    public static float[] skipPosition={-1,-1};
    private ValueAnimator animationScutcheonSkip() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, (int) MyCanvas.skipLathMeasure.getLength());
        valueAnimator.setDuration(theAnimationExecuteTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (int) animation.getAnimatedValue();
                // 获取当前点坐标封装到mCurrentPosition

                MyCanvas.skipLathMeasure.getPosTan(value, skipPosition, null);
                postInvalidate();
            }
        });
        return valueAnimator;
    }

    //下载的时候模拟下载,X轴方向的变化
    private ValueAnimator animationSimulate_X() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,30,50,85, 100);
        valueAnimator.setDuration(theAnimationExecuteTime*15);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_simulate_x = (float) animation.getAnimatedValue();
                invalidate();
                if (value_simulate_x>=100){
                    moveStatus=MoveStatus.STATUS5;
                    animationScutcheonOverturn().start();
                }
            }
        });
        return valueAnimator;
    }
    //下载的时候模拟下载,Y轴方向的变化
    private ValueAnimator animationSimulate_Y() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pointCircle.getR()*4/5,0);
        valueAnimator.setDuration(theAnimationExecuteTime*15);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_simulate_y = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        return valueAnimator;
    }

    //下载完成以后，标牌翻转360度的动画
    private ValueAnimator animationScutcheonOverturn() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,360);
        valueAnimator.setDuration(theAnimationExecuteTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_overturn = (float) animation.getAnimatedValue();
                invalidate();
                if (value_overturn>=360){
                    moveStatus=MoveStatus.STATUS6;
                    animationScutcheonShake().start();
                }
            }
        });
        return valueAnimator;
    }

    //标牌翻转360度的动画,以后抖一个激灵的动画
    private ValueAnimator animationScutcheonShake() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,20,-20);
        valueAnimator.setDuration(theAnimationExecuteTime/2);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value_shake = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        return valueAnimator;
    }









}
