package com.example.writing.badgefactory;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

public class BadgeGroup extends ViewGroup {
    int width,height;
    Context this_context;
    public BadgeGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
        this_context=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.setMeasuredDimension(width,width*5);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int last_height_position=0;
        int badgeWidth=width/2;
        int badgeHeight=badgeWidth;
        for (int i=0;i<this.getChildCount();i++){
            if (i%2==0){
                getChildAt(i).layout(0,last_height_position,badgeWidth,last_height_position+badgeHeight);

            }
            else {
                getChildAt(i).layout(badgeWidth,last_height_position,2*badgeWidth,last_height_position+badgeHeight);
                last_height_position+=badgeHeight;

            }
        }
        invalidate();

    }
}
