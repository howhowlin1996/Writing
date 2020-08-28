package com.example.writing.panel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

public class LookWritingGroup extends ViewGroup {
    int width,height,type=1,answer_position=0,button_width,writing2_width,writing3_width;
    public LookWritingGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
        this.setMeasuredDimension(width,height);
    }
    public void setType(int type_here,int answer_position_here){
        type=type_here;
        answer_position=answer_position_here;

    }

    public  void setDimension(int button,int writing_panel2,int writing_panel3){

        button_width=button;
        writing2_width=writing_panel2;
        writing3_width=writing_panel3;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int panelWidth=width*40/100;
        int panelHeight=width*40/100;
        int width_margin=width*10/100;
        int height_margin=height/2-panelHeight;
        if (type==0){
            if (answer_position==1){

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

            else if(answer_position==0){

                for (int i=0;i<4;i+=3){
                    getChildAt(i).layout(width_margin+panelWidth*(i/2),height_margin,width_margin+panelWidth*(i/2+1),height_margin+panelHeight);

                }
                for (int i=1;i<3;i+=1){
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
        else{


            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){

                for (int i=0;i<4;i+=3){
                    getChildAt(i).layout(width_margin+panelWidth*(i/2),height_margin,width_margin+panelWidth*(i/2+1),height_margin+panelHeight);

                }
                for (int i=1;i<3;i+=1){
                    getChildAt(i).layout(width_margin+panelWidth*(i/2),height_margin+panelHeight,width_margin+panelWidth*(i/2+1),height_margin+panelHeight*2);
                }
                getChildAt(6).layout(width_margin,height_margin+panelHeight*2,width_margin+panelWidth,height_margin+panelHeight*3);
                getChildAt(7).layout(width_margin+panelWidth,height_margin+panelHeight*2,width_margin+panelWidth*2,height_margin+panelHeight*3);
                int width_pos=0,height_pos=0;
                height_pos+=width/7;
                for (int i=4;i<6;i++){
                    width_pos+=width/16;
                    getChildAt(i).layout(width_pos,height-height_pos,width_pos+3*width/8,height);
                    width_pos+=3*width/8+width/16;

                }

            }

            else if(answer_position==1||answer_position==21){

                for (int i=0;i<4;i+=2){
                    getChildAt(i).layout(width_margin+panelWidth*(i/2),height_margin,width_margin+panelWidth*(i/2+1),height_margin+panelHeight);

                }
                for (int i=1;i<4;i+=2){
                    getChildAt(i).layout(width_margin+panelWidth*(i/2),height_margin+panelHeight,width_margin+panelWidth*(i/2+1),height_margin+panelHeight*2);
                }
                getChildAt(6).layout(width_margin,height_margin+panelHeight*2,width_margin+panelWidth,height_margin+panelHeight*3);
                getChildAt(7).layout(width_margin+panelWidth,height_margin+panelHeight*2,width_margin+panelWidth*2,height_margin+panelHeight*3);
                int width_pos=0,height_pos=0;
                height_pos+=width/7;
                for (int i=4;i<6;i++){
                    width_pos+=width/16;
                    getChildAt(i).layout(width_pos,height-height_pos,width_pos+3*width/8,height);
                    width_pos+=3*width/8+width/16;

                }



            }
            else if(answer_position==2){
                for (int i=0;i<4;i+=2){
                    getChildAt(i).layout(width_margin+panelWidth*(i/2),height_margin,width_margin+panelWidth*(i/2+1),height_margin+panelHeight);

                }
                for (int i=1;i<4;i+=2){
                    getChildAt(i).layout(width_margin+panelWidth*(i/2),height_margin+panelHeight*2,width_margin+panelWidth*(i/2+1),height_margin+panelHeight*3);
                }
                getChildAt(6).layout(width_margin,height_margin+panelHeight,width_margin+panelWidth,height_margin+panelHeight*2);
                getChildAt(7).layout(width_margin+panelWidth,height_margin+panelHeight,width_margin+panelWidth*2,height_margin+panelHeight*2);
                int width_pos=0,height_pos=0;
                height_pos+=width/7;
                for (int i=4;i<6;i++){
                    width_pos+=width/16;
                    getChildAt(i).layout(width_pos,height-height_pos,width_pos+3*width/8,height);
                    width_pos+=3*width/8+width/16;

                }

            }



    }


    }
}
