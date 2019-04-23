package com.gioppl.myviewdemo;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.View;

import java.util.ArrayList;

import static com.gioppl.myviewdemo.DownView.MoveStatus.ERROR_STATUS;

/**
 * create time：2019/4/22 16:22
 * creater：17664
 * desc:
 */
public class MyCanvas {
    /**
     * 贝塞尔画圆,四个锚点
     * @param canvas 画板
     * @param value 变化量
     * @param paint 画笔
     * @param view view
     * @param pointCircle 圆的信息
     * @param points 点集合
     */
    public static void drawCircleLine4_1(Canvas canvas, int value, Paint paint, View view,PointBean pointCircle,ArrayList<PointBean> points) {
        Path circleLinePath=new Path();
        if ((value < pointCircle.getR() * 2) ) {
            circleLinePath.moveTo(points.get(0).getX() - value, points.get(0).getY());
            circleLinePath.quadTo(points.get(1).getX(), points.get(1).getY(), points.get(2).getX(), points.get(2).getY() - value / 2);
            circleLinePath.quadTo(points.get(3).getX(), points.get(3).getY() - value, points.get(4).getX(), points.get(4).getY() - value);
            circleLinePath.quadTo(points.get(5).getX(), points.get(5).getY() - value, points.get(6).getX(), points.get(6).getY() - value / 2);
            circleLinePath.quadTo(points.get(7).getX(), points.get(7).getY(), points.get(8).getX() + value, points.get(8).getY());
            canvas.drawPath(circleLinePath, paint);
            circleLinePath.reset();
        } else {
            view.invalidate();
        }

    }

    /**
     * 贝塞尔画圆,四个锚点
     * @param canvas 画板
     * @param value 变化量
     * @param paint 画笔
     * @param view view
     * @param pointCircle 圆的信息
     * @param points 点集合
     */
    public static void drawCircleLine4_2(Canvas canvas, int value, Paint paint, View view,PointBean pointCircle,ArrayList<PointBean> points) {
        float offset=value*1f;
        int r2=value*13;
        int r=value*4;
        int r_div_2=value*2;
        int r_div_4=value;
        Path circleLinePath=new Path();
        if ((value < pointCircle.getR() * 2) ) {
            circleLinePath.moveTo(points.get(0).getX()-r2, points.get(0).getY()+r_div_2);
            circleLinePath.quadTo(points.get(1).getX()-r, points.get(1).getY()+r, points.get(2).getX()-offset, points.get(2).getY()+offset);
            circleLinePath.quadTo(points.get(3).getX()+r_div_2, points.get(3).getY()-r_div_2, points.get(4).getX(), points.get(4).getY()-r_div_2);
            circleLinePath.quadTo(points.get(5).getX()-r_div_2, points.get(5).getY()-r_div_2, points.get(6).getX()+offset, points.get(6).getY()+offset);
            circleLinePath.quadTo(points.get(7).getX()+r, points.get(7).getY()+r, points.get(8).getX()+r2, points.get(8).getY()+r_div_2);
            canvas.drawPath(circleLinePath, paint);

            for (int i=0;i<points.size();i++){
                canvas.drawPoint(points.get(i).getX(),points.get(i).getY(),paint);
            }



            circleLinePath.reset();



        } else {
            view.invalidate();
        }

    }
    //贝塞尔画圆8个锚点
    public static void drawCircleLine8(Canvas canvas, int controllerValue, Paint paint, View view,PointBean pointCircle,ArrayList<PointBean> points) {
        Path circleLinePath=new Path();
        circleLinePath.moveTo(points.get(0).getX(), points.get(0).getY());
        circleLinePath.cubicTo(points.get(1).getX(), points.get(1).getY(), points.get(2).getX(), points.get(2).getY(), points.get(3).getX(), points.get(3).getY());
        circleLinePath.moveTo(points.get(3).getX(), points.get(3).getY());
        circleLinePath.cubicTo(points.get(4).getX(), points.get(4).getY(), points.get(5).getX(), points.get(5).getY(), points.get(6).getX(), points.get(6).getY());
        circleLinePath.moveTo(points.get(6).getX(), points.get(6).getY());
        circleLinePath.cubicTo(points.get(7).getX(), points.get(7).getY(), points.get(8).getX(), points.get(8).getY(), points.get(9).getX(), points.get(9).getY());
        circleLinePath.moveTo(points.get(9).getX(), points.get(9).getY());
        circleLinePath.cubicTo(points.get(10).getX(), points.get(10).getY(), points.get(11).getX(), points.get(11).getY(), points.get(0).getX(), points.get(0).getY());
        canvas.drawPath(circleLinePath, paint);
        canvas.drawPoint(points.get(0).getX(), points.get(0).getY(), paint);
        canvas.drawPoint(points.get(1).getX(), points.get(1).getY(), paint);
        canvas.drawPoint(points.get(2).getX(), points.get(2).getY(), paint);
        canvas.drawPoint(points.get(3).getX(), points.get(3).getY(), paint);
        canvas.drawPoint(points.get(4).getX(), points.get(4).getY(), paint);
        canvas.drawPoint(points.get(5).getX(), points.get(5).getY(), paint);
        canvas.drawPoint(points.get(6).getX(), points.get(6).getY(), paint);
        canvas.drawPoint(points.get(7).getX(), points.get(7).getY(), paint);
        canvas.drawPoint(points.get(8).getX(), points.get(8).getY(), paint);
        canvas.drawPoint(points.get(9).getX(), points.get(9).getY(), paint);
        canvas.drawPoint(points.get(10).getX(), points.get(10).getY(), paint);
        canvas.drawPoint(points.get(11).getX(), points.get(11).getY(), paint);

    }
    //直线振荡
    public static void drawWave(Canvas canvas, int value, Paint paint, View view,PointBean pointCircle,ArrayList<PointBean> points) {
        float r = pointCircle.getR();
        float x = pointCircle.getX();
        float y = pointCircle.getY() - r;
        Path wavePath = new Path();


            PointBean point1 = new PointBean(x - 2 * r, y);
            PointBean point2 = new PointBean(x, y - value);
            PointBean point3 = new PointBean(x + 2 * r, y);
            wavePath.moveTo(point1.getX(), point1.getY());
            wavePath.quadTo(point2.getX(), point2.getY(), point3.getX(), point3.getY());
            canvas.drawPath(wavePath, paint);
            view.invalidate();
    }

    //画圆，渐变圆
    public static void drawCircle(Canvas canvas, int value, Paint paint, View view,PointBean pointCircle,ArrayList<PointBean> points) {
        if (value < pointCircle.getR()) {
            float x = pointCircle.getX();
            float y = pointCircle.getY();
            float r = pointCircle.getR();
            canvas.drawCircle(x, y, r, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            canvas.drawCircle(x, y, value, paint);
            paint.setXfermode(null);
            view.invalidate();
        } else {
            view.invalidate();
        }
    }
    //画箭头
    public static void drawArrow(Canvas canvas, int value, Paint paint, View view,PointBean pointCircle,ArrayList<PointBean> points) {
        int arrow_three_fourth=value/4*3;
        int arrow_half=value/2;
        int arrow_one_fourth=value/4;
        Path arrowPath = new Path();
        arrowPath.moveTo(points.get(0).getX()-arrow_three_fourth, points.get(0).getY()-arrow_half);
        arrowPath.lineTo(points.get(1).getX()-arrow_three_fourth, points.get(1).getY());
        arrowPath.lineTo(points.get(2).getX()+arrow_one_fourth, points.get(2).getY());
        arrowPath.lineTo(points.get(3).getX(), points.get(3).getY()-arrow_one_fourth);
        arrowPath.lineTo(points.get(4).getX()-arrow_one_fourth, points.get(4).getY());
        arrowPath.lineTo(points.get(5).getX()+arrow_three_fourth, points.get(5).getY());
        arrowPath.lineTo(points.get(6).getX()+arrow_three_fourth, points.get(6).getY()-arrow_half);
        arrowPath.lineTo(points.get(0).getX()-arrow_three_fourth, points.get(0).getY()-arrow_half);
        canvas.drawPath(arrowPath,paint);
    }



//    //画一个标牌
//    private void drawFlag(Canvas canvas) {
//        int width = getWidth() - 200;
//        int height = getHeight() - 200;
//        mArrowPath.moveTo(width / 2, height / 2);
//        mArrowPath.lineTo(width / 2 + 200, height / 2);
//        mArrowPath.lineTo(width / 2 + 200, height / 2 + 100);
//        mArrowPath.lineTo(width / 2 + 130, height / 2 + 100);
//        mArrowPath.lineTo(width / 2 + 100, height / 2 + 150);
//        mArrowPath.lineTo(width / 2 + 70, height / 2 + 100);
//        mArrowPath.lineTo(width / 2, height / 2 + 100);
//        mArrowPath.lineTo(width / 2, height / 2 + 100);
//        mArrowPath.lineTo(width / 2, height / 2);
//        canvas.drawText("100%", width / 2 + 35, height / 2 + 70, mTextPaint);
//        canvas.drawPath(mArrowPath, mArrowPaint);
//    }
}
