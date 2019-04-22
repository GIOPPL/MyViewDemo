package com.gioppl.myviewdemo;

import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;

/**
 * create time：2019/4/22 21:00
 * creater：17664
 * desc:
 */
public class MyPaint {
    
    public static Paint getPaintByAntiAliasAndDither(int color,Paint.Style style,int width){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        paint.setPathEffect(new CornerPathEffect(10f));
        return paint;
    }
}
