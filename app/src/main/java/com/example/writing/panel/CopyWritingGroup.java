package com.example.writing.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

public class CopyWritingGroup extends ViewGroup {
    int width,height;
    Context this_context;
    public  CopyWritingGroup(Context context, AttributeSet attrs) {
        super(context,attrs);
        this_context=context;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
        this.setMeasuredDimension(width,height);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("here"," ");
        int width_pos=0,height_pos=0;
        height_pos+=width/7;

        for (int i=0;i<2;i++){
            width_pos+=width/16;
            getChildAt(i).layout(width_pos,height-height_pos,width_pos+3*width/8,height);
            width_pos+=3*width/8+width/16;

        }
        for(int i=2;i<6;i+=2){
            getChildAt(i).layout(width*15/100,height_pos,width*85/100,height_pos+width*70/100);
            height_pos+=+width*70/100;

        }
        height_pos=width/7;
        for (int i=3;i<6;i+=2){
            getChildAt(i).layout(width*85/100,height_pos,width*95/100,height_pos+width*70/100);
            height_pos+=+width*70/100;

        }
    }
}
