package com.gioppl.myviewdemo;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
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
    public static void drawCircleLine4_2(Canvas canvas, int value , Paint paint, View view,PointBean pointCircle,ArrayList<PointBean> points) {

        float offset=value*1f;
        int r2=value*13;
        int r=value*4;
        int r_div_2=value*2;
        int r_div_4=value;
        Path circleLinePath=new Path();
        if ((value < pointCircle.getR() * 2) ) {
//            circleLinePath.moveTo(points.get(0).getX()-r2, points.get(0).getY()+r_div_2);
//            circleLinePath.quadTo(points.get(1).getX()-r, points.get(1).getY()+r, points.get(2).getX()-offset, points.get(2).getY()+offset);
//            circleLinePath.quadTo(points.get(3).getX()+r_div_2, points.get(3).getY()-r_div_2, points.get(4).getX(), points.get(4).getY()-r_div_2);
//            circleLinePath.quadTo(points.get(5).getX()-r_div_2, points.get(5).getY()-r_div_2, points.get(6).getX()+offset, points.get(6).getY()+offset);
//            circleLinePath.quadTo(points.get(7).getX()+r, points.get(7).getY()+r, points.get(8).getX()+r2, points.get(8).getY()+r_div_2);

            MyPath.circlePoints.get(0).setX(points.get(0).getX()-r2);       MyPath.circlePoints.get(0).setY(points.get(0).getY()+r_div_2);
            MyPath.circlePoints.get(1).setX(points.get(1).getX()-r);        MyPath.circlePoints.get(1).setY(points.get(1).getY()+r);
            MyPath.circlePoints.get(2).setX(points.get(2).getX()-offset);   MyPath.circlePoints.get(2).setY(points.get(2).getY()+offset);
            MyPath.circlePoints.get(3).setX(points.get(3).getX()+r_div_2);  MyPath.circlePoints.get(3).setY(points.get(3).getY()-r_div_2);
            MyPath.circlePoints.get(4).setX(points.get(4).getX());          MyPath.circlePoints.get(4).setY(points.get(4).getY()-r_div_2);
            MyPath.circlePoints.get(5).setX(points.get(5).getX()-r_div_2);  MyPath.circlePoints.get(5).setY(points.get(5).getY()-r_div_2);
            MyPath.circlePoints.get(6).setX(points.get(6).getX()+offset);   MyPath.circlePoints.get(6).setY(points.get(6).getY()+offset);
            MyPath.circlePoints.get(7).setX(points.get(7).getX()+r);        MyPath.circlePoints.get(7).setY(points.get(7).getY()+r);
            MyPath.circlePoints.get(8).setX(points.get(8).getX()+r2);       MyPath.circlePoints.get(8).setY(points.get(8).getY()+r_div_2);

            circleLinePath.moveTo(MyPath.circlePoints.get(0).getX(), MyPath.circlePoints.get(0).getY());
            circleLinePath.quadTo(MyPath.circlePoints.get(1).getX(), MyPath.circlePoints.get(1).getY(), MyPath.circlePoints.get(2).getX(), MyPath.circlePoints.get(2).getY());
            circleLinePath.quadTo(MyPath.circlePoints.get(3).getX(), MyPath.circlePoints.get(3).getY(), MyPath.circlePoints.get(4).getX(), MyPath.circlePoints.get(4).getY());
            circleLinePath.quadTo(MyPath.circlePoints.get(5).getX(), MyPath.circlePoints.get(5).getY(), MyPath.circlePoints.get(6).getX(), MyPath.circlePoints.get(6).getY());
            circleLinePath.quadTo(MyPath.circlePoints.get(7).getX(), MyPath.circlePoints.get(7).getY(), MyPath.circlePoints.get(8).getX(), MyPath.circlePoints.get(8).getY());

            canvas.drawPath(circleLinePath, paint);




        } else {
            view.invalidate();
        }

    }

    /**
     * 第二个状态，向下的曲线变直
     * @param canvas
     * @param value
     * @param paint
     * @param view
     * @param pointCircle
     * @param points
     */
    public static void drawLineToStraighten(Canvas canvas, int value , Paint paint, View view,PointBean pointCircle,ArrayList<PointBean> points) {
        float offset[]=new float[9];
        for (int i=1;i<offset.length;i++){
            offset[i]=(points.get(i).getY()-points.get(0).getY())/pointCircle.getR()*value;
        }
        Path circleLinePath=new Path();
        circleLinePath.moveTo(points.get(0).getX(), points.get(0).getY());
        circleLinePath.quadTo(points.get(1).getX(), points.get(1).getY()-offset[1], points.get(2).getX(), points.get(2).getY()-offset[2]);
        circleLinePath.quadTo(points.get(3).getX(), points.get(3).getY()-offset[3], points.get(4).getX(), points.get(4).getY()-offset[4]);
        circleLinePath.quadTo(points.get(5).getX(), points.get(5).getY()-offset[5], points.get(6).getX(), points.get(6).getY()-offset[6]);
        circleLinePath.quadTo(points.get(7).getX(), points.get(7).getY()-offset[7], points.get(8).getX(), points.get(8).getY()-offset[8]);

        canvas.drawPath(circleLinePath, paint);

        //画点
//        paint.setColor(Color.BLACK);
//        for (int i=0;i<points.size();i++){
//            canvas.drawPoint(points.get(i).getX(), points.get(i).getY(),paint);
//        }
//        paint.setColor(Color.WHITE);



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
    public static void drawArrow(Canvas canvas, int value, Paint paint,ArrayList<PointBean> points) {
        value=1;
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

//        MyPath.arrowPoints.get(0).setX(points.get(0).getX()-arrow_three_fourth);MyPath.arrowPoints.get(0).setY(points.get(0).getY()-arrow_half);
//        MyPath.arrowPoints.get(1).setCirclePoint(points.get(1).getX()-arrow_three_fourth, points.get(1).getY());
//        MyPath.arrowPoints.get(2).setCirclePoint(points.get(2).getX()+arrow_one_fourth, points.get(2).getY());
//        MyPath.arrowPoints.get(3).setCirclePoint(points.get(3).getX(), points.get(3).getY()-arrow_one_fourth);
//        MyPath.arrowPoints.get(4).setCirclePoint(points.get(4).getX()-arrow_one_fourth,  points.get(4).getY());
//        MyPath.arrowPoints.get(5).setCirclePoint(points.get(5).getX()+arrow_three_fourth, points.get(5).getY());
//        MyPath.arrowPoints.get(6).setCirclePoint(points.get(6).getX()+arrow_three_fourth, points.get(6).getY()-arrow_half);
        canvas.drawPath(arrowPath,paint);
    }
    //箭头往上到基准线
    public static void arrowUpToZeroLine(Canvas canvas, int value, Paint paint,ArrayList<PointBean> points) {
        Path arrowPath = new Path();
        arrowPath.moveTo(points.get(0).getX(), points.get(0).getY()-value);
        arrowPath.lineTo(points.get(1).getX(), points.get(1).getY()-value);
        arrowPath.lineTo(points.get(2).getX(), points.get(2).getY()-value);
        arrowPath.lineTo(points.get(3).getX(), points.get(3).getY()-value);
        arrowPath.lineTo(points.get(4).getX(), points.get(4).getY()-value);
        arrowPath.lineTo(points.get(5).getX(), points.get(5).getY()-value);
        arrowPath.lineTo(points.get(6).getX(), points.get(6).getY()-value);
        arrowPath.lineTo(points.get(0).getX(), points.get(0).getY()-value);
        MyPath.arrowOffset=value;
        canvas.drawPath(arrowPath,paint);
    }
    //画一条直线,直线震荡
    public static void drawStraightenLine(Canvas canvas, int value, Paint paint,ArrayList<PointBean> points){
        Path linePath=new Path();
        linePath.moveTo(points.get(0).getX(),points.get(0).getY());
        linePath.cubicTo(points.get(1).getX(),points.get(1).getY()-value,points.get(2).getX(),points.get(2).getY()-value,points.get(3).getX(),points.get(3).getY());
        canvas.drawPath(linePath,paint);
    }

    //画标牌
    public static void drawScutcheon2(Canvas canvas, int value, Paint paint,ArrayList<PointBean> points) {
        int arrowOffset=0;
        int arrow_three_fourth=value/4*3;
        int arrow_half=value/2;
        int arrow_one_fourth=value/4;
        Path arrowPath = new Path();
        arrowPath.moveTo(points.get(0).getX()-arrow_three_fourth, points.get(0).getY()-arrow_half-arrowOffset);
        arrowPath.lineTo(points.get(1).getX()-arrow_three_fourth, points.get(1).getY()-arrowOffset);
        arrowPath.lineTo(points.get(2).getX()+arrow_one_fourth, points.get(2).getY()-arrowOffset);
        arrowPath.lineTo(points.get(3).getX(), points.get(3).getY()-arrow_one_fourth-arrowOffset);
        arrowPath.lineTo(points.get(4).getX()-arrow_one_fourth, points.get(4).getY()-arrowOffset);
        arrowPath.lineTo(points.get(5).getX()+arrow_three_fourth, points.get(5).getY()-arrowOffset);
        arrowPath.lineTo(points.get(6).getX()+arrow_three_fourth, points.get(6).getY()-arrow_half-arrowOffset);
        arrowPath.lineTo(points.get(0).getX()-arrow_three_fourth, points.get(0).getY()-arrow_half-arrowOffset);
        canvas.drawPath(arrowPath,paint);
    }
    //画标牌,value=1/4个半径
    public static void drawScutcheon(Canvas canvas, int value,float[] skipPosition, Paint paint,ArrayList<PointBean> points) {
        Path arrowPath = new Path();
        int r=100;
        if (skipPosition[0]!=-1&&skipPosition[1]!=-1){
            float x=skipPosition[0];
            float y=skipPosition[1];
            arrowPath.moveTo(x-r/4-value, y-r);
            arrowPath.lineTo(x-r/4-value, y-r/2);
            arrowPath.lineTo(x-r/2+value, y-r/2);
            arrowPath.lineTo(x, y-value);
            arrowPath.lineTo(x+r/2-value, y-r/2);
            arrowPath.lineTo(x+r/4+value, y-r/2);
            arrowPath.lineTo(x+r/4+value, y-r);
            arrowPath.lineTo(x-r/4-value, y-r);
        }else {

        }
//        arrowPath.moveTo(points.get(0).getX()-value, points.get(0).getY());
//        arrowPath.lineTo(points.get(1).getX()-value, points.get(1).getY());
//        arrowPath.lineTo(points.get(2).getX()+value, points.get(2).getY());
//        arrowPath.lineTo(points.get(3).getX(), points.get(3).getY()-value);
//        arrowPath.lineTo(points.get(4).getX()-value, points.get(4).getY());
//        arrowPath.lineTo(points.get(5).getX()+value, points.get(5).getY());
//        arrowPath.lineTo(points.get(6).getX()+value, points.get(6).getY());
//        arrowPath.lineTo(points.get(0).getX()-value, points.get(0).getY());
        canvas.drawPath(arrowPath,paint);
    }

    //画一条曲线，跳动的路径
    public static PathMeasure skipLathMeasure;
    public static void drawSkipLine(Canvas canvas, Paint paint,ArrayList<PointBean> points){
        Path linePath=new Path();
        linePath.moveTo(points.get(0).getX(),points.get(0).getY());
        linePath.cubicTo(points.get(1).getX(),points.get(1).getY(),points.get(2).getX(),points.get(2).getY(),points.get(3).getX(),points.get(3).getY());
        skipLathMeasure=new PathMeasure();
        skipLathMeasure.setPath(linePath,false);//路径不闭合
//        canvas.drawPath(linePath,paint);
    }

    public static void drawPoint(Canvas canvas, Paint paint,float[] skipPosition){
        canvas.drawPoint(skipPosition[0],skipPosition[1],paint);
    }

}
