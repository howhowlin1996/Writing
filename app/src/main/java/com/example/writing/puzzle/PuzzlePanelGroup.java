package com.example.writing.puzzle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.writing.R;

public class PuzzlePanelGroup extends ViewGroup {                                                   /*PuzzleGroup is for puzzle package only, it is used to align the puzzle view. These group contains 11puzzle views*/
    private Context this_context;
    int width,height,viewgroup_h,piece;                                                                   //width means the width of screen, height means the same.
    public final  int[]begin_l=new int [15];                                                        //these two array use for storing the begin left and top position of 11 views
    public  final int[]begin_t=new int[15];


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
        AnswerBoard answer1=findViewById(R.id.answerBoard1);
        AnswerBoard answer2=findViewById(R.id.answerBoard2);
        AnswerBoard answer3=findViewById(R.id.answerBoard3);
        //Log.d("haha",new String(" "+widthMeasureSpec+" "+heightMeasureSpec));
        int widthhere=width*140/100;
        if(piece/10==1){
            answer1.measure(widthhere/2,widthhere/2);
            answer2.measure(0,0);
            answer3.measure(0,0);
        }
        else if(piece/10==2){
            if(piece%10==1){
                answer1.measure(widthhere/2,widthhere/4);
                answer2.measure(widthhere/2,widthhere/4);
                answer3.measure(0,0);
            }
            else if(piece%10==2){
                answer1.measure(widthhere/2,widthhere/6);
                answer2.measure(widthhere/2,widthhere/6);
                answer3.measure(widthhere/2,widthhere/6);
            }

        }
        else if(piece/10==3){
            if(piece%10==1){
                answer1.measure(widthhere/4,widthhere/2);
                answer2.measure(widthhere/4,widthhere/2);
                answer3.measure(0,0);
            }
            else if(piece%10==2){
                answer1.measure(widthhere/6,widthhere/2);
                answer2.measure(widthhere/6,widthhere/2);
                answer3.measure(widthhere/6,widthhere/2);
            }
        }
        else if(piece/10==4){
            answer1.measure(widthhere/4,widthhere/4);
            answer2.measure(widthhere/4,widthhere/4);
            answer3.measure(widthhere/4,widthhere/4);
        }
        else if(piece/10==5){
            answer1.measure(widthhere/2,widthhere/2);
            answer2.measure(widthhere/4,widthhere/4);
            answer3.measure(0,0);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {                          //to set the position of 11 layouts
        int left_pos=0;                                                                             //the panel index is arranged to be 0 deliberately because it needs to be last layout
        View sizeview=getChildAt(7);
        int puzzleHeight=sizeview.getMeasuredHeight();
        int puzzleWidth=sizeview.getMeasuredWidth();
        int height_here=2*puzzleHeight;
        for (int i=3;i<4;i+=2){                                                                      //to set the question puzzle
            View childview=getChildAt(i);                                                           //there are two question puzzles which are on the top of screen
            viewgroup_h=0;
            childview.layout(width*15/100,height_here,width*85/100,height_here+width*70/100);
            begin_l[i]=width*15/100;
            begin_t[i]=height_here;
            height_here+=width*70/100;
        }
        height_here=2*puzzleHeight;
        for (int i=4;i<7;i+=2){                                                                      //to set the question puzzle
            View childview=getChildAt(i);                                                           //there are two question puzzles which are on the top of screen
            viewgroup_h=0;
            childview.layout(width*85/100,height_here,width*95/100,height_here+width*70/100);
            begin_l[i]=width*15/100;
            begin_t[i]=height_here;
            height_here+=width*70/100;
        }
        puzzleWidth=sizeview.getMeasuredWidth();
        left_pos=0;
        for (int i=7;i<11;i++){                                                                      // to set the radical puzzles which are in the upper part of screen
            View childview=getChildAt(i);
            viewgroup_h=puzzleHeight;
            childview.layout(left_pos,0,left_pos+puzzleWidth,puzzleHeight);
            this.begin_l[i]=left_pos;
            this.begin_t[i]=0;
            left_pos+=puzzleWidth;
        }
        answerBoard();

        left_pos=0;
        for (int i=11;i<15;i++){
            View childview=getChildAt(i);
            viewgroup_h=height-puzzleHeight;
            childview.layout(left_pos,viewgroup_h,left_pos+puzzleWidth,height);            //to set the radical puzzles which are in the bottom of screen
            begin_l[i]=left_pos;
            begin_t[i]=viewgroup_h;
            left_pos+=puzzleWidth;
        }




    }
    protected  void splitNum(int num){
        piece=num;
    }



    protected void answerBoard( ){


     int left_pos;
        View sizeview=getChildAt(7);


        int puzzleWidth=width*15/100;
        int puzzleHeight=sizeview.getHeight();
        viewgroup_h=puzzleHeight*2+width*70/100;
        if(piece/10==1){
            for (int i=0;i<1;i++){                                                                      //to set the puzzlepanel in the middle of screen
                View Paneled=getChildAt(i);
                int PanelHeight= Paneled.getMeasuredHeight();
                int PanelWidth=Paneled.getMeasuredWidth();
                left_pos=puzzleWidth;
                Paneled.layout(left_pos,viewgroup_h,left_pos+PanelWidth,viewgroup_h+PanelHeight);
                begin_l[i]=left_pos;
                begin_t[i]=viewgroup_h;
            }

        }
        else if(piece/10==2){
            if(piece%10==1){
                for (int i=0;i<2;i++){                                                                      //to set the puzzlepanel in the middle of screen
                    View Paneled=getChildAt(i);
                    int PanelHeight= Paneled.getMeasuredHeight();
                    int PanelWidth=Paneled.getMeasuredWidth();
                    //Log.d("errorhere",new String(" "+PanelHeight+" "+PanelWidth));
                    left_pos=puzzleWidth;
                    Paneled.layout(left_pos,viewgroup_h,left_pos+PanelWidth,viewgroup_h+PanelHeight);
                    begin_l[i]=left_pos;
                    begin_t[i]=viewgroup_h;
                    viewgroup_h+=PanelHeight;
                }
            }
            else if(piece%10==2){
                for (int i=0;i<3;i++){                                                                      //to set the puzzlepanel in the middle of screen
                    View Paneled=getChildAt(i);
                    int PanelHeight= Paneled.getMeasuredHeight();
                    int PanelWidth=Paneled.getMeasuredWidth();
                    //Log.d("errorhere",new String(" "+PanelHeight+" "+PanelWidth));
                    left_pos=puzzleWidth;
                    Paneled.layout(left_pos,viewgroup_h,left_pos+PanelWidth,viewgroup_h+PanelHeight);
                    begin_l[i]=left_pos;
                    begin_t[i]=viewgroup_h;
                    viewgroup_h+=PanelHeight;
                }
            }


        }
        else if(piece/10==3){
            left_pos=puzzleWidth;
            if(piece%10==1){
                for (int i=0;i<2;i++){                                                                      //to set the puzzlepanel in the middle of screen
                    View Paneled=getChildAt(i);
                    int PanelHeight= Paneled.getMeasuredHeight();
                    int PanelWidth=Paneled.getMeasuredWidth();
                    //Log.d("errorhere",new String(" "+PanelHeight+" "+PanelWidth));
                    Paneled.layout(left_pos,viewgroup_h,left_pos+PanelWidth,viewgroup_h+PanelHeight);
                    begin_l[i]=left_pos;
                    begin_t[i]=viewgroup_h;
                    left_pos+=PanelWidth;

                }

            }
            else if(piece%10==2){
                for (int i=0;i<3;i++){                                                                      //to set the puzzlepanel in the middle of screen
                    View Paneled=getChildAt(i);
                    int PanelHeight= Paneled.getMeasuredHeight();
                    int PanelWidth=Paneled.getMeasuredWidth();
                    //Log.d("errorhere",new String(" "+PanelHeight+" "+PanelWidth));
                    Paneled.layout(left_pos,viewgroup_h,left_pos+PanelWidth,viewgroup_h+PanelHeight);
                    begin_l[i]=left_pos;
                    begin_t[i]=viewgroup_h;
                    left_pos+=PanelWidth;

                }


            }
        }
        else if(piece/10==4){
            left_pos=puzzleWidth;
            int PanelHeight= getChildAt(0).getMeasuredHeight();
            int PanelWidth=getChildAt(0).getMeasuredWidth();
            getChildAt(0).layout(left_pos+PanelWidth/2,viewgroup_h,left_pos+PanelWidth*3/2,viewgroup_h+PanelHeight);
            begin_l[0]=left_pos+PanelWidth/2;
            begin_t[0]=viewgroup_h;
            getChildAt(1).layout(left_pos,viewgroup_h+PanelHeight,left_pos+PanelWidth,viewgroup_h+2*puzzleHeight);
            begin_l[1]=left_pos;
            begin_t[1]=viewgroup_h+PanelHeight;
            getChildAt(2).layout(left_pos+PanelWidth,viewgroup_h+PanelHeight,left_pos+2*PanelWidth,viewgroup_h+2*PanelHeight);
            begin_l[2]=left_pos+PanelWidth;
            begin_t[2]=viewgroup_h+PanelHeight;
        }
        else if(piece/10==5){
            View Paneled=getChildAt(0);
            int PanelHeight= Paneled.getMeasuredHeight();
            int PanelWidth=Paneled.getMeasuredWidth();
            left_pos=puzzleWidth;
            Paneled.layout(left_pos,viewgroup_h,left_pos+PanelWidth,viewgroup_h+PanelHeight);
            begin_l[0]=left_pos;
            begin_t[0]=viewgroup_h;
            int secondPanel_W=getChildAt(1).getMeasuredWidth();
            int secondPanel_h=getChildAt(1).getMeasuredHeight();

            if(piece%10==1){
                getChildAt(1).layout(left_pos+secondPanel_W,viewgroup_h,left_pos+2*secondPanel_W,viewgroup_h+secondPanel_h);
                begin_l[1]=left_pos+secondPanel_W;
                begin_t[1]=viewgroup_h;

            }
            else if(piece%10==2){
                getChildAt(1).layout(left_pos+secondPanel_W,viewgroup_h+secondPanel_h,left_pos+2*secondPanel_W,viewgroup_h+2*secondPanel_h);
                begin_l[1]=left_pos+secondPanel_W;
                begin_t[1]=viewgroup_h+secondPanel_h;

            }
            else if(piece%10==3){
                getChildAt(1).layout(left_pos+secondPanel_W,viewgroup_h+secondPanel_h/2,left_pos+2*secondPanel_W,viewgroup_h+3*secondPanel_h/2);
                begin_l[1]=left_pos+secondPanel_W;
                begin_t[1]=viewgroup_h+secondPanel_h/2;

            }
            else if(piece%10==4){
                getChildAt(1).layout(left_pos+secondPanel_W/2,viewgroup_h+secondPanel_h/2,left_pos+3*secondPanel_W/2,viewgroup_h+3*secondPanel_h/2);
                begin_l[1]=left_pos+secondPanel_W/2;
                begin_t[1]=viewgroup_h+secondPanel_h/2;

            }
            else if(piece%10==5){
                getChildAt(1).layout(left_pos+secondPanel_W/2,viewgroup_h+secondPanel_h,left_pos+3*secondPanel_W/2,viewgroup_h+2*secondPanel_h);
                begin_l[1]=left_pos+secondPanel_W/2;
                begin_t[1]=viewgroup_h+secondPanel_h;
            }
            else if(piece %10==6){
                getChildAt(1).layout(left_pos,viewgroup_h+secondPanel_h,left_pos+secondPanel_W,viewgroup_h+2*secondPanel_h);
                begin_l[1]=left_pos;
                begin_t[1]=viewgroup_h+secondPanel_h;

            }

        }









    }






}
