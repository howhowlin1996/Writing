package com.example.writing.puzzle;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class PuzzlePanelGroup extends ViewGroup {
    private Context this_context;
    int width,height,viewgroup_h;
    public PuzzlePanelGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this_context=context;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left_pos=0;
        View sizeview=getChildAt(1);
        int puzzleHeight=sizeview.getMeasuredHeight();
        int puzzleWidth=sizeview.getMeasuredWidth();
        for (int i=1;i<3;i++){
            View childview=getChildAt(i);
            left_pos+=puzzleWidth;
            viewgroup_h=0;
            childview.layout(left_pos,viewgroup_h,left_pos+puzzleWidth,viewgroup_h+puzzleHeight);
        }
        left_pos=0;
        for (int i=3;i<7;i++){
            View childview=getChildAt(i);
            viewgroup_h=puzzleHeight;
            childview.layout(left_pos,viewgroup_h,left_pos+puzzleWidth,viewgroup_h+puzzleHeight);
            left_pos+=puzzleWidth;
        }
        left_pos=0;
        for (int i=0;i<1;i++){
            View Paneled=getChildAt(i);
            int PanelHeight= Paneled.getMeasuredHeight();
            int PanelWidth=Paneled.getMeasuredWidth();
            Log.d("error",new String(PanelHeight+"///////"+PanelWidth));
            left_pos=puzzleWidth;
            viewgroup_h*=3;
            Paneled.layout(left_pos,viewgroup_h,left_pos+PanelWidth,viewgroup_h+PanelHeight);
        }
        left_pos=0;
        for (int i=7;i<11;i++){
            View childview=getChildAt(i);
            viewgroup_h=height-2*puzzleHeight;
            childview.layout(left_pos,viewgroup_h,left_pos+puzzleWidth,viewgroup_h+puzzleHeight);
            left_pos+=puzzleWidth;
        }




    }


}
