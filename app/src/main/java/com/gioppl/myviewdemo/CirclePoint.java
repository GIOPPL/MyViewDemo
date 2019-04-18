package com.gioppl.myviewdemo;

/**
 * create time：2019/4/17 20:44
 * creater：17664
 * desc:
 */
public class CirclePoint {
    private float x;
    private float y;
    private float r;

    public CirclePoint(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void  setCirclePoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public CirclePoint(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
