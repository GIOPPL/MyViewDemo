package com.gioppl.myviewdemo;

import android.animation.TimeInterpolator;

/**
 * create time：2019/4/24 11:32
 * creater：17664
 * desc:
 */
public class LineInterPolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float result=0;
        if (input<=0.25){//第一次震动
            return result;
        }else if (input<=0.6){//第二次震动
            return result;
        }else {//第三次震动
            return result;
        }

    }
}
