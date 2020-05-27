package com.example.writing.puzzle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.writing.coosetype.LeftRightPage;

public class PuzzlePanelGroup extends ViewGroup {                                                   /*PuzzleGroup is for puzzle package only, it is used to align the puzzle view. These group contains 11puzzle views*/
    private Context this_context;
    int width,height,viewgroup_h;                                                                   //width means the width of screen, height means the same.
    public final  int[]begin_l=new int [13];                                                        //these two array use for storing the begin left and top position of 11 views
    public  final int[]begin_t=new int[13];

    public PuzzlePanelGroup(Context context, AttributeSet attrs) {                                  //to measure the screen size
        super(context, attrs);
        this_context=context;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {                         //to set the group to meet the screen
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {                          //to set the position of 11 layouts
        int left_pos=0;                                                                             //the panel index is arranged to be 0 deliberately because it needs to be last layout
        View sizeview=getChildAt(1);
        int puzzleHeight=sizeview.getMeasuredHeight();
        int puzzleWidth=sizeview.getMeasuredWidth();
        left_pos+=puzzleWidth/3*2;
        for (int i=1;i<5;i++){                                                                      //to set the question puzzle
            View childview=getChildAt(i);                                                           //there are two question puzzles which are on the top of screen

            if(i==2||i==4){
                puzzleWidth=sizeview.getMeasuredWidth()/2;
            }
            else{
                puzzleWidth=sizeview.getMeasuredWidth();
            }
            viewgroup_h=0;
            childview.layout(left_pos,viewgroup_h,left_pos+puzzleWidth,viewgroup_h+puzzleHeight);
            begin_l[i]=left_pos;
            begin_t[i]=viewgroup_h;
            left_pos+=puzzleWidth/10*9;
        }
        puzzleWidth=sizeview.getMeasuredWidth();
        left_pos=0;
        for (int i=5;i<9;i++){                                                                      // to set the radical puzzles which are in the upper part of screen
            View childview=getChildAt(i);
            viewgroup_h=puzzleHeight;
            childview.layout(left_pos,viewgroup_h,left_pos+puzzleWidth,viewgroup_h+puzzleHeight);
            this.begin_l[i]=left_pos;
            this.begin_t[i]=viewgroup_h;
            left_pos+=puzzleWidth;
        }
        left_pos=0;
        for (int i=0;i<1;i++){                                                                      //to set the puzzlepanel in the middle of screen
            View Paneled=getChildAt(i);
            int PanelHeight= Paneled.getMeasuredHeight();
            int PanelWidth=Paneled.getMeasuredWidth();
            left_pos=puzzleWidth;
            viewgroup_h*=3;
            Paneled.layout(left_pos,viewgroup_h,left_pos+PanelWidth,viewgroup_h+PanelHeight);
            begin_l[i]=left_pos;
            begin_t[i]=viewgroup_h;
        }
        left_pos=0;
        for (int i=9;i<13;i++){
            View childview=getChildAt(i);
            viewgroup_h=height-2*puzzleHeight;
            childview.layout(left_pos,viewgroup_h,left_pos+puzzleWidth,viewgroup_h+puzzleHeight);            //to set the radical puzzles which are in the bottom of screen
            begin_l[i]=left_pos;
            begin_t[i]=viewgroup_h;
            left_pos+=puzzleWidth;
        }




    }






}
