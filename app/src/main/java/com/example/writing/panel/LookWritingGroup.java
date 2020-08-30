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
        int width_here=width/2;
        button_width=button;
        writing2_width=width_here*8/10;
        if (writing2_width*2>(height-button)){
            writing2_width=(height-button)/2;
        }
        writing3_width=width_here*8/10;
        if (writing3_width*3>(height-button)){
            writing3_width=(height-button)/3;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int width_margin,height_margin;


        if (type==0){
            width_margin=(width-writing2_width*2)/3;
            if (width_margin<0){
                width_margin=0;
            }
            height_margin=(height-writing2_width*2-button_width)/3;
            if (height_margin<0){
                height_margin=0;
            }
            if (answer_position==1){

                for (int i=0;i<4;i+=2){
                    getChildAt(i).layout(width_margin*(1+i/2)+writing2_width*(i/2),height_margin,width_margin*(1+i/2)+writing2_width*(i/2+1),height_margin+writing2_width);

                }
                for (int i=1;i<4;i+=2){
                    getChildAt(i).layout(width_margin*(1+i/2)+writing2_width*(i/2),height_margin*2+writing2_width,width_margin*(1+i/2)+writing2_width*(i/2+1),height_margin*2+writing2_width*2);
                }
                int width_pos=0,height_pos=0;
                height_pos+=button_width;
                width_pos+=width/4;
                for (int i=4;i<6;i++){
                    getChildAt(i).layout(width_pos-button_width,height-height_pos,width_pos+button_width,height);
                    width_pos=width*3/4;

                }

            }

            else if(answer_position==0){


                for (int i=0;i<4;i+=3){
                    getChildAt(i).layout(width_margin*(1+i/2)+writing2_width*(i/2),height_margin,width_margin*(1+i/2)+writing2_width*(i/2+1),height_margin+writing2_width);

                }
                for (int i=1;i<3;i+=1){
                    getChildAt(i).layout(width_margin*(1+i/2)+writing2_width*(i/2),height_margin*2+writing2_width,width_margin*(1+i/2)+writing2_width*(i/2+1),height_margin*2+writing2_width*2);
                }
                int width_pos=0,height_pos=0;
                height_pos+=button_width;
                width_pos+=width/4;
                for (int i=4;i<6;i++){
                    getChildAt(i).layout(width_pos-button_width,height-height_pos,width_pos+button_width,height);
                    width_pos=width*3/4;

                }

            }



        }
        else{
            width_margin=(width-writing3_width*2)/3;
            if (width_margin<0){
                width_margin=0;
            }
            height_margin=(height-writing3_width*3-button_width)/4;
            if (height_margin<0){
                height_margin=0;
            }


            if (answer_position==0||answer_position==10||answer_position==20||answer_position==210){

                for (int i=0;i<4;i+=3){
                    getChildAt(i).layout(width_margin*(1+i/2)+writing3_width*(i/2),height_margin,width_margin*(1+i/2)+writing3_width*(i/2+1),height_margin+writing3_width);

                }
                for (int i=1;i<3;i+=1){
                    getChildAt(i).layout(width_margin*(1+i/2)+writing3_width*(i/2),height_margin*2+writing3_width,width_margin*(1+i/2)+writing3_width*(i/2+1),height_margin*2+writing3_width*2);
                }
                getChildAt(6).layout(width_margin,height_margin*3+writing3_width*2,width_margin+writing3_width,height_margin*3+writing3_width*3);
                getChildAt(7).layout(width_margin*2+writing3_width,height_margin*3+writing3_width*2,width_margin*2+writing3_width*2,height_margin*3+writing3_width*3);
                int width_pos=0,height_pos=0;
                height_pos+=button_width;
                width_pos+=width/4;
                for (int i=4;i<6;i++){
                    getChildAt(i).layout(width_pos-button_width,height-height_pos,width_pos+button_width,height);
                    width_pos=width*3/4;

                }

            }

            else if(answer_position==1||answer_position==21){

                for (int i=0;i<4;i+=2){
                    getChildAt(i).layout(width_margin*(1+i/2)+writing3_width*(i/2),height_margin,width_margin*(1+i/2)+writing3_width*(i/2+1),height_margin+writing3_width);

                }
                for (int i=1;i<4;i+=2){
                    getChildAt(i).layout(width_margin*(1+i/2)+writing3_width*(i/2),height_margin*2+writing3_width,width_margin*(1+i/2)+writing3_width*(i/2+1),height_margin*2+writing3_width*2);
                }
                getChildAt(6).layout(width_margin,height_margin*3+writing3_width*2,width_margin+writing3_width,height_margin*3+writing3_width*3);
                getChildAt(7).layout(width_margin*2+writing3_width,height_margin*3+writing3_width*2,width_margin*2+writing3_width*2,height_margin*3+writing3_width*3);
                int width_pos=0,height_pos=0;
                height_pos+=button_width;
                width_pos+=width/4;
                for (int i=4;i<6;i++){
                    getChildAt(i).layout(width_pos-button_width,height-height_pos,width_pos+button_width,height);
                    width_pos=width*3/4;

                }



            }
            else if(answer_position==2){
                for (int i=0;i<4;i+=2){
                    getChildAt(i).layout(width_margin*(1+i/2)+writing3_width*(i/2),height_margin,width_margin*(1+i/2)+writing3_width*(i/2+1),height_margin+writing3_width);

                }
                for (int i=1;i<4;i+=2){
                    getChildAt(i).layout(width_margin*(1+i/2)+writing3_width*(i/2),height_margin*3+writing3_width*2,width_margin*(1+i/2)+writing3_width*(i/2+1),height_margin*3+writing3_width*3);
                }
                getChildAt(6).layout(width_margin,height_margin*2+writing3_width,width_margin+writing3_width,height_margin*2+writing3_width*2);
                getChildAt(7).layout(width_margin*2+writing3_width,height_margin*2+writing3_width,width_margin*2+writing3_width*2,height_margin*2+writing3_width*2);
                int width_pos=0,height_pos=0;
                height_pos+=button_width;
                width_pos+=width/4;
                for (int i=4;i<6;i++){
                    getChildAt(i).layout(width_pos-button_width,height-height_pos,width_pos+button_width,height);
                    width_pos=width*3/4;

                }

            }



    }


    }
}
