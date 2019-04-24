package com.gioppl.myviewdemo;
import java.util.ArrayList;

/**
 * create time：2019/4/22 19:24
 * creater：17664
 * desc:e
 */
public class MyPath {
    private static final float BIZER_CONSTANT = 0.5522f;
    public static ArrayList<PointBean> circlePoints;

    //用贝塞尔画圆
    public static ArrayList<PointBean> BezierCircle(PointBean pointCircle) {
        float offset=pointCircle.getR()*0.1f;
        float offsetB=pointCircle.getR()*0.05f;

        circlePoints = new ArrayList<>();
        PointBean point0 = new PointBean(pointCircle.getX(), pointCircle.getY() - pointCircle.getR()+offsetB);
        PointBean point1 = new PointBean(pointCircle.getX() - pointCircle.getR()+offset, pointCircle.getY() - pointCircle.getR()+offset);
        PointBean point2 = new PointBean(pointCircle.getX() - pointCircle.getR()+offsetB, pointCircle.getY());
        PointBean point3 = new PointBean(pointCircle.getX() - pointCircle.getR()+offset, pointCircle.getY() + pointCircle.getR()-offset);
        PointBean point4 = new PointBean(pointCircle.getX(), pointCircle.getY() + pointCircle.getR()-offsetB);
        PointBean point5 = new PointBean(pointCircle.getX() + pointCircle.getR()-offset, pointCircle.getY() + pointCircle.getR()-offset);
        PointBean point6 = new PointBean(pointCircle.getX() + pointCircle.getR()-offsetB, pointCircle.getY());
        PointBean point7 = new PointBean(pointCircle.getX() + pointCircle.getR()-offset, pointCircle.getY() - pointCircle.getR()+offset);
        PointBean point8 = new PointBean(pointCircle.getX(), pointCircle.getY() - pointCircle.getR()+offsetB);
        circlePoints.add(point0);
        circlePoints.add(point1);
        circlePoints.add(point2);
        circlePoints.add(point3);
        circlePoints.add(point4);
        circlePoints.add(point5);
        circlePoints.add(point6);
        circlePoints.add(point7);
        circlePoints.add(point8);
        return circlePoints;
    }




    /**
     * 画箭头的
     * @param pointCircle
     * @return
     */
    public static ArrayList<PointBean> arrowPoints;
    public static ArrayList<PointBean> arrowPath(PointBean pointCircle) {

        arrowPoints = new ArrayList<>();
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
        arrowPoints.add(arrowPoint0);
        arrowPoints.add(arrowPoint1);
        arrowPoints.add(arrowPoint2);
        arrowPoints.add(arrowPoint3);
        arrowPoints.add(arrowPoint4);
        arrowPoints.add(arrowPoint5);
        arrowPoints.add(arrowPoint6);
        return arrowPoints;
    }

    //因为上一个动画结束以后，没有向上移动，所以添加偏移量
    public static float arrowOffset;
    public static void setArrowPointsOffset(){
        for (int i=0;i<arrowPoints.size();i++){
            arrowPoints.get(i).setY(arrowPoints.get(i).getY()-arrowOffset);
        }
    }



    /**
     * 画一条直线
     * @param pointCircle
     * @return
     */
    public static ArrayList<PointBean> linePoints;
    public static ArrayList<PointBean> straightLinePath() {
        linePoints = new ArrayList<>();
        float startX = circlePoints.get(0).getX();
        float startY = circlePoints.get(0).getY();
        float endX=circlePoints.get(8).getX();
        float endY = circlePoints.get(8).getY();
        PointBean point1=new PointBean(startX,startY);
        PointBean point2=new PointBean(startX+(endX-startX)/3,startY);
        PointBean point3=new PointBean(startX+(endX-startX)/3*2,startY);
        PointBean point4=new PointBean(endX,endY);
        linePoints.add(point1);
        linePoints.add(point2);
        linePoints.add(point3);
        linePoints.add(point4);
        return linePoints;
    }

    /**
     * 贝塞尔路径图，标牌跳动的路径
     * @return
     */
    public static ArrayList<PointBean> skipPoints;
    public static ArrayList<PointBean> skipWayPath(PointBean bean){
        skipPoints = new ArrayList<>();
        float startX =(circlePoints.get(0).getX()+circlePoints.get(8).getX())/2;
        float startY =circlePoints.get(0).getY();
        float endX=circlePoints.get(0).getX();
        float endY =circlePoints.get(0).getY();
        PointBean point1=new PointBean(startX,startY);
        PointBean point2=new PointBean(startX+(endX-startX)/3,startY-bean.getR()*3);
        PointBean point3=new PointBean(startX+(endX-startX)/3*2,startY-bean.getR()*3);
        PointBean point4=new PointBean(endX,endY);
        skipPoints.add(point1);
        skipPoints.add(point2);
        skipPoints.add(point3);
        skipPoints.add(point4);
        return skipPoints;
    }
}
