package com.example.writing.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

public class LookWritingGroup extends ViewGroup {
    int width,height;
    public LookWritingGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
        this.setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width_margin=width*10/100;
        int height_margin=height*10/100;
        int panelWidth=width*40/100;
        int panelHeight=width*40/100;
        for (int i=0;i<4;i+=2){
            getChildAt(i).layout(width_margin+panelWidth*(i/2),height_margin,width_margin+panelWidth*(i/2+1),height_margin+panelHeight);

        }
        for (int i=1;i<4;i+=2){
            getChildAt(i).layout(width_margin+panelWidth*(i/2),height_margin+panelHeight,width_margin+panelWidth*(i/2+1),height_margin+panelHeight*2);
        }
        int width_pos=0,height_pos=0;
        height_pos+=width/7;
        for (int i=4;i<6;i++){
            width_pos+=width/16;
            getChildAt(i).layout(width_pos,height-height_pos,width_pos+3*width/8,height);
            width_pos+=3*width/8+width/16;

        }

    }
}
