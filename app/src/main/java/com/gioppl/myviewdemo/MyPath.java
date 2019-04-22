package com.gioppl.myviewdemo;

import java.util.ArrayList;

/**
 * create time：2019/4/22 19:24
 * creater：17664
 * desc:e
 */
public class MyPath {
    private static final float BIZER_CONSTANT = 0.5522f;


    //用贝塞尔画圆
    public static ArrayList<PointBean> BezierCircle(PointBean pointCircle) {
        float offset=pointCircle.getR()*0.1f;
        float offsetB=pointCircle.getR()*0.05f;
        ArrayList<PointBean> points;
        points = new ArrayList<>();
        PointBean point0 = new PointBean(pointCircle.getX(), pointCircle.getY() - pointCircle.getR()+offsetB);
        PointBean point1 = new PointBean(pointCircle.getX() - pointCircle.getR()+offset, pointCircle.getY() - pointCircle.getR()+offset);
        PointBean point2 = new PointBean(pointCircle.getX() - pointCircle.getR()+offsetB, pointCircle.getY());
        PointBean point3 = new PointBean(pointCircle.getX() - pointCircle.getR()+offset, pointCircle.getY() + pointCircle.getR()-offset);
        PointBean point4 = new PointBean(pointCircle.getX(), pointCircle.getY() + pointCircle.getR()-offsetB);
        PointBean point5 = new PointBean(pointCircle.getX() + pointCircle.getR()-offset, pointCircle.getY() + pointCircle.getR()-offset);
        PointBean point6 = new PointBean(pointCircle.getX() + pointCircle.getR()-offsetB, pointCircle.getY());
        PointBean point7 = new PointBean(pointCircle.getX() + pointCircle.getR()-offset, pointCircle.getY() - pointCircle.getR()+offset);
        PointBean point8 = new PointBean(pointCircle.getX(), pointCircle.getY() - pointCircle.getR()+offsetB);
        points.add(point0);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);
        points.add(point6);
        points.add(point7);
        points.add(point8);
        return points;
    }

    public static ArrayList<PointBean> arrowPath(PointBean pointCircle) {
        ArrayList<PointBean> points;
        points = new ArrayList<>();
        float x = pointCircle.getX();
        float y = pointCircle.getY();
        float r = pointCircle.getR();
        PointBean arrowPoint0 = new PointBean(x - r / 4, y - r / 2);
        PointBean arrowPoint1 = new PointBean(x - r / 4, y);
        PointBean arrowPoint2 = new PointBean(x - r / 2, y);
        PointBean arrowPoint3 = new PointBean(x, y + r / 2);
        PointBean arrowPoint4 = new PointBean(x + r / 2, y);
        PointBean arrowPoint5 = new PointBean(x + r / 4, y);
        PointBean arrowPoint6 = new PointBean(x + r / 4, y - r / 2);
        points.add(arrowPoint0);
        points.add(arrowPoint1);
        points.add(arrowPoint2);
        points.add(arrowPoint3);
        points.add(arrowPoint4);
        points.add(arrowPoint5);
        points.add(arrowPoint6);
        return points;
    }
}
